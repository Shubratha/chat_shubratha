import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class testjson
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("jsonfile.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            //JSONArray employeeList = (JSONArray) obj;
            JSONArray employeeList = new JSONArray();
            employeeList.add(obj);
            System.out.println(employeeList);
             
            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
 
    private static void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        //JSONObject employeeObject = (JSONObject) employee.get("user");
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
         JSONArray employeeObject = new JSONArray();
            employeeObject.add(obj);
        //Get employee first name
        String firstName = (String) employeeObject.get("firstname");   
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastname"); 
        System.out.println(lastName);
         
        //Get employee website name
        String dob = (String) employeeObject.get("dob");   
        System.out.println(dob);

        String adhar = (String) employeeObject.get("adharID");   
        System.out.println(adhar);

        String gender = (String) employeeObject.get("gender");   
        System.out.println(gender);

        String email = (String) employeeObject.get("email");   
        System.out.println(dob);

        String username = (String) employeeObject.get("username");   
        System.out.println(username);

        String password = (String) employeeObject.get("password");   
        System.out.println(password);
    }
}