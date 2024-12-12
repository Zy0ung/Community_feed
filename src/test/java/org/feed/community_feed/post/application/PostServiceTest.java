package org.feed.community_feed.post.application;

import org.feed.community_feed.fake.FakeObjectFactory;
import org.feed.community_feed.post.application.dto.CreatePostRequestDto;
import org.feed.community_feed.post.application.dto.LikeRequestDto;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.content.PostPublicationState;
import org.feed.community_feed.user.application.UserService;
import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jiyoung
 */
class PostServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final PostService postService = FakeObjectFactory.getPostService();

    private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    private final CreatePostRequestDto dto =
            new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savePost = postService.createPost(dto);

        // then
        Post post = postService.getPost(savePost.getId());
        assertEquals(savePost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatePost() {
        // given
        Post savePost = postService.createPost(dto);

        // when
        Post updatedPost = postService.updatePost(savePost.getId(), dto);

        // then
        assertEquals(savePost.getId(), updatedPost.getId());
        assertEquals(savePost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savePost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
        // given
        Post savePost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike() {
        // given
        Post savePost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnLiked_thenReturnPostWithLike() {
        // given
        Post savePost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnLiked_thenReturnPostWithLike() {
        // given
        Post savePost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savePost.getLikeCount());
    }
}
