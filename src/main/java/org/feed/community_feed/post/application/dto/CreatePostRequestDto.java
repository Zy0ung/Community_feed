package org.feed.community_feed.post.application.dto;

import org.feed.community_feed.post.domain.content.PostPublicationState;

/**
 * @author jiyoung
 */
public record CreatePostRequestDto (Long userId, String content, PostPublicationState state) {
}
