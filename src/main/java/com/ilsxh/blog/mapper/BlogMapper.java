package com.ilsxh.blog.mapper;

import com.ilsxh.blog.dto.BlogFilter;
import com.ilsxh.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {

    Blog selectBlogByBlogId(Integer blogId);

    List<Blog> selectBlogList();

    List<Blog> selectRecentBlogs();

    List<Blog> selectHotBlogs();

    List<Blog> getBlogsByFilter(BlogFilter blogFilter);

    void addBlog(Blog blog);

    void deleteBlogById(Integer blogId);

    Long getTotalBlogNum();
}
