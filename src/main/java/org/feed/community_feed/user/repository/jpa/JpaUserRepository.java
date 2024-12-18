package org.feed.community_feed.user.repository.jpa;

import org.feed.community_feed.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiyoung
 */
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
