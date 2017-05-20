package jjnguy.jdapper;

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

    public <T> List<T> query(String sql, Class<T> type, Object... params) throws Exception {
        List<T> results = new ArrayList<>();

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        PreparedStatement statement = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            T obj = type.getConstructor().newInstance();

            for (Field field : fields) {
                Object data = rs.getObject(field.getName());
                field.set(obj, data);
            }

            results.add(obj);
        }

        return results;
    }

    private static final String RAW_INSERT = "INSERT INTO %s (%s) VALUES (%s);";

    public void insert(String tableName, Object data) throws Exception {
        Class<?> type = data.getClass();
        Field[] fields = type.getFields();

        StringBuilder columnNames = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Field field : fields) {
            columnNames.append(",").append(field.getName());
            values.append(",?");
        }
        columnNames = new StringBuilder(columnNames.substring(1));
        values = new StringBuilder(values.substring(1));

        String sql = String.format(RAW_INSERT, tableName, columnNames.toString(), values.toString());

        PreparedStatement statement = conn.prepareStatement(sql);

        for (int i = 0; i < fields.length; i++) {
            statement.setObject(i + 1, fields[i].get(data));
        }

        statement.execute();
    }
}
