package org.feed.community_feed.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String Content) {
}