package jee2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Example 3.1.
 */
public class Test {
  public static void main(String args[]) {
    String url = "jdbc:mysql://localhost/cars";
    Connection con = null;

    try {
      String driver = "com.mysql.jdbc.Driver";

      Class.forName(driver).newInstance();
    } catch (Exception e) {
      System.out.println("Failed to load mySQL driver.");
      return;
    }
    try {
      con = DriverManager.getConnection(url, "root", "455001");
      Statement select = con.createStatement();
      ResultSet result = select
          .executeQuery("SELECT * FROM models");

      System.out.println("Got results:");
      while (result.next()) { // process results one row at a time
        int key = result.getInt(1);
        String val = result.getString("model_name");

        System.out.println("key = " + key);
        System.out.println("val = " + val);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
