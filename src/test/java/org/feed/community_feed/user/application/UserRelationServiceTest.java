package org.feed.community_feed.user.application;

import org.feed.community_feed.common.FakeObjectFactory;
import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.application.dto.FollowUserRequestDto;
import org.feed.community_feed.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();
    private User user1;
    private User user2;
    private FollowUserRequestDto relationDto;

    @BeforeEach
    void setUp() {
        // given
        CreateUserRequestDto req = new CreateUserRequestDto("Doe", "");
        this.user1 = userService.createUser(req);
        this.user2 = userService.createUser(req);
        this.relationDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }


    @Test
    void givenCreateTwoUserWhenFollowThenUserFollowOtherUser() {
        // when
        userRelationService.follow(relationDto);

        // then
        assertEquals(1, user1.getFollowingCount().getCount());
        assertEquals(1, user2.getFollowerCount().getCount());
    }

    @Test
    void givenFollowUserWhenFollowAgainThenThrowException() {
        // given
        userRelationService.follow(relationDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(relationDto));
    }

    @Test
    void givenFollowUserWhenFollowSelfThenThrowException() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenFollowUserWhenUnfollowThenUserUnfollowOtherUser() {
        // given
        userRelationService.follow(relationDto);

        // when
        userRelationService.unFollow(relationDto);

        // then
        assertEquals(0, user1.getFollowingCount().getCount());
        assertEquals(0, user2.getFollowerCount().getCount());
    }

    @Test
    void givenUnfollowUserWhenUnfollowAgainThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(relationDto));
    }

    @Test
    void givenUnfollowUserWhenUnfollowSelfThenThrowException() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(sameUser));
    }
}
