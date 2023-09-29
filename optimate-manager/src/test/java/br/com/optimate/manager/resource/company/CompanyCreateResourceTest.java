package br.com.optimate.manager.resource.company;

import br.com.optimate.manager.service.CompanyService;
import br.com.optimate.manager.dto.CompanyDto;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@TestHTTPEndpoint(CompanyCreateResource.class)
class CompanyCreateResourceTest {

    @InjectMock
    private CompanyService companyServiceMock;

    @Test
    void saveCompanyTest() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", "test rest");
        jsonObj.put("cnpj", "1212121212");
        Mockito.when(companyServiceMock.saveCompany(Mockito.any())).thenReturn(new CompanyDto());

        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObj.toString())
                .when().post("/save")
                .then()
                .statusCode(401);
//                .assertThat()
//                .body("resultMessage",equalTo("Message accepted"));
    }

    @Test
    void editCompany() {
    }
}