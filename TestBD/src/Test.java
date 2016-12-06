import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Admin on 04.12.2016.
 */
//в проектах лучше всего создавать отдельный класс с методами инсерт, селект и тд. и наследоваться от него для работы с базой

public class Test {
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost/cars";
        Connection con = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        }
        catch (Exception e) {
            System.out.println("Error of loading new the driver");
            return;
        }

        try {
            con = DriverManager.getConnection(url, "root","455001");
            Statement select = con.createStatement();
            ResultSet resultSet = select.executeQuery("select * from models");
            while(resultSet.next()){
                int key = resultSet.getInt("id_model");//номер или имя столюца
                String val = resultSet.getString("model_name");
                System.out.println("key = "+key);
                System.out.println("val = "+val);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //закрываем соединение с бд
        finally {
            if (con!=null){
                try {
                    con.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
