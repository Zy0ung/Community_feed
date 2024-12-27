package org.feed.community_feed.post.application.interfaces;

import org.feed.community_feed.post.domain.Post;

/**
 * @author jiyoung
 */
public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
