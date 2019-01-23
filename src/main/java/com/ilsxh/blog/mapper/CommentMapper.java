package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentsByCommentFilter();

    Comment getCommentById(Integer commentId);

    void updateCommentStatus(Integer commentId);

    void deleteCommentById(Integer commentId);

    Long getTotalCommentNum();
}
