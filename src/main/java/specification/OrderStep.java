package specification;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import scooter.order.Order;

import static constants.ConstantUrlAPI.ENDPOINT_LIST_ORDERS;
import static io.restassured.RestAssured.given;

public class OrderStep {
    private Order order;

    public void setCreateOrder(Order order) {
        this.order = order;
    }

    @Step("Create an order, check the response code and order track")
    public Response createOrderRequest() {
        Response response =
                given().log().all()
                        .header("Content-type", "application/json")
                        .body(order)
                        .when()
                        .post(ENDPOINT_LIST_ORDERS);
        return response;

    }

    @Step("Getting a list of orders")
    public Response getOrdersRequest() {
        Response response =
                given().log().all()
                        .get(ENDPOINT_LIST_ORDERS);
        return response;
    }
}
