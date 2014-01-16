import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDapper {
	private final Connection conn;

	public JDapper(Connection conn) {
		this.conn = conn;
	}
	
	public <T> List<T> query(String sql, Class<T> type, Object...params) throws Exception {
		List<T> results = new ArrayList<>();
		
		Field[] fields = type.getFields();
		
		PreparedStatement statement = conn.prepareStatement(sql);
		
		for (int i = 0; i < params.length; i++) {
			statement.setObject(i+1, params[i]);
		}
		
		ResultSet rs = statement.executeQuery();
		
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
