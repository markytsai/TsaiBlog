package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.LogMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {

    Integer insertLog(@Param("log") LogMessage log);

    List<LogMessage> getLogs();
}
