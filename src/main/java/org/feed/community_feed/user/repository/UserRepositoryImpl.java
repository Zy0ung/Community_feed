package org.feed.community_feed.user.repository;

import org.feed.community_feed.user.application.interfaces.UserRepository;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.repository.entity.UserEntity;
import org.feed.community_feed.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return entity.toUser();
    }
}