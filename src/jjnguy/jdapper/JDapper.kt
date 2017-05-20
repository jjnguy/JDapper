package jjnguy.jdapper

import java.lang.reflect.Field
import java.sql.Connection
import java.util.ArrayList

class JDapper(private val conn: Connection) {

  fun <TResult> query(sql: String, type: Class<TResult>, vararg params: Any): List<TResult> {

    val fields = type.declaredFields.apply {
      forEach { field ->
        field.isAccessible = true
      }
    }

    val statement = conn.prepareStatement(sql).apply {
      for (i in params.indices) {
        setObject(i + 1, params[i])
      }
    }

    val rs = statement.executeQuery()

    val results = ArrayList<TResult>()

    while (rs.next()) {
      results.add(type.getConstructor().newInstance().apply {
        fields.forEach {
          it.set(this, rs.getObject(it.name))
        }
      })
    }

    return results
  }

  fun insert(tableName: String, data: Any) {
    val type = data.javaClass
    val fields = type.fields

    var columnNames = ""
    var values = ""
    for (field in fields) {
      columnNames += "," + field.name
      values += ",?"
    }
    columnNames = columnNames.substring(1)
    values = values.substring(1)

    val sql = String.format(RAW_INSERT, tableName, columnNames, values)

    val statement = conn.prepareStatement(sql)

    for (i in fields.indices) {
      statement.setObject(i + 1, fields[i].get(data))
    }

    statement.execute()
  }

  companion object {

    private val RAW_INSERT = "INSERT INTO %s (%s) VALUES (%s);"
  }
}
