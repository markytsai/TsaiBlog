package com.ilsxh.blog.entity;

public class Link {
    private Integer linkId;
    private String linkName;
    private String linkUrl;
    private String linkLogo;
    private String linkUsername;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkLogo() {
        return linkLogo;
    }

    public void setLinkLogo(String linkLogo) {
        this.linkLogo = linkLogo;
    }

    public String getLinkUsername() {
        return linkUsername;
    }

    public void setLinkUsername(String linkUsername) {
        this.linkUsername = linkUsername;
    }
}
