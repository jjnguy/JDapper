import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDapper {
	private final Connection conn;

	public JDapper(Connection conn) {
		this.conn = conn;
	}
	
	public <T> List<T> query(String sql, Class<T> type) throws Exception {
		List<T> results = new ArrayList<>();
		
		Field[] fields = type.getFields();
		ResultSet rs = conn.createStatement().executeQuery(sql);
		while(rs.next()){
			T obj = type.getConstructor().newInstance();
			
			for (Field field : fields) {
				Object data = rs.getObject(field.getName());
				field.set(obj, data);
			}
			
			results.add(obj);
		}
		
		return results;
	}
}
