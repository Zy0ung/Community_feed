package org.feed.community_feed.post.repository;

import org.feed.community_feed.post.application.interfaces.CommentRepository;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.post.repository.entity.comment.CommentEntity;
import org.feed.community_feed.post.repository.jpa.JpaCommentRepository;
import org.feed.community_feed.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);
        if (comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return comment;
        }
        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
