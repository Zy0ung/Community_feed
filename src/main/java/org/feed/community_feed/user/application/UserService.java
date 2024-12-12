package org.feed.community_feed.user.application;

import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.application.interfaces.UserRepository;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.domain.UserInfo;

/**
 * @author jiyoung
 */
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

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
