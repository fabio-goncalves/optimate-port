package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.dto.PortFacilityDto;
import br.com.optimate.manager.service.PortFacilityService;
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

@QuarkusTest
@TestHTTPEndpoint(PortFacilityResource.class)
class PortFacilityResourceTest {

    @InjectMock
    PortFacilityService portFacilityService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("name", "Porto XX");
        this.jsonObject.put("acronymPort", "POX01");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void savePortFacilityTest() {
        Mockito.when(portFacilityService.savePortFacility(Mockito.any())).thenReturn(new PortFacilityDto());
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
    void savePortFacilityUnauthorizedTest() {
        Mockito.when(portFacilityService.savePortFacility(Mockito.any())).thenReturn(new PortFacilityDto());
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
        Mockito.when(portFacilityService.listAll()).thenReturn(new ArrayList<>());
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
        Mockito.when(portFacilityService.listAll()).thenReturn(new ArrayList<>());
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
    void findPortFacilityByNameTest() {
        Mockito.when(portFacilityService.findPortFacilityByName(Mockito.any())).thenReturn(new PortFacilityDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findPortFacilityByName")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findPortFacilityByNameUnauthorizedTest() {
        Mockito.when(portFacilityService.findPortFacilityByName(Mockito.any())).thenReturn(new PortFacilityDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findPortFacilityByName")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void editPortFacilityTest() {
        Mockito.when(portFacilityService.editPortFacility(Mockito.any())).thenReturn(new PortFacilityDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editPortFacility")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void editPortFacilityUnauthorizedTest() {
        Mockito.when(portFacilityService.editPortFacility(Mockito.any())).thenReturn(new PortFacilityDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editPortFacility")
                .then()
                .statusCode(403);
    }
}