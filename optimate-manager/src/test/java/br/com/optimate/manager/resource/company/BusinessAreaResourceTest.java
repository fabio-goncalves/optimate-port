package br.com.optimate.manager.resource.company;

import br.com.optimate.manager.dto.BusinessAreaDto;
import br.com.optimate.manager.service.BusinessAreaService;
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
@TestHTTPEndpoint(BusinessAreaResource.class)
class BusinessAreaResourceTest {

    @InjectMock
    BusinessAreaService businessAreaService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("name", "PortoNorte");
        this.jsonObject.put("description", "Agência");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void saveBusinessAreaTest() {
        Mockito.when(businessAreaService.saveBusinessArea(Mockito.any())).thenReturn(new BusinessAreaDto());
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
    void saveBusinessAreaUnauthorizedTest() {
        Mockito.when(businessAreaService.saveBusinessArea(Mockito.any())).thenReturn(new BusinessAreaDto());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void listBusinessAreaTest() {
        Mockito.when(businessAreaService.listBusinessArea()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().get("/list")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"service"})
    void listBusinessAreaUnauthorizedUserTest() {
        Mockito.when(businessAreaService.listBusinessArea()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().get("/list")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void editBusinessAreaTest() {
        Mockito.when(businessAreaService.editBusinessArea(Mockito.any())).thenReturn(new BusinessAreaDto());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().post("/edit")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void editBusinessAreaUnauthorizedTest() {
        Mockito.when(businessAreaService.editBusinessArea(Mockito.any())).thenReturn(new BusinessAreaDto());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().post("/edit")
                .then()
                .statusCode(403);
    }
}