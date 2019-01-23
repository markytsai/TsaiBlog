package com.ilsxh.blog.entity;

import java.sql.Timestamp;

public class Comment {

    /**
     * comment表主键
     */
    private Integer commentId;
    /**
     * contents表主键,关联字段
     */
    private Integer blogId;
    /**
     * 评论生成时的GMT unix时间戳
     */
    private Timestamp commentTime;
    /**
     * 评论作者
     */
//    private String commentAuthor;
    /**
     * 评论所属用户id
     */
//    private String commentAuthorId;
    /**
     * 评论所属内容作者id
     */
    private Integer commentOwnerId;
    /**
     * 评论者邮件
     */
    private String commentEmail;
    /**
     * 评论者网址
     */
    private String commentUrl;
    /**
     * 评论者ip地址
     *
     */
    private String commentIp;
    /**
     * 评论者客户端
     */
    private String commentAgent;
    /**
     * 评论类型
     */
    private Integer commentType;
    /**
     * 评论状态：待审核：0；审核通过：1， 审核不通过：2，通过全局静态变量表示
     */
    private Integer commentStatus;
    /**
     * 父级评论
     */
//    private Integer parent;
    /**
     * 评论内容
     */
    private String commentContent;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentOwnerId() {
        return commentOwnerId;
    }

    public void setCommentOwnerId(Integer commentOwnerId) {
        this.commentOwnerId = commentOwnerId;
    }

    public String getCommentEmail() {
        return commentEmail;
    }

    public void setCommentEmail(String commentEmail) {
        this.commentEmail = commentEmail;
    }

    public String getCommentUrl() {
        return commentUrl;
    }

    public void setCommentUrl(String commentUrl) {
        this.commentUrl = commentUrl;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
