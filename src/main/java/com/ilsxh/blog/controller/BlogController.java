package com.ilsxh.blog.controller;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Link;
import com.ilsxh.blog.entity.Topic;
import com.ilsxh.blog.service.BlogService;
import com.ilsxh.blog.service.TopicService;
import com.ilsxh.blog.service.admin.LinkService;
import com.ilsxh.blog.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController {

    private BlogService blogService;
    private TopicService topicService;
    private UserService userService;
    private LinkService linkService;

    @Autowired
    public BlogController(BlogService blogService, TopicService topicService, UserService userService, LinkService linkService) {
        this.blogService = blogService;
        this.topicService = topicService;
        this.userService = userService;
        this.linkService = linkService;
    }

    @RequestMapping("/blog/{blogId}")
    public String getBlogDetai(@PathVariable Integer blogId, Model model) {

        Blog blog = blogService.selectBlogByBlogId(blogId);
        model.addAttribute("blog", blog);

        List<Topic> topicList = topicService.selectTopicList();
        model.addAttribute("topicList", topicList);

        List<Topic> blogRelatedTopicList = topicService.selectRelatedTopics(blogId);
        model.addAttribute("blogRelatedTopicList", blogRelatedTopicList);

        List<Blog> recentBlogList = blogService.selectRecentBlogs();
        model.addAttribute("recentBlogList", recentBlogList);

        return "front/singleBlog";
    }


    @RequestMapping("/blogs")
    public String getAllBlogs(Model model) {
        List<Blog> blogList = blogService.selectBlogList();
        model.addAttribute("blogList", blogList);

        List<Topic> topicList = topicService.selectTopicList();
        model.addAttribute("topicList", topicList);

        List<Blog> hotBlogList = blogService.selectHotBlogList();
        model.addAttribute("hotBlogList", hotBlogList);

        List<Link> linkList = linkService.selectLinkList();
        model.addAttribute("linkList", linkList);

        List<Blog> recentBlogList = blogService.selectRecentBlogs();
        model.addAttribute("recentBlogList", recentBlogList);

        return "front/blogs";
    }

    @GetMapping("/admin/login")
    public String toLogin() {
        return "front/login";
    }

//    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
//    @ResponseBody
//    public Response toLogin(HttpServletRequest request, HttpServletResponse response,
//                            @RequestParam(name = "username", required = true) String username,
//                            @RequestParam(name = "password", required = true) String password,
//                            @RequestParam(name = "remember_me", required = false) String remember_me) {
//        try {
//            // 调用Service登录方法
//            Integer userId = userService.login(username, password);
//            // 设置用户信息session
//            request.getSession().setAttribute(Constant.LOGIN_SESSION_KEY, userId);
//            // 判断是否勾选记住我
//            if (remember_me != null && remember_me.length() != 0) {
//                CommonUtil.setCookie(response, userId);
//            }
//            // 写入日志
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        // 返回登录成功信息
//        return Response.success();
//    }
}
