package org.feed.community_feed.post.repository.entity.like;

import org.feed.community_feed.common.repository.entity.TimeBaseEntity;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.user.domain.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jiyoung
 */
@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser) {
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser) {
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }
}