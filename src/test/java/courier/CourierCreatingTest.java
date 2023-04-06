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

public class CourierCreatingTest {
    CourierCreateStep courierCreate = new CourierCreateStep();
    CourierDeleteStep courierDelete = new CourierDeleteStep();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new AllureRestAssured());
    }

    @DisplayName("Courier - Creating a courier")
    @Description("Successful account creation")
    @Test //Courier - Создание курьера
    public void creatingCourierTest() { // Успешное создание учетной записи
        Response response = courierCreate.createValidCourier();
        response.then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat().body("ok", Matchers.is(true));
    }

    @DisplayName("Courier - Creating a courier")
    @Description("Request without login or password")
    @Test //Courier - Создание курьера
    public void createCourierWithoutLogin() { //Запрос без логина или пароля:
        Response response = courierCreate.createNoValidCourier();
        response.then().log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", Matchers.is("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Courier - Creating a courier")
    @Description("Request with a duplicate login")
    @Test //Courier - Создание курьера
    public void createTwoIdenticalLoginCourierTest() { //создание нового курьера с повторяющимся логином
        Response courier1 = courierCreate.createValidCourier();
        Response courier2 = courierCreate.createValidCourier();
        courier1.then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat().body("ok", Matchers.is(true));
        courier2.then().log().all()
                .statusCode(HttpStatus.SC_CONFLICT)
                .assertThat()
                .and()
                .body("message", Matchers.is("Этот логин уже используется. Попробуйте другой."));
    }


    @After
    public void deleteDataCourier() {
        courierDelete.deleteDataCourier();
    }
}

