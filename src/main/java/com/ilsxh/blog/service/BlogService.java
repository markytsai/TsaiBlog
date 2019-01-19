package com.ilsxh.blog.service;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public Blog selectBlogByBlogId(Integer blogId) {
        return blogMapper.selectBlogByBlogId(blogId);
    }

    public List<Blog> selectBlogList() {
        return blogMapper.selectBlogList();
    }
}
