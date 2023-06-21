import Files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


public class ComplexJson {

    public static void main(String[] args){

        JsonPath js = new JsonPath(PayLoad.CoursePrice());

        int Count = js.getInt("courses.size()");
        System.out.println(Count);
        int PurchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(PurchaseAmount);

        //3. Print Title of the first course
        String FirstCourseTitle = js.getString("courses[0].title");
        System.out.println(FirstCourseTitle);

        //4. Print All course titles and their respective Prices
        //int CoursesCount = js.getInt("courses.size()");
        for(int i = 0; i<Count; i++) {

            String CourseTitle = js.get("courses[" + i + "].title");
            int CoursePrice = js.get("courses[" + i + "].price");
            System.out.println(CourseTitle);
            System.out.println(CoursePrice);
        }


            //5. Print no of copies sold by RPA Course
        for(int i=0; i<Count; i++)
        {
            String CourseTitle = js.get("courses[" + i + "].title");
            if(CourseTitle.equalsIgnoreCase("RPA")){
               int Copies =  js.get("courses[" + i + "].copies");
                System.out.println(" RPA Copies Sold : "+Copies);
                break;
            }
        }


            //6. Verify if Sum of all Course prices matches with Purchase Amount

       int TotalCoursePrice = js.getInt("dashboard.purchaseAmount");
        System.out.println(TotalCoursePrice);
        int Sum = 0;
// Multiply the Courses_Prices & copies to get the Purchase Amount
        for (int i = 0; i<Count;i++)
        {
           int coursePrice =  js.get("courses[" + i +"].price");
            int courseCopies = js.get("courses ["+i+"].copies");
            int SumAmount = coursePrice * courseCopies;
            System.out.println(SumAmount);
           Sum = SumAmount + Sum;
        }
        Assert.assertEquals(TotalCoursePrice,Sum);
    }}

