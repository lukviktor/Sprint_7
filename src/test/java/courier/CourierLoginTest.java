package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specification.CourierCreateStep;
import specification.CourierDeleteStep;
import specification.CourierLoginStep;

import static constants.ConstantUrlAPI.BASE_URL;

public class CourierLoginTest {
    CourierCreateStep courierCreate = new CourierCreateStep();
    CourierDeleteStep courierDelete = new CourierDeleteStep();
    CourierLoginStep courierLogin = new CourierLoginStep();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new AllureRestAssured());
    }

    @DisplayName("Courier - The courier's login in the system")
    @Description("Successful login")
    @Test
    public void courierInSystemTest() {  //Успешный логин и пароль
        courierCreate.createValidCourier();
        Response response = courierLogin.courierInSystem();
        response.then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("id", Matchers.notNullValue());
    }

    @DisplayName("Courier - The courier's login in the system")
    @Description("Request without login or password")
    @Test // Courier - Логин курьера в системе
    public void courierInSystemNoPasswordTest() { //Запрос без логина или пароля
        courierCreate.createValidCourier();
        //vvv
        Response response = courierLogin.courierInSystemNonPassword();
        response.then().log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat().body("message", Matchers.is("Недостаточно данных для входа"));
    }

    @DisplayName("Courier - The courier's login in the system")
    @Description("Request with a non-existent login-password pair")
    @Test // Проверка курьер в системе.
    public void courierInSystemNonExistentLoginTest() {  //Запрос c несуществующей парой логин-пароль:
        Response response = courierLogin.courierInSystemNonExistentLoginPassword();
        response.then().log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat().body("message", Matchers.is("Учетная запись не найдена"));
    }


    @After
    public void deleteDataCourier() {
        courierDelete.deleteDataCourier();
    }
}

