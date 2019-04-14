package com.ilsxh.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ilsxh.blog.dto.BlogFilter;
import com.ilsxh.blog.dto.StatisticsDto;
import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Comment;
import com.ilsxh.blog.entity.LogMessage;
import com.ilsxh.blog.service.BlogService;
import com.ilsxh.blog.service.LogService;
import com.ilsxh.blog.service.admin.SiteService;
import com.ilsxh.blog.service.admin.UserService;
import com.ilsxh.blog.util.CommonUtil;
import com.ilsxh.blog.util.Constant;
import com.ilsxh.blog.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private LogService logService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/blog")
    public String index(HttpServletRequest request, @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(name = "limit", required = false, defaultValue = "15") int limit, Model model) {

        PageInfo<Blog> blogList = blogService.selectBlogListByFilter(new BlogFilter(), page, limit);
        model.addAttribute("blogList", blogList);
        return "admin/blog_list";
    }


    @GetMapping(value = {"/index", "/"})
    public String index(HttpServletRequest request, Model model) {

        // 获取后台统计数
        StatisticsDto statistics = siteService.getStatistics();
        model.addAttribute("statistics", statistics);

        // 获取5篇最新博客
        List<Blog> blogList = siteService.getNewBlogs(4);
        model.addAttribute("blogList", blogList);

        // 获取5条评论
        List<Comment> commentList = siteService.getComments(5);
        model.addAttribute("commentList", commentList);

        // 获取5篇日志
        PageInfo<LogMessage> logs = logService.getLogs(1, 5);
        List<LogMessage> logList = logs.getList();
        model.addAttribute("logList", logList);

        return "admin/index";
    }

    /**
     * 跳转到博客编辑页面
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/blog/publish")
    public String newArticle(HttpServletRequest request) {
//        MetaCond metaCond = new MetaCond();
//        metaCond.setType(Types.CATEGORY.getType());
//        List<MetaDomain> metas = metaService.getMetas(metaCond);
//        request.setAttribute("categories",metas);

        return "admin/blog_edit";
    }

    /**
     * 提交编辑完成的博客
     *
     * @param title
     * @param titlePic
     * @param slug
     * @param content
     * @param type
     * @param status
     * @param categories
     * @param tags
     * @param allowComment
     * @return
     */
    @PostMapping(value = "/blog/publish")
    @ResponseBody
    public Response publishBlog(
            @RequestParam(name = "title", required = true)
                    String title,
            @RequestParam(name = "titlePic", required = false)
                    String titlePic,
            @RequestParam(name = "slug", required = false)
                    String slug,
            @RequestParam(name = "content", required = true)
                    String content,
            @RequestParam(name = "type", required = true)
                    String type,
            @RequestParam(name = "status", required = true)
                    String status,
            @RequestParam(name = "categories", required = false, defaultValue = "默认分类")
                    String categories,
            @RequestParam(name = "tags", required = false)
                    String tags,
            @RequestParam(name = "allowComment", required = true)
                    Boolean allowComment
    ) {
        Blog blog = new Blog();
        blog.setBlogTitle(title);
        blog.setBlogSummary(content.substring(0, 30));
        blog.setBlogImages("http://pknhrkp8l.bkt.clouddn.com/login.jpg");
        blog.setBlogContent(content);
        blog.setBlogClicknum(0);
        blog.setBlogKeyword("spring");
        blog.setBlogAddTime(new Timestamp(System.currentTimeMillis()));

        // 只允许博客文章有分类，防止作品被收入分类
//        contentDomain.setTags(type.equals(Types.ARTICLE.getType()) ? tags : null);
//        contentDomain.setCategories(type.equals(Types.ARTICLE.getType()) ? categories : null);
//        contentDomain.setAllowComment(allowComment ? 1 : 0);

        // 添加文章
        blogService.addBlog(blog);

        return Response.success();
    }

    @PostMapping("/blog/delete")
    @ResponseBody
    public Response deleteArticle(@RequestParam(name = "blogId", required = true) Integer blogId) {
        // 删除文章
        blogService.deleteBlogById(blogId);
        // 写入日志
        return Response.success();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response toLogin(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "username", required = true) String username,
                            @RequestParam(name = "password", required = true) String password,
                            @RequestParam(name = "remember_me", required = false) String remember_me) {
        try {
            // 调用Service登录方法
            Integer userId = userService.login(username, password);
            // 设置用户信息session
            request.getSession().setAttribute(Constant.LOGIN_SESSION_KEY, userId);
            // 判断是否勾选记住我
            if (remember_me != null && remember_me.length() != 0) {
                CommonUtil.setCookie(response, userId);
            }
            // 写入日志
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        // 返回登录成功信息
        return Response.success();
    }
}
