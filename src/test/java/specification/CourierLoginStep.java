package specification;

import io.restassured.response.Response;
import scooter.courier.Courier;

import static constants.ConstantCourierData.COURIER_LOGIN;
import static constants.ConstantCourierData.COURIER_PASSWORD;
import static constants.ConstantUrlAPI.ENDPOINT_LOGIN_COURIER;
import static io.restassured.RestAssured.given;

public class CourierLoginStep {


    //Запрос несуществующей парой логин-пароль
    public Response courierInSystemNonExistentLoginPassword() { //Courier - системе. Запрос без логина или пароля
        Courier courier = new Courier("AbuKarimMuhammad", "EC11AA");
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_LOGIN_COURIER);
    }

    public Response courierInSystemNonPassword() { //Courier - системе. Запрос без логина или пароля
        Courier courier = new Courier(COURIER_LOGIN, "");
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_LOGIN_COURIER);
    }

    public Response courierInSystem() { // Courier - Логин курьера в системе успешный логин
        Courier courier = new Courier(COURIER_LOGIN, COURIER_PASSWORD);
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_LOGIN_COURIER);
    }
}