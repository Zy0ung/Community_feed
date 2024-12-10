package org.feed.community_feed.user.domain;

/**
 * @author jiyoung
 */
public class UserRelationCounter {
    private int count;

    public UserRelationCounter(){
        count = 0;
    }

    public void increase(){
        this.count++;
    }

    public void decrease(){
        if(count < 0){
            return;
        }
        this.count --;
    }
}
