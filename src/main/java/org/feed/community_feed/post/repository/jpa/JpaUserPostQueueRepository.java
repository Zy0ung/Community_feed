package org.feed.community_feed.post.repository.jpa;

import org.feed.community_feed.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiyoung
 */
public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    void deleteAllByUserIdAndAuthorId(Long userId, Long targetId);
}
