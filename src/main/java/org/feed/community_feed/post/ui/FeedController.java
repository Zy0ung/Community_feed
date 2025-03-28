package org.feed.community_feed.post.ui;

import lombok.RequiredArgsConstructor;
import org.feed.community_feed.common.ui.Response;
import org.feed.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.feed.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jiyoung
 */
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId,
                                                                 Long lastPostId) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(userId, lastPostId);
        return Response.ok(result);
    }
}
