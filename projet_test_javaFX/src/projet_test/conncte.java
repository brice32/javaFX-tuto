package projet_test;

import java.sql.*;

public class conncte {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
		      Class.forName("com.mysql.jdbc.Driver");     //¼ÓÔØMYSQL JDBCÇý¶¯³ÌÐò   
		      //Class.forName("org.gjt.mm.mysql.Driver");
		     System.out.println("Success loading Mysql Driver!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!");
		      e.printStackTrace();
		    }
		
        Connection connection=DriverManager.getConnection("jdbc:mysql://projeti2javawang.clepbqlz5cny.us-west-2.rds.amazonaws.com:3306/projet_test", "root", "3il3il3il");
        System.out.println("Success connect Mysql server!");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from utilisateur");
        while (result.next()) {
            System.out.println(result.getInt("id"));
          }

	}

}
