package org.feed.community_feed.user.repository;

import org.feed.community_feed.user.application.interfaces.UserRelationRepository;
import org.feed.community_feed.user.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jiyoung
 */
public class FakeUserRelationRepository implements UserRelationRepository {
    private final Set<Relation> store = new HashSet<>();

    @Override
    public boolean isAlreadyFollow(User user, User targetUSer) {
        return store.contains(new Relation(user.getId(), targetUSer.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        store.add(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User user, User targetUser) {
        store.remove(new Relation(user.getId(), targetUser.getId()));
    }
}

record Relation(Long userId, Long targetUserId){}
