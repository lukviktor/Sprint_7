package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scooter.order.Order;
import specification.OrderStep;

import static constants.ConstantUrlAPI.BASE_URL;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;

    public CreateOrderTest(Order order) {
        this.order = order;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new AllureRestAssured());
    }

    @Parameterized.Parameters(name = "Test data: {0}")
    public static Object[][] getOrderParameters() {
        return new Object[][]{
                {new Order("Ян", "Константинопольский", "Полевая",
                        "Черкизовская", "+79099099009", "2023-04-10",
                        1, new String[]{"BLACK"}, "Не беспокоить")},


                {new Order("Абдурахмангаджи", "Ли", "Нулевая",
                        "Динамо", "79099099009", "10.04.2023",
                        3, new String[]{"GREY"}, "Do not disturb")},


                {new Order("ПетрНет", "Henderson", "Москва",
                        "Щёлковская", "89099099009", "2023-04-15",
                        5, new String[]{"BLACK", "GREY"}, "No comments")},


                {new Order("Charlotte", "Fields", "Дубна",
                        "Площадь Ильича", "+79099999999", "2023-04-16",
                        5, new String[]{}, "")}
        };
    }

    OrderStep orderStep = new OrderStep();

    @Test
    /*
    Проверь, что когда создаёшь заказ:
можно указать один из цветов — BLACK или GREY;
можно указать оба цвета;
можно совсем не указывать цвет;
тело ответа содержит track.
     */
    @DisplayName("Orders - Creating an order")
    @Description("Successful order creation")
    public void checkCreateOrder() {

        orderStep.setCreateOrder(order);
        orderStep.createOrderRequest()
                .then().statusCode(201)
                .and()
                .assertThat().body("track", notNullValue());
    }
}
