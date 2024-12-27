package org.feed.community_feed.user.repository.jpa;

import org.feed.community_feed.user.repository.entity.UserRelationEntity;
import org.feed.community_feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jiyoung
 */
public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    @Query("SELECT u.followingUserId FROM UserRelationEntity u WHERE u.followerUserId = :userId")
    List<Long> findFollowers(Long userId);
}
