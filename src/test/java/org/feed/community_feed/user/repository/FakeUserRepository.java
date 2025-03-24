package org.feed.community_feed.user.repository;


import org.feed.community_feed.user.application.interfaces.UserRepository;
import org.feed.community_feed.user.domain.User;

import java.util.HashMap;
import java.util.Map;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            store.put(user.getId(), user);
            return user;
        }
        long id = store.size() + 1;
        User newUser = new User(id, user.getInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
