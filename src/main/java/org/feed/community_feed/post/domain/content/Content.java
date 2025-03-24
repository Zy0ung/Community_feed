package org.feed.community_feed.post.domain.content;

import org.feed.community_feed.post.domain.common.DateTimeInfo;

/**
 * @author jiyoung
 */
public abstract class Content {
    protected String contentText;
    protected final DateTimeInfo updateTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.updateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        this.updateTimeInfo.updateEditDateTime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }

    public boolean isEdited() {
        return updateTimeInfo.isEdited();
    }
}
