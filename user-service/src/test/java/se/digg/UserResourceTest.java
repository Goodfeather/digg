package se.digg;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import se.digg.model.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class UserResourceTest {


    @Test
    void createUser() {

        User user = new User("Pelle Pirat", "Gata 1", "09-1111");
        given().contentType(ContentType.JSON)
                .body(user)
                .when().post("/digg/user")
                .then()
                .statusCode(200)
                .body(is("101"));

        given().when().get("/digg/user/101")
                .then()
                .statusCode(200)
                .body(is("{\"address\":\"Gata 1\",\"id\":101,\"name\":\"Pelle Pirat\",\"telephone\":\"09-1111\"}"));
    }

    @Test
    void readUsers() {
        given()
                .when().get("/digg/user")
                .then()
                .statusCode(200);
    }

    void updateUser() {

        given().when().get("/digg/user/1")
                .then()
                .statusCode(200)
                .body(is("{\"address\":\"vägen 0\",\"id\":1,\"name\":\"Kalle Anka 0\",\"telephone\":\"08-123130\"}"));

        User user = new User("Pelle Pirat", "Gata 1", "09-1111");
        user.setId(1L);
        given().contentType(ContentType.JSON)
                .body(user)
                .when().put("/digg/user")
                .then()
                .statusCode(200)
                .body(is("1"));

        given().when().get("/digg/user/1")
                .then()
                .statusCode(200)
                .body(is("{\"address\":\"Gata 1\",\"id\":1,\"name\":\"Pelle Pirat\",\"telephone\":\"09-1111\"}"));
    }

    @Test
    void deleteUser() {

        given().contentType(ContentType.JSON)
                .when().delete("/digg/user/2")
                .then()
                .statusCode(200)
                .body(is("{\"address\":\"vägen 1\",\"id\":2,\"name\":\"Kalle Anka 1\",\"telephone\":\"08-123131\"}"));

    }
}