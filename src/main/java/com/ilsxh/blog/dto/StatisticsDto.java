package com.ilsxh.blog.dto;

public class StatisticsDto {

    /**
     * 文章数
     */
    private Long BlogNum;

    /**
     * 评论数
     */
    private Long commentNum;

    /**
     * 链接数
     */
    private Long linkNum;

    /**
     * 文件数
     */
    private Long attachFileNum;

    public Long getBlogNum() {
        return BlogNum;
    }

    public void setBlogNum(Long blogNum) {
        BlogNum = blogNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Long getLinkNum() {
        return linkNum;
    }

    public void setLinkNum(Long linkNum) {
        this.linkNum = linkNum;
    }

    public Long getAttachFileNum() {
        return attachFileNum;
    }

    public void setAttachFileNum(Long attachFileNum) {
        this.attachFileNum = attachFileNum;
    }
}
