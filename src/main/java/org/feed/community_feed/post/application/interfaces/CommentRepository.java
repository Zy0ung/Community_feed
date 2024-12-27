package org.feed.community_feed.post.application.interfaces;

import org.feed.community_feed.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);
}
