package jjnguy;

import java.sql.Connection;
import java.sql.DriverManager;

import jjnguy.jdapper.JDapper;

public class Main {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3307/";
		String dbName = "test"; //"world";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "jjnguy";
		String password = "asdf1234";
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		JDapper jd = new JDapper(conn);

		Stuff s = new Stuff();
		s.string1 = "Hi youtube";
		s.number1 = 42;
		
		jd.insert("stuff", s);
	}
}
