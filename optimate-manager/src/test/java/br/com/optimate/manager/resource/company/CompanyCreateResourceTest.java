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

@QuarkusTest
@TestHTTPEndpoint(CompanyCreateResource.class)
class CompanyCreateResourceTest {

    @InjectMock
    private CompanyService companyServiceMock;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void init() {
        this.jsonObject.put("name", "test rest");
        this.jsonObject.put("cnpj", "1212121212");
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void saveCompanyTest() {
        Mockito.when(companyServiceMock.saveCompany(Mockito.any())).thenReturn(new CompanyDto());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"admin", "user"})
    void editCompany() {
        Mockito.when(companyServiceMock.editCompany(Mockito.any())).thenReturn(new CompanyDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/edit")
                .then()
                .statusCode(200);
    }
}