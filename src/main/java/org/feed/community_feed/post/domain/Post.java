package org.feed.community_feed.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.feed.community_feed.common.domain.PositiveIntegerCounter;
import org.feed.community_feed.post.domain.content.Content;
import org.feed.community_feed.post.domain.content.PostContent;
import org.feed.community_feed.post.domain.content.PostPublicationState;
import org.feed.community_feed.user.domain.User;

/**
 * @author jiyoung
 */
@Getter
@Builder
@AllArgsConstructor
public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private PostPublicationState state;
    private final PositiveIntegerCounter likeCount;

    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public Post(Long id, User author, String content) {
        this(id, author, new PostContent(content), PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }


    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
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

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public Content getContentObject() {
        return content;
    }

    public PostPublicationState getState() {
        return state;
    }
}