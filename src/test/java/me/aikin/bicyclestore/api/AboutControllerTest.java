package me.aikin.bicyclestore.api;


import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class AboutControllerTest extends ApiBaseTest {

    @Test
    public void should_return_correct_content_when_get_about() {
        given().

        when().
                get("/api/about").
        then().
                body(equalTo("aikin: about."));
    }

}
