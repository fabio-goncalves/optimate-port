package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.dto.OperationalAreaDto;
import br.com.optimate.manager.service.OperationalAreaService;
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
@TestHTTPEndpoint(OperationalAreaResource.class)
class OperationalAreaResourceTest {

    @InjectMock
    OperationalAreaService operationalAreaService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("name", "Berço 1");
        this.jsonObject.put("acronym", "BE01");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void saveOperationalAreaTest() {
        Mockito.when(operationalAreaService.saveOperationalArea(Mockito.any())).thenReturn(new OperationalAreaDto());
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
    void saveOperationalAreaUnauthorizedTest() {
        Mockito.when(operationalAreaService.saveOperationalArea(Mockito.any())).thenReturn(new OperationalAreaDto());
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
        Mockito.when(operationalAreaService.listAll()).thenReturn(new ArrayList<>());
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
        Mockito.when(operationalAreaService.listAll()).thenReturn(new ArrayList<>());
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
        Mockito.when(operationalAreaService.findMooringLocationByAcronym(Mockito.any())).thenReturn(new OperationalAreaDto());
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
        Mockito.when(operationalAreaService.findMooringLocationByAcronym(Mockito.any())).thenReturn(new OperationalAreaDto());
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
        Mockito.when(operationalAreaService.editMooringLocation(Mockito.any())).thenReturn(new OperationalAreaDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/edit")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void editMooringLocationUnauthorizedTest() {
        Mockito.when(operationalAreaService.editMooringLocation(Mockito.any())).thenReturn(new OperationalAreaDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/edit")
                .then()
                .statusCode(403);
    }
}