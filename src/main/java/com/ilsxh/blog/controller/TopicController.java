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
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/topic/{topicId}")
    public String getBlogListByTopicId(@PathVariable Integer topicId, Model model) {
        List<Blog> blogList = topicService.getBlogListByTopicId(topicId);
        model.addAttribute("blogList", blogList);

        List<Topic> topicList = topicService.selectTopicList();
        model.addAttribute("topicList", topicList);

        List<Blog> hotBlogList = blogService.selectHotBlogList();
        model.addAttribute("hotBlogList", hotBlogList);

        return "front/blogs-topic";

    }
}
