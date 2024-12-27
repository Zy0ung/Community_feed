package org.feed.community_feed.post.domain.comment;

import org.feed.community_feed.common.domain.PositiveIntegerCounter;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.content.CommentContent;
import org.feed.community_feed.post.domain.content.Content;
import org.feed.community_feed.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author jiyoung
 */
@Builder
@AllArgsConstructor
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, String content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }
        if (post == null) {
            throw new IllegalArgumentException();
        }
        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = new CommentContent(content);
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void updateComment(User user, String updatedContent) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updatedContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContentText() {
        return content.getContentText();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContentObject() {
        return content;
    }
}
