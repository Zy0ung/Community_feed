package org.feed.community_feed.post.application;

import org.feed.community_feed.post.application.dto.CreateCommentRequestDto;
import org.feed.community_feed.post.application.dto.LikeRequestDto;
import org.feed.community_feed.post.application.dto.UpdateCommentRequestDto;
import org.feed.community_feed.post.application.interfaces.CommentRepository;
import org.feed.community_feed.post.application.interfaces.LikeRepository;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.user.application.UserService;
import org.feed.community_feed.user.domain.User;

/**
 * @author jiyoung
 */
public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository,
            LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CreateCommentRequestDto dto){
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.Content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto){
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto){
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unLikeComment(LikeRequestDto dto){
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            comment.unlike();
            likeRepository.unLike(comment, user);
        }
    }
}