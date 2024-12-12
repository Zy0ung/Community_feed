package org.feed.community_feed.user.application;

import org.feed.community_feed.fake.FakeObjectFactory;
import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jiyoung
 */
class UserServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUSer(){
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();

        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }
}
