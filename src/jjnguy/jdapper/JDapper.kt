package jjnguy.jdapper

import java.lang.reflect.Field
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.ArrayList

class JDapper(private val conn: Connection) {

  fun <T> query(sql: String, type: Class<T>, vararg params: Any): List<T> {
    val results = ArrayList<T>()

    val fields = type.declaredFields
    for (field in fields) {
      field.isAccessible = true
    }

    val statement = conn.prepareStatement(sql)
    for (i in params.indices) {
      statement.setObject(i + 1, params[i])
    }

    val rs = statement.executeQuery()

    while (rs.next()) {
      val obj = type.getConstructor().newInstance()

      for (field in fields) {
        val data = rs.getObject(field.name)
        field.set(obj, data)
      }

      results.add(obj)
    }

    return results
  }

  fun insert(tableName: String, data: Any) {
    val type = data.javaClass
    val fields = type.fields

    var columnNames = StringBuilder()
    var values = StringBuilder()
    for (field in fields) {
      columnNames.append(",").append(field.name)
      values.append(",?")
    }
    columnNames = StringBuilder(columnNames.substring(1))
    values = StringBuilder(values.substring(1))

    val sql = String.format(RAW_INSERT, tableName, columnNames.toString(), values.toString())

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
