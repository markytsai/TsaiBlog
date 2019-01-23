package com.ilsxh.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Integer selectLoginUser(String username, String password);
}
