package Kursovaya.src;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DB {

	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	
	DB(String db) throws ClassNotFoundException, SQLException {
		//для связи программы с СУБД подключаем соответствующий драйвер
		// http://dev.mysql.com/downloads/connector/j/3.0.html
		//далее с помощью класса коннекшт загружаем драйвер
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/" + db ;
		//для соединения с БД используем класс Connection где URL имеет формат jdbc:mysql://сервер/база_данных
		// Connection dbh = DriverManager.getConnection(url, user, passwd);
		con = (Connection) DriverManager.getConnection(url, "root", "455001");
		
		//2 основных метода класса Statement для работы с БД
		// executeQuery - мы ожидаем ответ от БД - select
		// executeUpdate - ответ не обязателен - update, create, insert, delete
		stmt = (Statement) con.createStatement();
	}
	
	int CountStr(String zapros) throws SQLException {
		int colStr  = 0;
		rs = stmt.executeQuery(zapros);
		while (rs.next()) {
			colStr++;
		}
		return colStr;
	}
	
	int CountStb(String table) throws SQLException {
		String zapros = "SELECT * FROM " + table;
		rs = stmt.executeQuery(zapros);
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		return numberOfColumns;
		
	}
	
	int CountStb2(String zapros) throws SQLException {
		rs = stmt.executeQuery(zapros);
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		return numberOfColumns;
		
	}
	
	String CountMax(String table, String kolonka) throws SQLException {
		String zapros = "SELECT max(" + kolonka + ") FROM " + table;
		rs = stmt.executeQuery(zapros);
		try {
			if (rs.next())
				return (rs.getString(1));
		}
		catch (Exception e) {
			e.getMessage();
		}
		return "";		
	}
	
	String CountMin(String table, String kolonka) throws SQLException {
		String zapros = "SELECT min(" + kolonka + ") FROM " + table;
		rs = stmt.executeQuery(zapros);
		try {
			if (rs.next())
				return (rs.getString(1));
		}
		catch (Exception e) {
			e.getMessage();
		}
		return "";		
	}
	
	 String select(String zapros) throws Exception {
	  this.rs = this.stmt.executeQuery(zapros);
	  int x = this.CountStb2(zapros);
	  ResultSetMetaData rsm = this.rs.getMetaData();
	  String[][] Data = new String[CountStr(zapros) + 1][CountStb2(zapros)];

	  int y = CountStb2(zapros);
	  int CountColumn = 1;
	  int j = 0;
	  while(CountStb2(zapros) >= CountColumn ) {
		  	  if (rs.next())
			  Data[0][j] = rs.getString(j+1);
			  System.out.println(Data[0][j]);
			  j++;
		  
		  //j = 0;
	  }
	  /*while(CountStb2(zapros) >= CountColumn) {
		  Stroka += rsm.getColumnName(CountColumn) + "    "; 
		  CountColumn++;
	  }
	  
	  while(rs.next()){
		  
		  for(int i = 1; i <= x;i++){
			  if(i % y==0) Stroka+=rs.getString(i)+ "\r\n";
			  else Stroka+=rs.getString(i)+"    ";
		  } 
	  }*/
	  
	  return "";
	  }
	 
	 String query(String zapros) throws SQLException {
		 if (stmt.executeUpdate(zapros) > 0) return "Ok";
		 return "Error";
	 }
}
