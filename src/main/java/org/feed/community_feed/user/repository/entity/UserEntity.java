package org.feed.community_feed.user.repository.entity;

import org.feed.community_feed.common.domain.PositiveIntegerCounter;
import org.feed.community_feed.common.repository.entity.TimeBaseEntity;
import org.feed.community_feed.user.domain.User;
import org.feed.community_feed.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jiyoung
 */
@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                   .id(id)
                   .info(new UserInfo(name, profileImage))
                   .followerCount(new PositiveIntegerCounter(followerCount))
                   .followingCount(new PositiveIntegerCounter(followingCount))
                   .build();
    }
}
