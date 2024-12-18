package org.feed.community_feed.common.domain.exception;

import lombok.Getter;

/**
 * @author jiyoung
 */
@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "invalid_input_value"),
    NOT_FOUND(404, "not_found"),
    INTERNAL_SERVER_ERROR(500, "internal_server_error");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
