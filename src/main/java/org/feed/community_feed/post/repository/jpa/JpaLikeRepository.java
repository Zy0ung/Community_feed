package org.feed.community_feed.post.repository.jpa;

import org.feed.community_feed.post.repository.entity.like.LikeEntity;
import org.feed.community_feed.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiyoung
 */
public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
