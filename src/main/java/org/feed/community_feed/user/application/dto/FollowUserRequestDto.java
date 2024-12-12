package org.feed.community_feed.user.application.dto;

public record FollowUserRequestDto(Long userId, Long targetUserId) {
}
