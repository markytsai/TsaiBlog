package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> selectTopicList();

    Category selectTopicByTopicId(Integer categoryId);

    List<Blog> getBlogListByTopicId(Integer categoryId);

    List<Category> selectRelatedTopics(Integer blogId);
}
