package br.com.optimate.manager.resource.product;

import br.com.optimate.manager.dto.ProductDto;
import br.com.optimate.manager.service.ProductService;
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
@TestHTTPEndpoint(ProductResource.class)
class ProductResourceTest {

    @InjectMock
    ProductService productService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("acronym", "SOP001");
        this.jsonObject.put("description", "soja");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin"})
    void saveProductTest() {
        Mockito.when(productService.saveProduct(Mockito.any())).thenReturn(new ProductDto());
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
    void saveProductUnauthorizedTest() {
        Mockito.when(productService.saveProduct(Mockito.any())).thenReturn(new ProductDto());
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
    void listAllTest() {
        Mockito.when(productService.listAll()).thenReturn(new ArrayList<>());
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
        Mockito.when(productService.listAll()).thenReturn(new ArrayList<>());
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
    void findProductByAcronymTest() {
        Mockito.when(productService.findProductByAcronym(Mockito.any())).thenReturn(new ProductDto());
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
    void findProductByAcronymUnauthorizedTest() {
        Mockito.when(productService.findProductByAcronym(Mockito.any())).thenReturn(new ProductDto());
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
    void editProductTest() {
        Mockito.when(productService.editProduct(Mockito.any())).thenReturn(new ProductDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editProduct")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void editProductUnauthorizedTest() {
        Mockito.when(productService.editProduct(Mockito.any())).thenReturn(new ProductDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editProduct")
                .then()
                .statusCode(403);
    }
}