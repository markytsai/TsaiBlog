package com.ilsxh.blog.redis;

public class BlogKey extends BasePrefix {

    private BlogKey(String prefix) {
        super(prefix);
    }

    private BlogKey(String prefix, int expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static final BlogKey blogListKey = new BlogKey("blogListKey");
    public static final BlogKey hotBlogListKey = new BlogKey("hotBlogListKey");


}
