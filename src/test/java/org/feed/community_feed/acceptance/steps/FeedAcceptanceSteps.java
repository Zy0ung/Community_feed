package org.feed.community_feed.acceptance.steps;

import io.restassured.RestAssured;
import org.feed.community_feed.post.application.dto.CreatePostRequestDto;
import org.feed.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * @author jiyoung
 */
public class FeedAcceptanceSteps {

    // io.restassured.response.Response
    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    public static List<GetPostContentResponseDto> requestFeedList(Long requestUserId) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed/{userId}", requestUserId)
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }
}
