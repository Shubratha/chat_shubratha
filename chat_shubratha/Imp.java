import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import java.util.Date; 
public class Imp{
    
     class Record {
        String firstName,lastName,email,username,password;
        //java.sql.Date dob;
        String dob;
        String adharId;
        char gender;



    }


    public static Connection Mysqlcon(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoData","root","");    
            }
            catch(Exception e)
            { 
                System.out.println(e);
            } 
            return con;
    }

    public int checkExtension(String filename){
        String ext = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if(ext.equals("csv"))
            return 1;
        if(ext.equals("xml"))
            return 2;
        if(ext.equals("json"))
            return 3;
        return 0;
    }
    



    private  ArrayList<Record> readTXTFile(String filename) {

        //String csvFile = "csvfile.csv";
        int check=checkExtension(filename);
        ArrayList<Record> records = new ArrayList<Record>();

        if(check==1){
            String line = "";
            String cvsSplitBy = ",";
        //  ArrayList<Record> records = new ArrayList<Record>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

                while ((line = br.readLine()) != null) {
                    String[] columns = line.split(cvsSplitBy);
                    Record r = new Record();
                    r.firstName = columns[0];
                    
                    r.lastName = columns[1];
                    
                    r.dob=columns[2];
                    r.adharId=columns[3];
                    char c = columns[4].charAt(0);
                    r.gender = c;
                    if(isValidEmailAddress(columns[5]))
                    r.email = columns[5];
                    r.username = columns[6];
                    System.out.println(r.gender);
                    r.password = columns[7];
                    records.add(r);

                    
                }
            }   

            catch (IOException e) {
                e.printStackTrace();
            }
            return records;
        }


        if(check==2)
        {
            
            /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(filename); */

        }


        if(check==3)
        {   
            

            JSONParser jsonParser = new JSONParser();
            
            
            try
            {
            //Read JSON file

                Object obj = jsonParser.parse(new FileReader(filename));

                JSONObject users = (JSONObject) obj;
                System.out.println(obj);
                            System.out.println("*************************");
            JSONArray userlist = (JSONArray)users.get("user");
            Iterator<String> iterator = userlist.iterator();
            while (iterator.hasNext()) 
            {

                Object userobj = (Object)iterator.next();
                JSONObject userObject = (JSONObject)userobj;
                Record r = new Record();
                //Read data from JSON file
                String fname = (String) userObject.get("firstname");   
                r.firstName= fname;
                String lname = (String) userObject.get("lastname");
                r.lastName=lname; 
                String date_of_birth = (String) userObject.get("dob");
                r.dob=date_of_birth;   
                String adhar_id = (String) userObject.get("adharID");
                r.adharId=  adhar_id; 
                String gender = (String) userObject.get("gender");
                char c = gender.charAt(0);
                r.gender=c;
                String email = (String) userObject.get("email"); 
                if(isValidEmailAddress(email))  
                    r.email=email;
                String username = (String) userObject.get("username");   
                r.username=username;
                String password = (String) userObject.get("password");   
                r.password=password;

                records.add(r);

             }
                       } catch (Exception err) {
                System.out.println(err);
            }
            return records;

        }
       // return records;
    }

    public static boolean isValidEmailAddress(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+

                            "[a-zA-Z0-9_+&*-]+)*@" +

                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null)
            return false;
        return pat.matcher(email).matches();
   }

    public void exequery(Connection con, ArrayList<Record> records)
    {
        String query;
        try {
            for (Record r : records) {
                Statement stmt = con.createStatement();
                query = "INSERT INTO user VALUES ('"+r.firstName + "','" + r.lastName+ "','" + r.dob + "'," + r.adharId + ",'"+ r.gender + "','"+ r.email +"','" + r.username+ "','" + r.password +"');";
                System.out.println(query);
                stmt.executeUpdate(query);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


        
    public static void main(String[] args) {
        Imp m= new Imp();
        Connection c=m.Mysqlcon();
        c=m.Mysqlcon();
        ArrayList<Record> rec=m.readTXTFile(args[0]);
        //rec=m.readTXTFile(args[0]);
        m.exequery(c,rec);
     } 
}