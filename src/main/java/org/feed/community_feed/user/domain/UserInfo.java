package org.feed.community_feed.user.domain;

/**
 * @author jiyoung
 */
public class UserInfo {
    private final String name;
    private final String profileImage;

    public UserInfo(String name, String profileImage) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.profileImage = profileImage;
    }
}
