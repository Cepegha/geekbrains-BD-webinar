import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Admin on 04.12.2016.
 */
//� �������� ����� ����� ��������� ��������� ����� � �������� ������, ������ � ��. � ������������� �� ���� ��� ������ � �����

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
                int key = resultSet.getInt("id_model");//����� ��� ��� �������
                String val = resultSet.getString("model_name");
                System.out.println("key = "+key);
                System.out.println("val = "+val);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //��������� ���������� � ��
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
