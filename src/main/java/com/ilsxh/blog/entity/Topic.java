package com.ilsxh.blog.entity;

public class Topic {
    private Integer topicId;
    private String topicName;
    private Integer relateBlogNum;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getRelateBlogNum() {
        return relateBlogNum;
    }

    public void setRelateBlogNum(Integer relateBlogNum) {
        this.relateBlogNum = relateBlogNum;
    }
}
