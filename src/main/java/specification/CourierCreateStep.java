package specification;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import scooter.courier.Courier;

import static constants.ConstantCourierData.*;
import static constants.ConstantUrlAPI.ENDPOINT_CREATE_COURIER;
import static io.restassured.RestAssured.given;

public class CourierCreateStep {
    @Step("Successful creation of a courier account")
    public Response createValidCourier() { // Courier - Создание курьера успешное создание курьера
        Courier courier = new Courier(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRSTNAME);
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_CREATE_COURIER);
    }

    @Step("Creating a courier account without a login")
    public Response createNoValidCourier() {// Courier - Создание курьера без логина
        Courier courier = new Courier("", COURIER_PASSWORD, COURIER_FIRSTNAME);
        return given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ENDPOINT_CREATE_COURIER);
    }
}