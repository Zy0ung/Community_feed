package org.feed.community_feed.post.application;

import org.feed.community_feed.post.application.dto.CreatePostRequestDto;
import org.feed.community_feed.post.application.dto.LikeRequestDto;
import org.feed.community_feed.post.application.dto.UpdatePostRequestDto;
import org.feed.community_feed.post.application.interfaces.LikeRepository;
import org.feed.community_feed.post.application.interfaces.PostRepository;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.user.application.UserService;
import org.feed.community_feed.user.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author jiyoung
 */
@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(null, author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, UpdatePostRequestDto dto) {
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unLike(post, user);
        }
    }
}
