package org.feed.community_feed.post.repository.post_queue;

import org.feed.community_feed.post.repository.entity.post.PostEntity;

/**
 * @author jiyoung
 */
public interface UserPostQueueCommandRepository {

    void publishPost(PostEntity postEntity);

    void saveFollowPost(Long userId, Long targetId);

    void deleteUnfollowPost(Long userId, Long targetId);
}