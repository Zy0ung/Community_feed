package org.feed.community_feed.user.application.interfaces;

import org.feed.community_feed.user.domain.User;

import java.util.Optional;

/**
 * @author jiyoung
 */
public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
}
