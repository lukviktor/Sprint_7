package specification;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import scooter.courier.Courier;

import static constants.ConstantCourierData.COURIER_LOGIN;
import static constants.ConstantCourierData.COURIER_PASSWORD;
import static constants.ConstantUrlAPI.*;
import static io.restassured.RestAssured.given;

public class CourierDeleteStep {
    @Step("Getting the Courier ID value")
    public Integer courierId() {
        Courier courier = new Courier(COURIER_LOGIN, COURIER_PASSWORD);
        Integer id =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(ENDPOINT_LOGIN_COURIER)
                        .then().extract().body().path("id");
        return id;
    }

    @Step("Deleting a courier by id")
    public void deleteDataCourier() { //запрос на успешное удаление курьера
        Integer id = courierId();
        if (id != null) {
            given()
                    .delete(ENDPOINT_CREATE_COURIER + "/{id}", id.toString());
        }
    }

    public Response deleteCourierNoId() { //Запрос без id:
        return given()
                .delete(ENDPOINT_DELETE_COURIER);
    }

    @Step("Deleting a courier without id data")
    public Response deleteCourierNonExistentId() { //Запрос c несуществующим id
        return given()
                .delete(ENDPOINT_CREATE_COURIER + "/{id}", "1aa2w");
    }
}