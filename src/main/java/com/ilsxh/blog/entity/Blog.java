package com.ilsxh.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Blog {


        /** 自增 */
        private Integer blogId;

        /** 博客标题 */
        private String blogTitle;

        /** 博客简介/摘要 */
        private String blogSummary;

        /**  */
        private String blogKeyword;

        /** 封面缩略图等 */
        private String blogImages;

        /** 点击量 */
        private Integer blogClicknum;

        /** 评论量 */
//        private Integer commentnum;

        /** 点赞量 */
//        private Integer agreenum;

        /** 1置顶，0普通 */
//        private Integer istop;

        /** 博主推荐,1为推荐，0为普通 */
//        private Integer isrecommend;

        /**  */
        @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
        private Date blogUpdateTime;

        /**  */
        @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
        private Date blogAddTime;

        /** -1为草稿，1为正文 ，2为回收站*/
//        private Integer status;

        /** 类别 */

//        private BlogType type;

        /** 博客内容 */
        private String blogContent;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSummary() {
        return blogSummary;
    }

    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary;
    }

    public String getBlogKeyword() {
        return blogKeyword;
    }

    public void setBlogKeyword(String blogKeyword) {
        this.blogKeyword = blogKeyword;
    }

    public String getBlogImages() {
        return blogImages;
    }

    public void setBlogImages(String blogImages) {
        this.blogImages = blogImages;
    }

    public Integer getBlogClicknum() {
        return blogClicknum;
    }

    public void setBlogClicknum(Integer blogClicknum) {
        this.blogClicknum = blogClicknum;
    }

    public Date getBlogUpdateTime() {
        return blogUpdateTime;
    }

    public void setBlogUpdateTime(Date blogUpdateTime) {
        this.blogUpdateTime = blogUpdateTime;
    }

    public Date getBlogAddTime() {
        return blogAddTime;
    }

    public void setBlogAddTime(Date blogAddTime) {
        this.blogAddTime = blogAddTime;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}
