package org.feed.community_feed.post.repository.entity.post;

import org.feed.community_feed.post.domain.content.PostPublicationState;

import jakarta.persistence.AttributeConverter;

/**
 * @author jiyoung
 */
public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {
    @Override
    public String convertToDatabaseColumn(PostPublicationState postPublicationState) {
        return postPublicationState.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        return PostPublicationState.valueOf(s);
    }
}
