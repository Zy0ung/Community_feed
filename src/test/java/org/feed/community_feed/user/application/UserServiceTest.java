package org.feed.community_feed.user.application;

import org.feed.community_feed.common.FakeObjectFactory;
import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDtoWhenCreateUserThenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("John", "www.naver.com");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        assertEquals(savedUser.getId(), foundUser.getId());
    }
}
