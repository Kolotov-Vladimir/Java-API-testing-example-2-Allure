package steps;

import generator.CreateCourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import pojo.CreateCourier;
import pojo.LoginCourier;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginCourierTest {

    private static final String REGISTER_ERROR_404 = "Учетная запись не найдена";
    private static final String REGISTER_ERROR_400 = "Недостаточно данных для входа";

    @Test
    @DisplayName("Успешная авторизация курьера")
    @Description("Ожидание ответа 201")
    public void successfulCourierAutorizathion() {
        CreateCourier request = CreateCourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = Courier.courierAuthorization(new LoginCourier(request.getLogin(), request.getPassword()));

        loginResponse.then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера с пустым логином")
    @Description("Ожидание ответа 404")
    public void autorizathionWrongLogin() {
        CreateCourier request = CreateCourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = Courier.courierAuthorization(new LoginCourier(null, request.getPassword()));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Авторизация курьера с пустым паролем")
    @Description("Ожидание ответа 404")
    public void autorizathionWrongPassword() {
        CreateCourier request = CreateCourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = Courier.courierAuthorization(new LoginCourier(request.getLogin(), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_404));
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Ожидание ответа 404")
    public void autorizathionNonExistentCourier() {
        Response loginResponse = Courier.courierAuthorization(new LoginCourier(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_404));
    }
}