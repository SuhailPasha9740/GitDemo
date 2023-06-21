import Files.PayLoad;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidations {
    //Assignment
   // 6. Verify if Sum of all Course prices matches with Purchase Amount
    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(PayLoad.CoursePrice());

      int Count =   js.get("courses.size()");

        int sum = 0;
        for (int i = 0; i<Count;i++) {
            int CoursePrice = js.get("courses[" + i + "].price");
            int CourseCopies = js.get("courses[" + i + "].copies");
            int SumAmount = CoursePrice * CourseCopies;
            sum = sum + SumAmount;

        }
        int TotalPurchaseAmount = js.get("dashboard.purchaseAmount");
       Assert.assertEquals(sum,TotalPurchaseAmount);


    }
}
