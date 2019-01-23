package com.ilsxh.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ilsxh.blog.entity.LogMessage;
import com.ilsxh.blog.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    public void addLog(String action, String data, int ip, Integer authorId) {
        LogMessage log = new LogMessage();
        log.setLogId(authorId);
        log.setLogId(ip);
        log.setLogDesc(action);
        logMapper.insertLog(log);
    }

    public PageInfo<LogMessage> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogMessage> logs = logMapper.getLogs();
        PageInfo<LogMessage> pageInfo = new PageInfo<>(logs);
        return pageInfo;

    }
}
