package com.ilsxh.blog.service;

import com.ilsxh.blog.annotation.Loggable;
import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Loggable(descpition = "查看博客详情", include = "blogId")
    public Blog selectBlogByBlogId(Integer blogId) {
        return blogMapper.selectBlogByBlogId(blogId);
    }

    @Loggable(descpition = "查询博客列表")
    public List<Blog> selectBlogList() {
        return blogMapper.selectBlogList();
    }

    @Loggable(descpition = "获取最近博客")
    public List<Blog> selectRecentBlogs() {
        return blogMapper.selectRecentBlogs();
    }

    @Loggable(descpition = "获取热门博客")
    public List<Blog> selectHotBlogList() {
        return blogMapper.selectHotBlogs();
    }
}
