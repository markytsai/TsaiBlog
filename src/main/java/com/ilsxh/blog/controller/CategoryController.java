package com.ilsxh.blog.controller;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Category;
import com.ilsxh.blog.service.BlogService;
import com.ilsxh.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/category/{categoryId}")
    public String getBlogListByTopicId(@PathVariable Integer categoryId, Model model) {
        List<Blog> blogList = categoryService.getBlogListByTopicId(categoryId);
        model.addAttribute("blogList", blogList);

        List<Category> categoryList = categoryService.selectTopicList();
        model.addAttribute("topicList", categoryList);

        List<Blog> hotBlogList = blogService.selectHotBlogList();
        model.addAttribute("hotBlogList", hotBlogList);

        return "front/blogs-category";

    }
}
