package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Data;
import pojo.CreateCourier;
import pojo.LoginCourier;

import static io.restassured.RestAssured.given;

@Data
public class Courier extends Base {

    private static final String COURIER = "courier";
    private static final String COURIER_LOGIN = COURIER + "/login";

    @Step("Создать курьера")
    public static Response createNewCourier(CreateCourier body) {
        return given().log().all()
                .spec(getDefaultRequestSpec())
                .body(body)
                .when()
                .post(COURIER);
    }

    @Step("Проверка авторизации курьера")
    public static Response courierAuthorization(LoginCourier body) {
        return given().log().all()
                .spec(getDefaultRequestSpec())
                .body(body)
                .when()
                .post(COURIER_LOGIN);
    }

}