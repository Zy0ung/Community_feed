package org.feed.community_feed.common.domain;

/**
 * @author jiyoung
 */
public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter(int count) {
        this.count = count;
    }

    public PositiveIntegerCounter() {
        count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}