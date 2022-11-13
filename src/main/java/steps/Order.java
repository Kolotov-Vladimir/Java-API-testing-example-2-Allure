package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CreateOrder;

import static io.restassured.RestAssured.given;

public class Order extends Base {
    private static final String ORDERS = "orders";

    @Step("Создание заказа")
    public static Response createOrder(CreateOrder body) {
        return given().log().all()
                .spec(getDefaultRequestSpec())
                .body(body)
                .when()
                .post(ORDERS);
    }

    @Step("Получение списка заказов")
    public static Response getOrderList() {
        return given().log().all()
                .spec(getDefaultRequestSpec())
                .when()
                .get(ORDERS);
    }
}
