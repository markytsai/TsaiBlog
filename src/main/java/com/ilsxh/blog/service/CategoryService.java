package com.ilsxh.blog.service;

import com.ilsxh.blog.annotation.Loggable;
import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Category;
import com.ilsxh.blog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Loggable(descpition = "获取热点标签")
    public List<Category> selectTopicList() {
        return categoryMapper.selectTopicList();
    }

    public List<Blog> getBlogListByTopicId(Integer categoryId) {
        List<Blog> blogList = categoryMapper.getBlogListByTopicId(categoryId);
        blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
        return blogList;
    }

    @Loggable(descpition = "获取问题的标签属性", include = "blogId")
    public List<Category> selectRelatedTopics(Integer blogId) {
        List<Category> list = categoryMapper.selectRelatedTopics(blogId);
        return list;
    }
}
