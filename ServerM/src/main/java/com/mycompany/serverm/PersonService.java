package com.mycompany.serverm;


 

 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;


 



@Service("userService")
public class PersonService {
	 
	//private  static List<Person> persons;
	 
      
    public static ArrayList<Person> findAllPersons() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        ArrayList<Person>a=new ArrayList();
   
      Properties props = new Properties();
FileInputStream in 
       = new FileInputStream("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\ServerM\\src\\main\\java\\com\\mycompany\\serverm\\db.properties");
            props.load(in);
       
in.close();

String driver = props.getProperty("jdbc.driver");
if (driver != null) {
    Class.forName(driver) ;
}

String url = props.getProperty("jdbc.url");
String username = props.getProperty("jdbc.username");
String password = props.getProperty("jdbc.password");

Connection con = DriverManager.getConnection(url, username, password);

String query = "SELECT * FROM  Person";

     
      Statement st = con.createStatement();
      
       
      ResultSet rs = st.executeQuery(query);
      
      
      while (rs.next())
      {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String location= rs.getString("location");
     
        Person p=new Person(id,name,location);
        a.add(p);
        System.out.println(id);
          System.out.println(name);
          System.out.println(location);
      }
      st.close();
    
        return a;
                
                
        
   
   
    }

 

    void save(Person p) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        
       Properties props = new Properties();
FileInputStream in 
       = new FileInputStream("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\ServerM\\src\\main\\java\\com\\mycompany\\serverm\\db.properties");
            props.load(in);
       
in.close();

String driver = props.getProperty("jdbc.driver");
if (driver != null) {
    Class.forName(driver) ;
}

String url = props.getProperty("jdbc.url");
String username = props.getProperty("jdbc.username");
String password = props.getProperty("jdbc.password");

Connection con = DriverManager.getConnection(url, username, password);
String sql = " insert into Person(id,name, location)"
        + " values (?, ?, ?)";

 PreparedStatement preparedStmt = con.prepareStatement(sql);
      preparedStmt.setInt (1, p.getId());
      preparedStmt.setString (2, p.getName());
      preparedStmt.setString   (3, p.getLocation());
      preparedStmt.execute();

 con.close();

  
}

   void deleteById(int id) {   PreparedStatement statement = null;
         try
    {
       
   
         Properties props = new Properties();
FileInputStream in 
       = new FileInputStream("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\ServerM\\src\\main\\java\\com\\mycompany\\serverm\\db.properties");
            props.load(in);
       
in.close();

String driver = props.getProperty("jdbc.driver");
if (driver != null) {
    Class.forName(driver) ;
}

String url = props.getProperty("jdbc.url");
String username = props.getProperty("jdbc.username");
String password = props.getProperty("jdbc.password");

 
Connection conn = DriverManager.getConnection(url, username, password); 

      
     
     String query = "DELETE FROM Person WHERE id=?;";
          
            statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            int r = statement.executeUpdate();

    
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }

  }
    
}
    




    

  
 
  

  
