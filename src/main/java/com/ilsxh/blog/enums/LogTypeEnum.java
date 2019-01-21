package com.ilsxh.blog.enums;
public enum LogTypeEnum {

    WEB("1"),
    DUBBO("2"),
    MQ("3");

    private final String value;

    LogTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
