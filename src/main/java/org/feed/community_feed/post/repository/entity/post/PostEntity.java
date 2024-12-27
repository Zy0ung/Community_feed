package org.feed.community_feed.post.repository.entity.post;

import org.feed.community_feed.common.domain.PositiveIntegerCounter;
import org.feed.community_feed.common.repository.entity.TimeBaseEntity;
import org.feed.community_feed.post.domain.Post;
import org.feed.community_feed.post.domain.content.PostContent;
import org.feed.community_feed.post.domain.content.PostPublicationState;
import org.feed.community_feed.user.repository.entity.UserEntity;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jiyoung
 */
@Entity
@Table(name = "community_user_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // ddl 생성시 FK 제한
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    private Integer likeCount;

    @ColumnDefault("0")
    private int commentCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost() {
        return Post.builder()
                   .id(id)
                   .author(author.toUser())
                   .content(new PostContent(content))
                   .state(state)
                   .likeCount(new PositiveIntegerCounter(likeCount))
                   .build();

    }
}
