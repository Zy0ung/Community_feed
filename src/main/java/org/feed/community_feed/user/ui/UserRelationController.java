package org.feed.community_feed.user.ui;

import org.feed.community_feed.common.ui.Response;
import org.feed.community_feed.user.application.UserRelationService;
import org.feed.community_feed.user.application.dto.FollowUserRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private static final Logger log = LoggerFactory.getLogger(UserRelationController.class);
    private final UserRelationService relationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        relationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        relationService.unFollow(dto);
        return Response.ok(null);
    }
}