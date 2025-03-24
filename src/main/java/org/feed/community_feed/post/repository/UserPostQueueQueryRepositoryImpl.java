package org.feed.community_feed.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.feed.community_feed.post.repository.entity.like.QLikeEntity;
import org.feed.community_feed.post.repository.entity.post.QPostEntity;
import org.feed.community_feed.post.repository.entity.post.QUserPostQueueEntity;
import org.feed.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.feed.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.feed.community_feed.user.repository.entity.QUserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiyoung
 */
@Repository
@RequiredArgsConstructor
@Primary
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory.select(Projections.fields(GetPostContentResponseDto.class, postEntity.id.as("id"),
                        postEntity.content.as("content"), userEntity.id.as("userId"), userEntity.name.as("userName"),
                        userEntity.profileImage.as("userProfileImage"), postEntity.regDt.as("createdAt"),
                        postEntity.updDt.as("updatedAt"), postEntity.commentCount.as("commentCount"),
                        postEntity.likeCount.as("likeCount"), likeEntity.isNotNull().as("isLikedByMe")))
                .from(userPostQueueEntity)
                .join(postEntity)
                .on(userPostQueueEntity.postId.eq(postEntity.id))
                .join(userEntity)
                .on(userPostQueueEntity.authorId.eq(userEntity.id))
                .leftJoin(likeEntity)
                .on(hasLike(userId))
                .where(userPostQueueEntity.userId.eq(userId), hasLastData(lastContentId))
                .orderBy(userPostQueueEntity.postId.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return postEntity.id.eq(likeEntity.id.targetId)
                .and(likeEntity.id.targetType.eq("POST"))
                .and(likeEntity.id.userId.eq(userId));
    }
}
