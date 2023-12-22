package br.com.optimate.manager.resource.product;

import br.com.optimate.manager.dto.ProductGroupDto;
import br.com.optimate.manager.service.ProductGroupService;
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
@TestHTTPEndpoint(ProductGroupResource.class)
class ProductGroupResourceTest {

    @InjectMock
    ProductGroupService productGroupService;
    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("acronym", "SOP001");
        this.jsonObject.put("description", "soja");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void saveProductGroupTest() {
        Mockito.when(productGroupService.saveProductGroup(Mockito.any())).thenReturn(new ProductGroupDto());
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
    void saveProductGroupUnauthorizedTest() {
        Mockito.when(productGroupService.saveProductGroup(Mockito.any())).thenReturn(new ProductGroupDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void listAllTest() {
        Mockito.when(productGroupService.listAll()).thenReturn(new ArrayList<>());
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
        Mockito.when(productGroupService.listAll()).thenReturn(new ArrayList<>());
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
    void findProductGroupByAcronymTest() {
        Mockito.when(productGroupService.findListProductByAcronym(Mockito.any())).thenReturn(new ProductGroupDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findProductByAcronym")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findProductGroupByAcronymUnauthorizedTest() {
        Mockito.when(productGroupService.findListProductByAcronym(Mockito.any())).thenReturn(new ProductGroupDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findProductByAcronym")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void editProductGroupTest() {
        Mockito.when(productGroupService.saveProductGroup(Mockito.any())).thenReturn(new ProductGroupDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editProductGroup")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void editProductGroupUnauthorizedTest() {
        Mockito.when(productGroupService.saveProductGroup(Mockito.any())).thenReturn(new ProductGroupDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editProductGroup")
                .then()
                .statusCode(403);
    }
}