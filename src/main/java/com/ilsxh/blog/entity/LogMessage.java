package com.ilsxh.blog.entity;

import com.ilsxh.blog.enums.LogTypeEnum;

import java.sql.Timestamp;

public class LogMessage {
    /**
     * 日志ID，自增
     */
    private int logId;

    /**
     * 日志操作用户名
     */
    private String logUsername;

    /**
     * 日志创建时间
     */
    private Timestamp createTime;

    /**
     * 日志详情
     */
    private String logDesc;

    /**
     * 日志操作持续时间
     */
    private long logDuration;

    /**
     * 日志操作的IP
     */
    private String userIp;

    /**
     * 日志的级别：info, warning, error
     */
    private String logLevel;

    /**
     * 日志类型：web, dubbo, mq
     */
    private LogTypeEnum logType;
    /**
     * 接口请求入参
     */
    private String reqParam;

    /**
     * 接口返回值，出参
     */
    private String resResult;

    /**
     * 接口请求函数名称
     */
    private String reqMethods;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogUsername() {
        return logUsername;
    }

    public void setLogUsername(String logUsername) {
        this.logUsername = logUsername;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }

    public long getLogDuration() {
        return logDuration;
    }

    public void setLogDuration(long logDuration) {
        this.logDuration = logDuration;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public LogTypeEnum getLogType() {
        return logType;
    }

    public void setLogType(LogTypeEnum logType) {
        this.logType = logType;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public String getResResult() {
        return resResult;
    }

    public void setResResult(String resResult) {
        this.resResult = resResult;
    }

    public String getReqMethods() {
        return reqMethods;
    }

    public void setReqMethods(String reqMethods) {
        this.reqMethods = reqMethods;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "logId=" + logId +
                ", logUsername='" + logUsername + '\'' +
                ", createTime=" + createTime +
                ", logDesc='" + logDesc + '\'' +
                ", logDuration=" + logDuration +
                ", userIp='" + userIp + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", logType=" + logType +
                ", reqParam='" + reqParam + '\'' +
                ", resResult='" + resResult + '\'' +
                ", reqMethods='" + reqMethods + '\'' +
                '}';
    }
}
