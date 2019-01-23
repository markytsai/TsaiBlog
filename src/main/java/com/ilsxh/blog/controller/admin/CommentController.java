package com.ilsxh.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ilsxh.blog.dto.CommentFilter;
import com.ilsxh.blog.entity.Comment;
import com.ilsxh.blog.service.CommentService;
import com.ilsxh.blog.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public String toComment(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "limit", required = false, defaultValue = "15") int limit,
                            HttpServletRequest request) {
        CommentFilter commentFilter = new CommentFilter();
        PageInfo<Comment> comments = commentService.getCommentsByCommentFilter(commentFilter, page, limit);
        request.setAttribute("comments", comments);
        return "admin/comment_list";
    }

    @PostMapping("/comment/delete")
    @ResponseBody
    public Response deleteComment(HttpServletRequest request, @RequestParam(name = "commentId", required = true) Integer commentId) {
        try {
            Comment comment = commentService.getCommentById(commentId);
            if (null != comment) {
                commentService.deleteComment(commentId);
            } else {
                return Response.fail("通过失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail(e.getMessage());
        }
        return Response.success();
    }

    @PostMapping(value = "/comment/status")
    @ResponseBody
    public Response changeStatus(HttpServletRequest request, @RequestParam(name = "commentId", required = true) Integer commentId,
                                 @RequestParam(name = "commentStatus", required = true) String commentStatus) {
        try {
            Comment comment = commentService.getCommentById(commentId);
            if (null != comment) {
                commentService.updateCommentStatus(commentId, commentStatus);
            } else {
                return Response.fail("通过失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail(e.getMessage());
        }
        return Response.success();
    }
}
