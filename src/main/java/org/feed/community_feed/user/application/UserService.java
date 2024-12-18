package org.feed.community_feed.user.application;

import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.application.dto.GetUserResponseDto;
import org.feed.community_feed.user.application.interfaces.UserRepository;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.domain.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author jiyoung
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }
}
