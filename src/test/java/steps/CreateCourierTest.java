package steps;

import generator.CreateCourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CreateCourier;

import static org.hamcrest.core.IsEqual.equalTo;

public class CreateCourierTest {
    private static final String REGISTER_ERROR_400 = "Недостаточно данных для создания учетной записи";
    private static final String REGISTER_ERROR_409 = "Этот логин уже используется. Попробуйте другой.";

    @Test
    @DisplayName("Создание курера с валидными данными")
    @Description("Ожидание ответа 201")
    public void createCourierAllCorrectParameters() {
        CreateCourier request = CreateCourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);

        response.then().statusCode(201).and().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание нового курьера с валидными данными, без поля 'firstName")
    @Description("Ожидание ответа 201")
    public void createCourierWithoutFirstName() {
        CreateCourier request = CreateCourierGenerator.getNewCourierWithFirstNameNull();
        Response response = Courier.createNewCourier(request);

        response.then().statusCode(201).and().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание нового курьера с валидными данными, без поля 'password")
    @Description("Ожидание ответа 400")
    public void createCourierWithoutPassword() {
        CreateCourier request = CreateCourierGenerator.getNewCourierWithPasswordNull();
        Response response = Courier.createNewCourier(request);

        response.then().statusCode(400).and().assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Создание нового курьера с валидными данными, без поля 'password")
    @Description("Ожидание ответа 400")
    public void createCourierWithoutLogin() {
        CreateCourier request = CreateCourierGenerator.getNewCourierWithLoginNull();
        Response response = Courier.createNewCourier(request);

        response.then().statusCode(400).and().assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Ожидание ответа 400")
    public void createTwoIdenticalCourier() {
        CreateCourier request = CreateCourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);

        response.then().statusCode(201).and().assertThat().body("ok", equalTo(true));

        Response errorResponse = Courier.createNewCourier(request);
        errorResponse.then().statusCode(409).and().assertThat().body("message", equalTo(REGISTER_ERROR_409));
    }
}