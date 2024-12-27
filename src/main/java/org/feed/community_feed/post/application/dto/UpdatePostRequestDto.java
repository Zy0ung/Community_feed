package org.feed.community_feed.post.application.dto;

import org.feed.community_feed.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
