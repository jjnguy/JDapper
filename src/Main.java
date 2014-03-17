import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jdapper.JDapper;
import jdapper.JDapperStream;
import jdapper.stream.FilteredStream;

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
		
		Stream<String> strs = (Arrays.asList("a", "1234", "12345")).stream();
		Stream<String> filtered = new FilteredStream<>(strs, item -> item.length() > 4);
		List<String> list = filtered.collect(Collectors.toList());
		
		Stuff s = new Stuff();
		s.string1 = "Hi youtube";
		s.number1 = 42;
		
		//jd.insert("stuff", s);
	}
}
