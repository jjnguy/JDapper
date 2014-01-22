import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3307/";
		String dbName = "world";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "jjnguy";
		String password = "asdf1234";
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName, password);
		JDapper jd = new JDapper(conn);

		Scanner sin = new Scanner(System.in);
		System.out.println("Please enter in a population: ");
		String pop = sin.nextLine();
		
		String sql = "SELECT * FROM Country WHERE Population < ? LIMIT 20";

		List<Country> results = jd.query(sql, Country.class, pop);
		for (Country country : results) {
			System.out.println(country.getName());
		}

		conn.close();
	}
}
