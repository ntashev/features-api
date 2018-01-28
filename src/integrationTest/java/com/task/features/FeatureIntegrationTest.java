package com.task.features;

import com.task.features.rest.dto.AvailableFeaturesDto;
import com.task.features.rest.dto.FeatureForUserRequestDto;
import com.task.features.rest.dto.FeatureRequestDto;
import com.task.features.rest.dto.FeatureResponseDto;
import io.restassured.response.Response;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Features API integration tests.
 */
public class FeatureIntegrationTest extends IntegrationBaseTest {

    private static final String FEATURES_PATH = "/features/";

    //Get enabled features tests
    @Test
    public void testGetAllFeatures() {
        given().when().get(FEATURES_PATH)
                .then().statusCode(200).body("$", hasKey("enabledFeatures"));
    }

    @Test
    public void testGetAllFeaturesFindOneCreated() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("enabledFeature" + randomString);
        given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH)
                .then().statusCode(201);

        AvailableFeaturesDto responseBody = getAvailableFeaturesDto();

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("enabledFeature" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));
    }

    //Create features tests
    @Test
    public void testCreateFeatureSuccess() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("newlyCreatedFeature" + randomString);
        given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH)
                .then().statusCode(201).header("Location", containsString("api/v1/features/"));

        AvailableFeaturesDto responseBody = getAvailableFeaturesDto();

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("newlyCreatedFeature" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));
    }

    @Test
    public void testCreateFeatureMissingName() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH)
                .then().statusCode(400).body("errorMessage", equalTo("'name' may not be null"));
    }

    @Test
    public void testCreateFeatureEmptyName() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("");
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH)
                .then().statusCode(400)
                .body("errorMessage", equalTo("'name' length must be between 2 and 30"));
    }

    @Test
    public void testCreateFeatureLongerThan30Name() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH)
                .then().statusCode(400).body("errorMessage", equalTo("'name' length must be between 2 and 30"));
    }

    @Test
    public void testCreateFeatureMissingEnabled() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("name");
        dto.setEnabled(null);
        given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH)
                .then().statusCode(400).body("errorMessage", equalTo("'enabled' may not be null"));
    }

    //Update features tests
    @Test
    public void testUpdateFeatureNameSuccess() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("newFeature" + randomString);
        String id = createNewFeature(dto);

        dto.setName("updatedName" + randomString);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", id)
                .when().put(FEATURES_PATH + "{featureId}").then()
                .statusCode(204);

        AvailableFeaturesDto availableFeaturesDto = getAvailableFeaturesDto();

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("updatedName" + randomString);

        assertTrue(availableFeaturesDto.getEnabledFeatures().contains(expected));
    }

    @Test
    public void testUpdateFeatureDisableGlobally() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("disabledFeature" + randomString);
        String id = createNewFeature(dto);

        AvailableFeaturesDto responseBody = getAvailableFeaturesDto();

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("disabledFeature" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));

        dto.setEnabled(false);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", id)
                .when().put(FEATURES_PATH + "{featureId}").then()
                .statusCode(204);

        AvailableFeaturesDto availableFeaturesDto = getAvailableFeaturesDto();

        assertFalse(availableFeaturesDto.getEnabledFeatures().contains(expected));
    }

    @Test
    public void testUpdateFeatureEnableGlobally() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(false);
        dto.setName("enabledFeature" + randomString);
        String id = createNewFeature(dto);

        AvailableFeaturesDto responseBody = getAvailableFeaturesDto();

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("enabledFeature" + randomString);

        assertFalse(responseBody.getEnabledFeatures().contains(expected));

        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", id)
                .when().put(FEATURES_PATH + "{featureId}").then()
                .statusCode(204);

        AvailableFeaturesDto availableFeaturesDto = getAvailableFeaturesDto();

        assertTrue(availableFeaturesDto.getEnabledFeatures().contains(expected));
    }

    @Test
    public void testUpdateFeatureInvalidId() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("name");
        dto.setEnabled(null);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", 0)
                .when().put(FEATURES_PATH + "{featureId}").then()
                .statusCode(400).body("errorMessage", containsString("must be greater than or equal to 1"));
    }

    @Test
    public void testUpdateFeatureIdNotFound() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("name");
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", 9999999)
                .when().put(FEATURES_PATH + "{featureId}").then()
                .statusCode(404).body("errorMessage", containsString("Feature not found"));
    }

    @Test
    public void testUpdateFeatureMissingName() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", 1)
                .when().put(FEATURES_PATH + "{featureId}")
                .then().statusCode(400).body("errorMessage", equalTo("'name' may not be null"));
    }

    @Test
    public void testUpdateFeatureEmptyName() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("");
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", 1)
                .when().put(FEATURES_PATH + "{featureId}")
                .then().statusCode(400).body("errorMessage", equalTo("'name' length must be between 2 and 30"));
    }

    @Test
    public void testUpdateFeatureLongerThan30Name() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        dto.setEnabled(true);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", 1)
                .when().put(FEATURES_PATH + "{featureId}")
                .then().statusCode(400).body("errorMessage", equalTo("'name' length must be between 2 and 30"));
    }

    @Test
    public void testUpdateFeatureMissingEnabled() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setName("name");
        dto.setEnabled(null);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("featureId", 1)
                .when().put(FEATURES_PATH + "{featureId}").then()
                .statusCode(400).body("errorMessage", equalTo("'enabled' may not be null"));
    }


    //Delete features tests
    @Test
    public void testDeleteFeatureInvalidId() {
        given().contentType(MediaType.APPLICATION_JSON).pathParam("featureId", 0)
                .when().delete(FEATURES_PATH + "{featureId}").then()
                .statusCode(400).body("errorMessage", containsString("must be greater than or equal to 1"));
    }

    @Test
    public void testDeleteFeature() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("featureToDelete" + randomString);
        String id = createNewFeature(dto);

        AvailableFeaturesDto responseBody = getAvailableFeaturesDto();

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("featureToDelete" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));

        given().contentType(MediaType.APPLICATION_JSON).pathParam("featureId", id)
                .when().delete(FEATURES_PATH + "{featureId}").then()
                .statusCode(204);

        responseBody = getAvailableFeaturesDto();

        assertFalse(responseBody.getEnabledFeatures().contains(expected));
    }



    //Enables/disables a features for user tests
    @Test
    public void testEnableFeatureForUserInvalidId() {
        FeatureForUserRequestDto dto = new FeatureForUserRequestDto();
        dto.setEnabled(true);
        dto.setFeatureId(1);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("userId", 0)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(400).body("errorMessage", containsString("must be greater than or equal to 1"));
    }

    @Test
    public void testEnableFeatureForUserMisingEnabled() {
        FeatureForUserRequestDto dto = new FeatureForUserRequestDto();
        dto.setEnabled(null);
        dto.setFeatureId(1);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(400).body("errorMessage", equalTo("'enabled' may not be null"));
    }
    @Test
    public void testEnableFeatureForUserInvalidFeatureId() {
        FeatureForUserRequestDto dto = new FeatureForUserRequestDto();
        dto.setEnabled(true);
        dto.setFeatureId(0);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(400).body("errorMessage", containsString("must be greater than or equal to 1"));
    }

    @Test
    public void testEnableFeatureForUserMissingFeatureId() {
        FeatureForUserRequestDto dto = new FeatureForUserRequestDto();
        dto.setEnabled(true);
        dto.setFeatureId(null);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(400).body("errorMessage", equalTo("'featureId' may not be null"));
    }

    @Test
    public void testEnableFeatureForUserNotFoundUserId() {
        FeatureForUserRequestDto dto = new FeatureForUserRequestDto();
        dto.setEnabled(true);
        dto.setFeatureId(1);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("userId", 99999999)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(404).body("errorMessage", equalTo("User not found."));
    }

    @Test
    public void testEnableFeatureForUserNotFoundFeatureId() {
        FeatureForUserRequestDto dto = new FeatureForUserRequestDto();
        dto.setEnabled(true);
        dto.setFeatureId(99999999);
        given().contentType(MediaType.APPLICATION_JSON).body(dto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(404).body("errorMessage", equalTo("Feature not found."));
    }

    @Test
    public void testEnableFeaturesForUser() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("featureToEnable" + randomString);
        String featureId = createNewFeature(dto);

        FeatureForUserRequestDto enableDto = new FeatureForUserRequestDto();
        enableDto.setEnabled(true);
        enableDto.setFeatureId(Integer.parseInt(featureId));
        given().contentType(MediaType.APPLICATION_JSON).body(enableDto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(204);

        Response response = given().pathParam("userId", 1)
                .when().get(FEATURES_PATH + "user/{userId}");
        AvailableFeaturesDto responseBody = response.getBody().as(AvailableFeaturesDto.class);

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("featureToEnable" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));
    }

    @Test
    public void testDisableFeatureForUser() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("featureToDisable" + randomString);
        String featureId = createNewFeature(dto);

        FeatureForUserRequestDto enableDto = new FeatureForUserRequestDto();
        enableDto.setEnabled(true);
        enableDto.setFeatureId(Integer.parseInt(featureId));
        given().contentType(MediaType.APPLICATION_JSON).body(enableDto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(204);

        Response response = given().pathParam("userId", 1)
                .when().get(FEATURES_PATH + "user/{userId}");
        AvailableFeaturesDto responseBody = response.getBody().as(AvailableFeaturesDto.class);

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("featureToDisable" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));

        FeatureForUserRequestDto disableDto = new FeatureForUserRequestDto();
        disableDto.setEnabled(false);
        disableDto.setFeatureId(Integer.parseInt(featureId));
        given().contentType(MediaType.APPLICATION_JSON).body(disableDto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(204);

        response = given().pathParam("userId", 1)
                .when().get(FEATURES_PATH + "user/{userId}");
        responseBody = response.getBody().as(AvailableFeaturesDto.class);

        expected = new FeatureResponseDto();
        expected.setName("featureToDisable" + randomString);

        assertFalse(responseBody.getEnabledFeatures().contains(expected));
    }

    @Test
    public void testEnableFeatureForUserGloballyDisabled() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(false);
        dto.setName("featureToEnable" + randomString);
        String featureId = createNewFeature(dto);

        FeatureForUserRequestDto enableDto = new FeatureForUserRequestDto();
        enableDto.setEnabled(true);
        enableDto.setFeatureId(Integer.parseInt(featureId));
        given().contentType(MediaType.APPLICATION_JSON).body(enableDto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(204);

        Response response = given().pathParam("userId", 1)
                .when().get(FEATURES_PATH + "user/{userId}");
        AvailableFeaturesDto responseBody = response.getBody().as(AvailableFeaturesDto.class);

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("featureToEnable" + randomString);

        assertFalse(responseBody.getEnabledFeatures().contains(expected));
    }


    //Get user's features tests
    @Test
    public void testGetFeaturesForUserInvalidUserId() {
        given().pathParam("userId", 0)
                .when().get(FEATURES_PATH + "user/{userId}")
                .then().statusCode(400).body("errorMessage", containsString("must be greater than or equal to 1"));
    }

    @Test
    public void testGetFeaturesForUserNotFound() {
        given().pathParam("userId", 999999999)
                .when().get(FEATURES_PATH + "user/{userId}")
                .then().statusCode(404).body("errorMessage", containsString("User not found."));
    }

    @Test
    public void testGetFeaturesForUser() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(true);
        dto.setName("features" + randomString);
        String featureId = createNewFeature(dto);

        FeatureForUserRequestDto enableDto = new FeatureForUserRequestDto();
        enableDto.setEnabled(true);
        enableDto.setFeatureId(Integer.parseInt(featureId));
        given().contentType(MediaType.APPLICATION_JSON).body(enableDto).pathParam("userId", 1)
                .when().put(FEATURES_PATH + "user/{userId}").then()
                .statusCode(204);

        Response response = given().pathParam("userId", 1)
                .when().get(FEATURES_PATH + "user/{userId}");
        AvailableFeaturesDto responseBody = response.getBody().as(AvailableFeaturesDto.class);

        FeatureResponseDto expected = new FeatureResponseDto();
        expected.setName("features" + randomString);

        assertTrue(responseBody.getEnabledFeatures().contains(expected));
    }


    //helpers
    private AvailableFeaturesDto getAvailableFeaturesDto() {
        Response response = given().when().get(FEATURES_PATH);
        assertEquals(200, response.statusCode());
        return response.getBody().as(AvailableFeaturesDto.class);
    }

    private String createNewFeature(FeatureRequestDto dto) {
        Response response =  given().contentType(MediaType.APPLICATION_JSON).body(dto)
                .when().post(FEATURES_PATH);

        assertEquals(201, response.statusCode());
        assertTrue(response.getHeader("Location").contains("api/v1/features/"));
        String[] split = response.getHeader("Location").split("/");
        return split[split.length - 1];
    }
}
