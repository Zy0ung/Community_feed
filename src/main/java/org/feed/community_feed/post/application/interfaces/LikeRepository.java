package org.feed.community_feed.post.application.interfaces;

import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.user.domain.User;

/**
 * @author jiyoung
 */
public interface LikeRepository {

    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unLike(Post post, User user);

    boolean checkLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unLike(Comment comment, User user);
}
