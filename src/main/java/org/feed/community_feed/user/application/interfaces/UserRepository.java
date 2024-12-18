package org.feed.community_feed.user.application.interfaces;

import org.feed.community_feed.user.domain.User;

/**
 * @author jiyoung
 */
public interface UserRepository {

    User save(User user);

    User findById(Long id);
}
