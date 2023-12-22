package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.dto.MooringLocationDto;
import br.com.optimate.manager.service.MooringLocationService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(MooringLocationResource.class)
class MooringLocationResourceTest {

    @InjectMock
    MooringLocationService mooringLocationService;
    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        jsonObject.put("acronymMooring", "P01");
        jsonObject.put("description", "Berco 01");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void saveMooringLocationTest() {
        Mockito.when(mooringLocationService.saveMooringLocation(Mockito.any())).thenReturn(new MooringLocationDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void saveMooringLocationUnauthorizedTest() {
        Mockito.when(mooringLocationService.saveMooringLocation(Mockito.any())).thenReturn(new MooringLocationDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin", "user"})
    void listAllTest() {
        Mockito.when(mooringLocationService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/listAll")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void listAllUnauthorizedTest() {
        Mockito.when(mooringLocationService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/listAll")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void findMooringLocationByAcronymTest() {
        Mockito.when(mooringLocationService.findMooringLocationByAcronym(Mockito.any())).thenReturn(new MooringLocationDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findMooringLocationByAcronym")
                .then()
                .statusCode(200);
    }
    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findMooringLocationByAcronymUnauthorizedTest() {
        Mockito.when(mooringLocationService.findMooringLocationByAcronym(Mockito.any())).thenReturn(new MooringLocationDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findMooringLocationByAcronym")
                .then()
                .statusCode(403);
    }


    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void editMooringLocationTest() {
        Mockito.when(mooringLocationService.editMooringLocation(Mockito.any())).thenReturn(new MooringLocationDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editMooringLocation")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void editMooringLocationUnauthorizedTest() {
        Mockito.when(mooringLocationService.editMooringLocation(Mockito.any())).thenReturn(new MooringLocationDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editMooringLocation")
                .then()
                .statusCode(403);
    }
}