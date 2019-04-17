package com.ilsxh.blog.service;

import com.ilsxh.blog.annotation.Loggable;
import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Topic;
import com.ilsxh.blog.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Loggable(descpition = "获取热点标签")
    public List<Topic> selectTopicList() {
        return topicMapper.selectTopicList();
    }

    public List<Blog> getBlogListByTopicId(Integer topicId) {
        List<Blog> blogList = topicMapper.getBlogListByTopicId(topicId);
        blogList.stream().forEach(blog -> blog.setBlogUrl(blog.getBlogTitle().replace(" ", "-")));
        return blogList;
    }

    @Loggable(descpition = "获取问题的标签属性", include = "blogId")
    public List<Topic> selectRelatedTopics(Integer blogId) {
        List<Topic> list = topicMapper.selectRelatedTopics(blogId);
        return list;
    }
}
