package com.ilsxh.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ilsxh.blog.annotation.Loggable;
import com.ilsxh.blog.dto.BlogFilter;
import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.mapper.BlogMapper;
import com.ilsxh.blog.redis.BlogKey;
import com.ilsxh.blog.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisService redisService;

    @Loggable(descpition = "查看博客详情", include = "blogId")
    public Blog selectBlogByBlogId(String blogUrl) {
        Blog blog = blogMapper.selectBlogByBlogId(blogUrl.replace("-", " "));
        blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-"));
        return blog;
    }

    @Loggable(descpition = "查询博客列表")
    public List<Blog> selectBlogList() {
        List<Blog> blogList = redisService.getList(BlogKey.blogListKey, "blogList", Blog.class);
        if (blogList != null) {
            blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
            return blogList;
        }
        blogList = blogMapper.selectBlogList();
        redisService.setList(BlogKey.blogListKey, "blogList", blogList);
        blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
        return blogList;
    }

    @Loggable(descpition = "获取最近博客")
    public List<Blog> selectRecentBlogs() {
        List<Blog> blogList = blogMapper.selectRecentBlogs();
        blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
        return blogList;
    }

    @Loggable(descpition = "获取热门博客")
    public List<Blog> selectHotBlogList() {
        List<Blog> blogList = redisService.getList(BlogKey.hotBlogListKey, "blogList", Blog.class);
        if (blogList != null) {
            blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
            return blogList;
        }
        blogList = blogMapper.selectHotBlogs();
        redisService.setList(BlogKey.hotBlogListKey, "blogList", blogList);
        blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
        return blogList;
    }

    public void addBlog(Blog blog) {
        blogMapper.addBlog(blog);
    }

    public PageInfo<Blog> selectBlogListByFilter(BlogFilter blogFilter, int pageNum, int pageSize) {
        if (null == blogFilter) {
            return null;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> contents = blogMapper.getBlogsByFilter(blogFilter);
        PageInfo<Blog> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    public void deleteBlogById(Integer blogId) {
        blogMapper.deleteBlogById(blogId);
    }
}
