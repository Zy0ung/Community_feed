package org.feed.community_feed.post.domain;

import org.feed.community_feed.post.domain.content.PostContent;
import org.feed.community_feed.post.domain.content.PostPublicationState;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author jiyoung
 */
class PostTest {

    private final UserInfo userInfo = new UserInfo("name", "url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1(){
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeByOtherUser_thenThrowException(){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnLike_thenLikeCountShouldBe0(){
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0(){
        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated(){
        // given
        String newPostContent = "new content";

        // when
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        // then
        assertEquals(newPostContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateOutherUser_thenThrowException(){
        // given
        String newPostContent = "new content";

        // when, then
        assertThrows(IllegalArgumentException.class, ()-> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }
}
