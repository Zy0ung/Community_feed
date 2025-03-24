package org.feed.community_feed.acceptance.utils;

import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.feed.community_feed.acceptance.post.UserAcceptanceSteps.createUser;
import static org.feed.community_feed.acceptance.post.UserAcceptanceSteps.followUser;

@Component
public class DataLoader {

    public void loadData() {
        // user 1, 2, 3 생성 및 user 1 follow user 2, user 3
        CreateUserRequestDto user = new CreateUserRequestDto("user", null);
        createUser(user);
        createUser(user);
        createUser(user);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));
    }
}