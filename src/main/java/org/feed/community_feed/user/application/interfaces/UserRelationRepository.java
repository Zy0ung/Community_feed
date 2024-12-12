package org.feed.community_feed.user.application.interfaces;

import org.feed.community_feed.user.domain.User;

/**
 * @author jiyoung
 */
public interface UserRelationRepository {
    boolean isAlreadyFollow(User user, User targetUSer);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);
}