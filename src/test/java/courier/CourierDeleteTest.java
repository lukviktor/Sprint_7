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

import static constants.ConstantUrlAPI.BASE_URL;
import static org.junit.Assert.assertNull;

public class CourierDeleteTest {
    CourierCreateStep courierCreate = new CourierCreateStep();
    CourierDeleteStep courierDelete = new CourierDeleteStep();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new AllureRestAssured());
    }


    @DisplayName("Courier - Courier Removal")
    @Description("Successful removal of the courier")
    @Test //Courier - Удаление курьера
    public void deleteCourierTest() { //Успешное удаление курьера
        courierCreate.createValidCourier();
        courierDelete.deleteDataCourier();
        Integer response = courierDelete.courierId();
        assertNull("when deleting, the Id is null", response);
    }

    @DisplayName("Courier - Courier Removal")
    @Description("Request without id")
    @Test //Courier - Удаление курьера
    public void deleteCourierNoIdTest() { //Запрос без id
        Response response = courierDelete.deleteCourierNoId();
        response.then().log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat()
                .body("message", Matchers.is("Курьера с таким id нет"));
    }

    @DisplayName("Courier - Courier Removal")
    @Description("Request without id")
    @Test //Courier - Удаление курьера
    public void deleteCourierNonExistentIdTest() { //Запрос c несуществующим id:
        Response response = courierDelete.deleteCourierNonExistentId();
        response.then().log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", Matchers.is("Недостаточно данных для удаления курьера"));
    }

    @After
    public void deleteDataCourier() {
        courierDelete.deleteDataCourier();
    }
}

