package order;

import generator.CreateOrderGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.CreateOrder;
import steps.Order;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;

@AllArgsConstructor
@RunWith(Parameterized.class)
@DisplayName("Создание заказа")
public class CreateOrdersTest {
    private final List<String> colorsOfOrder;
    private final int expectedStatus;

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                {List.of("BLACK"), 201},
                {List.of("GREY"), 201},
                {Arrays.asList("BLACK", "GREY"), 201},
                {Collections.emptyList(), 201}
        };
    }

    @Test
    @DisplayName("Создание заказа с параметризацией")
    @Description("Ожидаемый код ответа: 201")
    public void createOrderWithParam() {
        CreateOrder request = CreateOrderGenerator.getRandomOrderGenerator(colorsOfOrder);
        Response response = Order.createOrder(request);

        response.then()
                .statusCode(expectedStatus)
                .and()
                .assertThat().body("track", notNullValue());
    }
}
