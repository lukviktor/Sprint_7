package specification;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import scooter.courier.Courier;

import static constants.ConstantCourierData.COURIER_LOGIN;
import static constants.ConstantCourierData.COURIER_PASSWORD;
import static constants.ConstantUrlAPI.ENDPOINT_LOGIN_COURIER;
import static io.restassured.RestAssured.given;

public class CourierLoginStep {


    @Step("The courier's login in the system, a request with a non-existent login-password pair")
    public Response courierInSystemNonExistentLoginPassword() { //Courier - в системе. Запрос без логина или пароля
        Courier courier = new Courier("AbuKarimMuhammad", "EC11AA");
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_LOGIN_COURIER);
    }

    @Step("Courier's login in the system, request without login or password")
    public Response courierInSystemNonPassword() { //Courier - в системе. Запрос без логина или пароля
        Courier courier = new Courier(COURIER_LOGIN, "");
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_LOGIN_COURIER);
    }

    @Step("Courier's login in the system, successful request")
    public Response courierInSystem() { // Courier - Логин курьера в системе успешный логин
        Courier courier = new Courier(COURIER_LOGIN, COURIER_PASSWORD);
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_LOGIN_COURIER);
    }
}