package org.feed.community_feed.post.repository;

import org.feed.community_feed.post.application.interfaces.PostRepository;
import org.feed.community_feed.post.domain.Post;

import java.util.HashMap;
import java.util.Map;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> store = new HashMap<>();


    @Override
    public Post findById(Long id) {
        return store.get(id);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }

        long id = store.size() + 1;
        Post newPost = new Post(id, post.getAuthor(), post.getContentObject());
        store.put(id, newPost);
        return newPost;
    }

    public Post publish(Post post) {
        return save(post);
    }
}
