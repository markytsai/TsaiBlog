package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {

    Blog selectBlogByBlogId(Integer blogId);

    List<Blog> selectBlogList();

    List<Blog> selectRecentBlogs();

    List<Blog> selectHotBlogs();
}
