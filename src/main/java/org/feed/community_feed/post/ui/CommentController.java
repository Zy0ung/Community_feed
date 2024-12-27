package org.feed.community_feed.post.ui;

import org.feed.community_feed.common.ui.Response;
import org.feed.community_feed.post.application.CommentService;
import org.feed.community_feed.post.application.dto.CreateCommentRequestDto;
import org.feed.community_feed.post.application.dto.LikeRequestDto;
import org.feed.community_feed.post.application.dto.UpdateCommentRequestDto;
import org.feed.community_feed.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId,
            @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.unLikeComment(dto);
        return Response.ok(null);
    }
}
