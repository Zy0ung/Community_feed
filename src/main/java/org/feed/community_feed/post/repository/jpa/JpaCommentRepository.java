package org.feed.community_feed.post.repository.jpa;

import org.feed.community_feed.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author jiyoung
 */
public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query("UPDATE CommentEntity c " + "SET c.likeCount = :#{#comment.getLikeCount()} " +
            "WHERE c.id = :#{#comment.getId()}")
    void updateLikeCount(CommentEntity comment);

    @Modifying
    @Query("UPDATE CommentEntity c " + "SET c.content = :#{#comment.getContentText()}," + "c.updDt = now() " +
            "WHERE c.id = :#{#comment.getId()}")
    void updateCommentEntity(CommentEntity comment);
}
