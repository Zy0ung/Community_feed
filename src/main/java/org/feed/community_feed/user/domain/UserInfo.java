package org.feed.community_feed.user.domain;

import lombok.Getter;

/**
 * @author jiyoung
 */
@Getter
public class UserInfo {
    private String name;
    private String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
