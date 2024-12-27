package org.feed.community_feed.post.application;

import org.feed.community_feed.fake.FakeObjectFactory;
import org.feed.community_feed.post.application.dto.CreateCommentRequestDto;
import org.feed.community_feed.post.application.dto.CreatePostRequestDto;
import org.feed.community_feed.post.application.dto.LikeRequestDto;
import org.feed.community_feed.post.application.dto.UpdateCommentRequestDto;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.post.domain.content.PostPublicationState;
import org.feed.community_feed.user.application.UserService;
import org.feed.community_feed.user.application.dto.CreateUserRequestDto;
import org.feed.community_feed.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jiyoung
 */
class CommentServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final PostService postService = FakeObjectFactory.getPostService();
    private final CommentService commentService = FakeObjectFactory.getCommentService();

    private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    private final CreatePostRequestDto createPostRequestDto =
            new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);

    final Post post = postService.createPost(createPostRequestDto);

    final String commentContentText = "this is test comment";

    private final CreateCommentRequestDto createCommentRequestDto =
            new CreateCommentRequestDto(post.getId(), user.getId(), commentContentText);


    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        // when
        Comment comment = commentService.createComment(createCommentRequestDto);

        // then
        String content = comment.getContentText();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        // given
        Comment comment = commentService.createComment(createCommentRequestDto);

        // when
        UpdateCommentRequestDto updateCommentRequestDto =
                new UpdateCommentRequestDto(comment.getId(), user.getId(), "updated-content");
        Comment updatedComment = commentService.updateComment(comment.getId(), updateCommentRequestDto);

        // then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(comment.getContentText(), updatedComment.getContentText());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike() {
        // given
        Comment comment = commentService.createComment(createCommentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnLikeComment_thenReturnCommentWithoutLike() {
        // given
        Comment comment = commentService.createComment(createCommentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unLikeComment(likeRequestDto);

        //then
        assertEquals(0, comment.getLikeCount());
    }
}
