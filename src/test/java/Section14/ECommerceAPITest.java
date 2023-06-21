package Section14;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITest {

    public static void main(String[] args) {

        //1. Login
      LoginRequest login = new LoginRequest();
      login.setUserEmail("Suha1232@gmail.com");
      login.setUserPassword("Test@1234");

        RequestSpecification res = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification req = given().spec(res).body(login);
       LoginResponse response = req.when().post("/api/ecom/auth/login").then().log().all().spec(resspec).extract().response().as(LoginResponse.class);
        System.out.println(response.getToken());
        String Token =response.getToken();
        System.out.println(response.getMessage());
        System.out.println(response.getUserId());
        String userId = response.getUserId();

        //2.Create Product
       RequestSpecification CreateProductBaseRequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
               .addHeader("Authorization",Token).build();
        //.param is used to add the Form-Data bcoz this one is ot JSON
      RequestSpecification reqAddProduct = given().log().all().spec(CreateProductBaseRequest).param("productName", "Monitor")
               .param("productAddedBy", userId).param("productCategory", "Electronoics")
               .param("productSubCategory", "Desktop").param("productPrice", "25500")
               .param("productDescription", "Lenova").param("productFor", "men")
               .multiPart("productImage", new File("Test.jpg"));

      CreateProductResponse CreateProductRes = reqAddProduct.when().post("/api/ecom/product/add-product")
              .then().log().all().extract().as(CreateProductResponse.class);
        System.out.println(CreateProductRes.getProductId());
        String productId = CreateProductRes.getProductId();

        System.out.println(CreateProductRes.getMessage());


        //3.Create Order
        RequestSpecification CreateOrderBaseRequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization",Token).build();


        //Second. as OrderDetail class having data so created object to access them
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderedId(productId);
        //Third. we have created List bcoz now we have only one array in future if have multiple then we need this
        //we have added orderDetail in add parameter to add in to the orderDetailList
        //now orderDetailList is having the data & that list is passed in to the First(SetOrders Parameter)
        List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
        orderDetailList.add(orderDetail);
        //First. as setOrder is expecting List in its parameter so we have writtern Second
        Order order = new Order();
        order.setOrders(orderDetailList);


        RequestSpecification reqCreateOrder = given().spec(CreateOrderBaseRequest).body(order);
       String resCreateOrder = reqCreateOrder.when().post("/api/ecom/order/create-order")
                .then().log().all().extract().asString();
        System.out.println(resCreateOrder);


        //View Order Details

        //Delete Order

        RequestSpecification deleteBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization",Token).build();

        RequestSpecification deletereq = given().spec(deleteBaseReq).pathParam("productId",productId);

        String deleteProductRes = deletereq.when().delete("/api/ecom/product/delete-product/{productId}")
                                            .then().log().all().extract().asString();

        JsonPath js = new JsonPath(deleteProductRes);
        Assert.assertEquals("Product Deleted Successfully",js.get("message"));

    }
}
