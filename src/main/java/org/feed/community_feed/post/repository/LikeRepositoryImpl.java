package org.feed.community_feed.post.repository;

import org.feed.community_feed.post.application.interfaces.LikeRepository;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.post.repository.entity.like.LikeEntity;
import org.feed.community_feed.post.repository.jpa.JpaCommentRepository;
import org.feed.community_feed.post.repository.jpa.JpaLikeRepository;
import org.feed.community_feed.post.repository.jpa.JpaPostRepository;
import org.feed.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager em;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        em.persist(likeEntity);
        jpaPostRepository.updateLikeCount(post.getId(), 1);
    }

    @Override
    @Transactional
    public void unLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(post.getId(), -1);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        em.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(comment.getId(), 1);
    }

    @Override
    @Transactional
    public void unLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(comment.getId(), -1);
    }
}
