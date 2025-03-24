package org.feed.community_feed.post.domain.content;

import org.feed.community_feed.post.domain.common.DateTimeInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DateTimeInfo info = new DateTimeInfo();
        LocalDateTime datetime = info.getDateTime();

        // when
        info.updateEditDateTime();

        // then
        assertNotEquals(datetime, info.getDateTime());
        assertTrue(info.isEdited());
    }

}
