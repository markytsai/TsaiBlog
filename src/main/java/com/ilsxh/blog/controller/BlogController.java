package com.ilsxh.blog.controller;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/index")
    @ResponseBody
    public Map<String, String> returnString() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "index");
        return map;
    }

    @RequestMapping("/blogDetail")
    public String returnIndex() {
        return "singleBlog";
    }

    @RequestMapping("/blog/{blogId}")
    public String getBlogDetai(@PathVariable Integer blogId, Model model) {

        Blog blog = blogService.selectBlogByBlogId(blogId);
        model.addAttribute("blog", blog);
        return "singleBlog";
    }


    @RequestMapping("/blogs")
    public String getAllBlogs(Model model) {
        List<Blog> blogList = blogService.selectBlogList();
        model.addAttribute("blogList", blogList);
        return "blogs";
    }
}
