package org.feed.community_feed.user.repository.jpa;

import org.feed.community_feed.user.repository.entity.UserRelationEntity;
import org.feed.community_feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiyoung
 */
public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
