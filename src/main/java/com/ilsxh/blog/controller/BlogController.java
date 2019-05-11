package com.ilsxh.blog.controller;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Category;
import com.ilsxh.blog.entity.Link;
import com.ilsxh.blog.service.BlogService;
import com.ilsxh.blog.service.CategoryService;
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
    private CategoryService categoryService;
    private UserService userService;
    private LinkService linkService;

    @Autowired
    public BlogController(BlogService blogService, CategoryService categoryService, UserService userService, LinkService linkService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.linkService = linkService;
    }

    @RequestMapping("/blog/{blogUrl}")
    public String getBlogDetai(@PathVariable String blogUrl, Model model) {

        Blog blog = blogService.selectBlogByBlogId(blogUrl);
        model.addAttribute("blog", blog);

        List<Category> categoryList = categoryService.selectTopicList();
        model.addAttribute("categoryList", categoryList);

        List<Category> blogRelatedCategoryList = categoryService.selectRelatedTopics(blog.getBlogId());
        model.addAttribute("blogRelatedTopicList", blogRelatedCategoryList);

        List<Blog> recentBlogList = blogService.selectRecentBlogs();
        model.addAttribute("recentBlogList", recentBlogList);

        return "front/singleBlog";
    }


    @RequestMapping(value = {"/blogs", "/"})
    public String getAllBlogs(Model model) {
        List<Blog> blogList = blogService.selectBlogList();
        model.addAttribute("blogList", blogList);

        List<Category> categoryList = categoryService.selectTopicList();
        model.addAttribute("categoryList", categoryList);

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
