package org.feed.community_feed.common;

import org.feed.community_feed.post.application.CommentService;
import org.feed.community_feed.post.application.PostService;
import org.feed.community_feed.post.application.interfaces.CommentRepository;
import org.feed.community_feed.post.application.interfaces.LikeRepository;
import org.feed.community_feed.post.application.interfaces.PostRepository;
import org.feed.community_feed.post.repository.FakeCommentRepository;
import org.feed.community_feed.post.repository.FakeLikeRepository;
import org.feed.community_feed.post.repository.FakePostRepository;
import org.feed.community_feed.user.application.UserRelationService;
import org.feed.community_feed.user.application.UserService;
import org.feed.community_feed.user.application.interfaces.UserRelationRepository;
import org.feed.community_feed.user.application.interfaces.UserRepository;
import org.feed.community_feed.user.repository.FakeUserRelationRepository;
import org.feed.community_feed.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
