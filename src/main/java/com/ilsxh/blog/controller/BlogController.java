package com.ilsxh.blog.controller;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Topic;
import com.ilsxh.blog.service.BlogService;
import com.ilsxh.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController {

    private BlogService blogService;
    private TopicService topicService;

    @Autowired
    public BlogController(BlogService blogService, TopicService topicService) {
        this.blogService = blogService;
        this.topicService = topicService;
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

        return "singleBlog";
    }


    @RequestMapping("/blogs")
    public String getAllBlogs(Model model) {
        List<Blog> blogList = blogService.selectBlogList();
        model.addAttribute("blogList", blogList);

        List<Topic> topicList = topicService.selectTopicList();
        model.addAttribute("topicList", topicList);

        List<Blog> hotBlogList = blogService.selectHotBlogList();
        model.addAttribute("hotBlogList", hotBlogList);

        return "blogs";
    }
}
