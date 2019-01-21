package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {

    List<Topic> selectTopicList();

    Topic selectTopicByTopicId(Integer topicId);

    List<Blog> getBlogListByTopicId(Integer topicId);

    List<Topic> selectRelatedTopics(Integer blogId);
}
