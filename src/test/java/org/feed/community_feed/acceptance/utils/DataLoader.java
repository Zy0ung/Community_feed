package org.feed.community_feed.acceptance.utils;

import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.feed.community_feed.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.feed.community_feed.acceptance.steps.UserAcceptanceSteps.followUser;

/**
 * @author jiyoung
 */
@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
