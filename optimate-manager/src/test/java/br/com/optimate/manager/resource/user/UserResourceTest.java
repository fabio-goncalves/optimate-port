package br.com.optimate.manager.resource.user;

import br.com.optimate.manager.domain.user.User;
import br.com.optimate.manager.dto.UserDto;
import br.com.optimate.manager.service.UserService;
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
@TestHTTPEndpoint(UserResource.class)
class UserResourceTest {

    @InjectMock
    UserService userService;
    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setUp() {
        this.jsonObject.put("username", "userTest");
        this.jsonObject.put("email", "test@test.com");
    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin"})
    void saveUserTest() {
        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(new UserDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/save")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin"})
    void listAllTest() {
        Mockito.when(userService.listAll()).thenReturn(new ArrayList<>());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/listAll")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin"})
    void findUserByUsernameTest() {
        Mockito.when(userService.findUserByUsername(Mockito.any())).thenReturn(new UserDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/findUserByUsername")
                .then()
                .statusCode(200);

    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin"})
    void editUserTest() {
        Mockito.when(userService.editUser(Mockito.any())).thenReturn(new UserDto());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().post("/editUser")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin", "user"})
    void getCurrentUserTest() {
        Mockito.when(userService.getCurrentUser()).thenReturn(new User());
        RestAssured
                .given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when().get("/currentUser")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin"})
    void deleteUserTest() {
        Mockito.when(userService.deleteUser(Mockito.anyLong())).thenReturn(" ");
        RestAssured
                .given()
                .contentType("application/json")
                .param("id", 1)
                .when().delete("/deleteUser")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "userTest", roles = {"admin"})
    void uploadAvatarTest() {
        Mockito.when(userService.uploadAvatar(Mockito.anyLong(), Mockito.anyString())).thenReturn(new UserDto());
        RestAssured
                .given()
                .contentType("application/json")
                .pathParam("id", 1)
                .when().post("/uploadAvatar/{id}")
                .then()
                .statusCode(200);
    }
}