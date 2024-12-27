package org.feed.community_feed.post.repository;

import org.feed.community_feed.post.application.interfaces.PostRepository;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.repository.entity.post.PostEntity;
import org.feed.community_feed.post.repository.jpa.JpaPostRepository;
import org.feed.community_feed.post.repository.post_queue.UserPostQueueCommandRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    @Transactional
    public Post save(Post post) {

        PostEntity postEntity = new PostEntity(post);
        if (post.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }
        postEntity = jpaPostRepository.save(postEntity);
        userPostQueueCommandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
