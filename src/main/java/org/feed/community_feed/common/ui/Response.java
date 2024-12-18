package org.feed.community_feed.common.ui;

import org.feed.community_feed.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T data) {

    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "ok", value);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}