package com.ilsxh.blog.service.admin;

import com.github.pagehelper.PageHelper;
import com.ilsxh.blog.dto.BlogFilter;
import com.ilsxh.blog.dto.StatisticsDto;
import com.ilsxh.blog.entity.Blog;
import com.ilsxh.blog.entity.Comment;
import com.ilsxh.blog.mapper.BlogMapper;
import com.ilsxh.blog.mapper.CommentMapper;
import com.ilsxh.blog.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SiteService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 获取评论列表
     *
     * @param limit
     * @return
     */
    public List<Comment> getComments(int limit) {
        List<Comment> commentList = new ArrayList<>(1);
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setCommentUrl("https://www.baidu.com");
        comment.setCommentTime(new Timestamp(new Date().getTime()));
        comment.setCommentContent("真的是太棒啦！！！");
        commentList.add(comment);
        return commentList;
    }

    /**
     * 获取文章列表
     *
     * @param limit
     * @return
     */
    public List<Blog> getNewBlogs(int limit) {
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        // 设置检索条件
        BlogFilter blogFilter = new BlogFilter();
        PageHelper.startPage(1, limit);
        List<Blog> blogList = blogMapper.getBlogsByFilter(blogFilter);
        return blogList;
    }

    /**
     * 获取后台统计数
     *
     * @return
     */
    public StatisticsDto getStatistics() {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setBlogNum(blogMapper.getTotalBlogNum());
        statisticsDto.setCommentNum(commentMapper.getTotalCommentNum());
        statisticsDto.setAttachFileNum(1L);
        statisticsDto.setLinkNum(linkMapper.getTotalLinkNum());
        return statisticsDto;
    }
}
