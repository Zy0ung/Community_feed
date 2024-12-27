package org.feed.community_feed.post.repository.post_queue;

import org.feed.community_feed.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

/**
 * @author jiyoung
 */
public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
