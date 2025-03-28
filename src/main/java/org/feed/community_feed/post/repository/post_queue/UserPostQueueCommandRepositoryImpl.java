package org.feed.community_feed.post.repository.post_queue;

import org.feed.community_feed.post.repository.entity.post.PostEntity;
import org.feed.community_feed.post.repository.entity.post.UserPostQueueEntity;
import org.feed.community_feed.post.repository.jpa.JpaPostRepository;
import org.feed.community_feed.post.repository.jpa.JpaUserPostQueueRepository;
import org.feed.community_feed.user.repository.entity.UserEntity;
import org.feed.community_feed.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntityList = followersIds.stream()
                                                                        .map(userId -> new UserPostQueueEntity(userId,
                                                                                postEntity.getId(), userEntity.getId()))
                                                                        .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIdList = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntityList =
                postIdList.stream().map(postId -> new UserPostQueueEntity(userId, postId, targetId)).toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);

    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
