package com.ilsxh.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ilsxh.blog.dto.BlogFilter;
import com.ilsxh.blog.dto.CommentFilter;
import com.ilsxh.blog.entity.Comment;
import com.ilsxh.blog.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public PageInfo<Comment> getCommentsByCommentFilter(CommentFilter commentFilter, int pageNum, int pageSize) {
        if (null == commentFilter) {
            throw new RuntimeException("内部错误");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.getCommentsByCommentFilter();
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return pageInfo;
    }

    public Comment getCommentById(Integer commentId) {
        if (null == commentId) {
            throw new RuntimeException("内部错误");
        }
        return commentMapper.getCommentById(commentId);
    }

    public void updateCommentStatus(Integer commentId, String commentStatus) {
        if (null == commentId) {
            throw new RuntimeException("内部错误");
        }
        commentMapper.updateCommentStatus(commentId);
    }

    public void deleteComment(Integer commentId) {
        if (null == commentId) {
            throw new RuntimeException("内部错误");
        }
        commentMapper.deleteCommentById(commentId);
    }
}
