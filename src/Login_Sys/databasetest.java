package Login_Sys;
import java.sql.*;
public class databasetest {
	public static void main(String arg[]) {
		try{
			
			// 1. Create a connection to database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginApp","root","toor");
			// 2. Create  a statement
			Statement statement = conn.createStatement();
			// 3. Execute SQL query
			ResultSet set = statement.executeQuery("select * from login");
			// 4. Process the result set
			while(set.next()) {
				System.out.println(set.getString("username")+" , "+set.getString("password"));
			}
			
			System.out.println("Connection created");
		} catch(Exception e) {
			System.out.println("No Connection created");
		}
	}
}
