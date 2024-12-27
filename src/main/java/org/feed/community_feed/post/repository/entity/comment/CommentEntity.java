package org.feed.community_feed.post.repository.entity.comment;

import org.feed.community_feed.common.domain.PositiveIntegerCounter;
import org.feed.community_feed.common.repository.entity.TimeBaseEntity;
import org.feed.community_feed.post.domain.comment.Comment;
import org.feed.community_feed.post.domain.content.CommentContent;
import org.feed.community_feed.post.repository.entity.post.PostEntity;
import org.feed.community_feed.user.repository.entity.UserEntity;

import jakarta.persistence.ConstraintMode;
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
@Table(name = "community_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "authorId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "postId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCount;

    public String getContentText() {
        return toComment().getContentText();
    }

    public CommentEntity(Comment comment) {
        this.id = comment.getId();
        this.author = new UserEntity(comment.getAuthor());
        this.post = new PostEntity(comment.getPost());
        this.content = comment.getContentText();
        this.likeCount = comment.getLikeCount();
    }

    public Comment toComment() {
        return Comment.builder()
                      .id(id)
                      .author(author.toUser())
                      .post(post.toPost())
                      .content(new CommentContent(content))
                      .likeCount(new PositiveIntegerCounter(likeCount))
                      .build();

    }
}