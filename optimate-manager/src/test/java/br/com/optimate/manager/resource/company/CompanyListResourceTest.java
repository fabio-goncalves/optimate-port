package br.com.optimate.manager.resource.company;

import br.com.optimate.manager.dto.CompanyDto;
import br.com.optimate.manager.service.CompanyService;
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
@TestHTTPEndpoint(CompanyListResource.class)
class CompanyListResourceTest {

    @InjectMock
    CompanyService companyService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        jsonObject.put("name", "empresa 1");
        jsonObject.put("isActive", true);
        jsonObject.put("cnpj", "00.000.000/0001-00");
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void findCompanyByNameTest() {
        Mockito.when(companyService.findCompanyByName(Mockito.anyString())).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().get("/findCompanyByName")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findCompanyByNameUnauthorizedTest() {
        Mockito.when(companyService.findCompanyByName(Mockito.anyString())).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().get("/findCompanyByName")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void findCompanyByStatusTest() {
        Mockito.when(companyService.findCompanyByStatus(Mockito.anyBoolean())).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findCompanyByStatus")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findCompanyByStatusUnauthorizedTest() {
        Mockito.when(companyService.findCompanyByStatus(Mockito.anyBoolean())).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findCompanyByStatus")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void findCompanyByCNPJTest() {
        Mockito.when(companyService.findCompanyByCNPJ(Mockito.anyString())).thenReturn(new CompanyDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findCompanyByCNPJ")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findCompanyByCNPJUnauthorizedTest() {
        Mockito.when(companyService.findCompanyByCNPJ(Mockito.anyString())).thenReturn(new CompanyDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findCompanyByCNPJ")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void findListCompanyByNameTest() {
        Mockito.when(companyService.findListCompanyByName(Mockito.anyString())).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findListCompanyByCNPJ")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"mock"})
    void findListCompanyByNameUnauthorizedTest() {
        Mockito.when(companyService.findListCompanyByName(Mockito.anyString())).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findListCompanyByCNPJ")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"user"})
    void listAllCompaniesTest() {
        Mockito.when(companyService.listAll()).thenReturn(new ArrayList<>());
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
    void listAllCompaniesUnauthorizedTest() {
        Mockito.when(companyService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/listAll")
                .then()
                .statusCode(403);
    }
}