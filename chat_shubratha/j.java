import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class j
{
    public static void main(String[] args) throws Exception 
    {

        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoData","root","");  
        Statement stmt=con.createStatement(); 
        Object obj = new JSONParser().parse(new FileReader("employee.json"));
        JSONArray emp = (JSONArray) obj;
        Iterator<String> i = emp.iterator();
        while(i.hasNext())
        {

            Object a = (Object)i.next();
            JSONObject j = (JSONObject)a;
            JSONObject jo= (JSONObject)j.get("user");
            String fname = (String) jo.get("firstname");
            String lname = (String) jo.get("lastname");
            String dob = (String) jo.get("dob");
            String adhar = (String) jo.get("adharID");
            String gender = (String) jo.get("gender");
            String email = (String) jo.get("email");
            String username = (String) jo.get("username");
            String password = (String) jo.get("password");
            String query = ("insert into user values(\""+fname+"\",\""+lname+"\",\""
                                                     +dob+"\",\""+adhar+"\",\""+gender
                                                            +"\",\""+email+"\",\""+username+"\",\""+password+"\");");
            System.out.println(query);
            stmt.executeUpdate(query);

        }
        con.close();
    }
}