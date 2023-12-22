package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.dto.BerthDto;
import br.com.optimate.manager.service.BerthService;
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
@TestHTTPEndpoint(BerthResource.class)
class BerthResourceTest {

    @InjectMock
    BerthService berthService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("name", "Berço 1");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void saveBerthTest() {
        Mockito.when(berthService.saveBerth(Mockito.any())).thenReturn(new BerthDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void saveBerthUnauthorizedTest() {
        Mockito.when(berthService.saveBerth(Mockito.any())).thenReturn(new BerthDto());
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
        Mockito.when(berthService.listAll()).thenReturn(new ArrayList<>());
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
        Mockito.when(berthService.listAll()).thenReturn(new ArrayList<>());
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
    void findBerthByNameTest() {
        Mockito.when(berthService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findBerthByName")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findBerthByNameUnauthorizedTest() {
        Mockito.when(berthService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findBerthByName")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void editBerthTest() {
        Mockito.when(berthService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editBerth")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void editBerthUnauthorizedTest() {
        Mockito.when(berthService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/listAll")
                .then()
                .statusCode(403);
    }
}