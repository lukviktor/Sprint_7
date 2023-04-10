package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import specification.OrderStep;

import static constants.ConstantUrlAPI.BASE_URL;
import static org.hamcrest.Matchers.notNullValue;

public class ListOrdersTest {
    OrderStep orderStep = new OrderStep();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new AllureRestAssured());
    }


    @Test
    @DisplayName("Orders - Getting a list of orders")
    @Description("Check that the list of orders is returned to the response body.")
    public void checkCreateOrder() {
        orderStep.getOrdersRequest()
                .then().statusCode(200)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}