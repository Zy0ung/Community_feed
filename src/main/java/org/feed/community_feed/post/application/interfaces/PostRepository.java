package org.feed.community_feed.post.application.interfaces;

import org.feed.community_feed.post.domain.Post;

import java.util.Optional;

/**
 * @author jiyoung
 */
public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
