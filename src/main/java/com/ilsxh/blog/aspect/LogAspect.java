package com.ilsxh.blog.aspect;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ilsxh.blog.annotation.Loggable;
import com.ilsxh.blog.entity.LogMessage;
import com.ilsxh.blog.enums.LogScopeEnum;
import com.ilsxh.blog.mapper.LogMapper;
import com.ilsxh.blog.util.IPUtils;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

import static com.ilsxh.blog.util.Constant.NotPintLogInConsole;
import static com.ilsxh.blog.util.Constant.PrintLogInConsole;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogMapper logMapper;

    long startTime;
    long endTime;

    @Pointcut("execution(* com.ilsxh.blog.service.*.*(..))")
    public void pointerCutMethod() {
    }


    /**
     * 环绕通知
     *
     * @param joinPoint
     * @param annotation
     * @return
     */
    @Around(value = "pointerCutMethod() && @annotation(annotation)")
    public Object doAround(ProceedingJoinPoint joinPoint, Loggable annotation) {

        LogMessage logMessage = new LogMessage();
        // 设置日志创建时间
        logMessage.setCreateTime(new Timestamp(new Date().getTime()));

        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();

        // 设置访问IP
        String ip = IPUtils.getClientIp(request);
        logMessage.setUserIp(ip);

        // 从cookie中取出用户id，查询数据库
//        String userId = userHelperService.getUserIdFromRedis(request);
//        User loginUser = userHelperService.getUserByUserId(userId);
//        if (loginUser == null) {
//            throw new CustomException(StatusEnum.OPERATION_ERROR);
//        }
//        // 设置访问用户名称
        logMessage.setLogUsername("defalut-user");

        // 从切点上获取目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logMessage.setReqMethods(method.getName());

        String[] paramNames = signature.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>(paramNames.length);
        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], i);
        }
        String reqParam = JSON.toJSONString(Collections.emptyMap());
        Map<String, Object> params = getArgsMap(joinPoint, paramMap);
        if (params != null) {
            //序列化参数列表
            reqParam = JSON.toJSONString(params);
        }
        logMessage.setReqParam(reqParam);

        //方法注解实体
        Loggable methodLogAnnon = method.getAnnotation(Loggable.class);

        //日志打印外部开关默认关闭
        String logSwitch = methodLogAnnon.console() ? PrintLogInConsole : NotPintLogInConsole;

        //处理入参日志
        try {
            handleRequstLog(joinPoint, methodLogAnnon, request, logMessage, logSwitch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object result = null;
//        执行目标方法内容，获取执行结果
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //处理接口响应日志
        handleResponseLog(logSwitch, logMessage, methodLogAnnon, result);

        return result;

    }

    private void handleRequstLog(ProceedingJoinPoint point, Loggable methodLogAnnon, HttpServletRequest request,
                                 LogMessage logMessage, String logSwitch) throws Exception {

        Logger LOGGER = Logger.getLogger("WebLogAspect");
        String reqParam = "";

        //判断是否输出日志
        if (methodLogAnnon.loggable()
                && methodLogAnnon.logScope().contains(LogScopeEnum.BEFORE)
                && methodLogAnnon.console()
                && StringUtils.equals(logSwitch, PrintLogInConsole)) {
            //打印入参日志
            LOGGER.info("-- " + methodLogAnnon.descpition().toString() + point.getSignature().getName() + reqParam);
        }
        startTime = System.currentTimeMillis();
        //接口描述
        logMessage.setLogDesc(methodLogAnnon.descpition().toString());
        logMessage.setLogLevel(methodLogAnnon.logLevel());
        logMessage.setLogType(methodLogAnnon.logType());

        //...省略部分构造logMessage信息代码
    }

    private Map<String, Object> getMethodParamNames(Class cls,
                                                    String methodName, String include) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(cls));
        CtMethod cm = pool.get(cls.getName()).getDeclaredMethod(methodName);
        LocalVariableAttribute attr = (LocalVariableAttribute) cm
                .getMethodInfo().getCodeAttribute()
                .getAttribute(LocalVariableAttribute.tag);

        if (attr == null) {
            throw new Exception("attr is null");
        } else {
            Map<String, Object> paramNames = new HashMap<>();
            int paramNamesLen = cm.getParameterTypes().length;
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            if (StringUtils.isEmpty(include)) {
                for (int i = 0; i < paramNamesLen; i++) {
                    paramNames.put(attr.variableName(i + pos), i);
                }
            } else { // 若include不为空
                for (int i = 0; i < paramNamesLen; i++) {
                    String paramName = attr.variableName(i + pos);
                    if (include.indexOf(paramName) > -1) {
                        paramNames.put(paramName, i);
                    }
                }
            }
            return paramNames;
        }
    }

    private Map getArgsMap(ProceedingJoinPoint point, Map<String, Object> methodParamNames) {
        Object[] args = point.getArgs();
        if (null == methodParamNames) {
            return Collections.EMPTY_MAP;
        }
        for (Map.Entry<String, Object> entry : methodParamNames.entrySet()) {
            int index = Integer.valueOf(String.valueOf(entry.getValue()));
            if (args != null && args.length > 0) {
                Object arg = (null == args[index] ? "" : args[index]);
                methodParamNames.put(entry.getKey(), arg);
            }
        }
        return methodParamNames;
    }

    private void handleResponseLog(String logSwitch, LogMessage logMessage, Loggable methodLogAnnon, Object result) {
        // 处理切点返回值
        if (null != result) {
            Map<String, Object> tempMap = new HashMap<>(1);
            if (result instanceof List) {
                tempMap.put("responseListSize", ((List) result).size());
            } else if (result instanceof Map) {
                tempMap.put("responseMapSize", ((Map) result).size());
            } else {
                tempMap.put("responseContent", result);
            }
            logMessage.setResResult(JSON.toJSONString(tempMap));
        }
        endTime = System.currentTimeMillis();
        logMessage.setLogDuration(new Date().getTime() - startTime);
        //是否输出日志
        if (methodLogAnnon.loggable()
                && methodLogAnnon.logScope().contains(LogScopeEnum.AFTER)) {
            //判断是否入库
            if (methodLogAnnon.db()) {
                logMapper.insertLog(logMessage);
            }
            //判断是否输出到控制台
            if (methodLogAnnon.console()
                    && StringUtils.equals(logSwitch, PrintLogInConsole)) {
                System.out.println(logMessage.toString());
            }
        }
    }
}
