package org.feed.community_feed.user.repository;

import org.feed.community_feed.post.repository.post_queue.UserPostQueueCommandRepository;
import org.feed.community_feed.user.application.interfaces.UserRelationRepository;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.repository.entity.UserEntity;
import org.feed.community_feed.user.repository.entity.UserRelationEntity;
import org.feed.community_feed.user.repository.entity.UserRelationIdEntity;
import org.feed.community_feed.user.repository.jpa.JpaUserRelationRepository;
import org.feed.community_feed.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUSer) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUSer.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
