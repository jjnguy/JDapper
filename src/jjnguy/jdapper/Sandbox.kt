package jjnguy.jdapper

import java.lang.reflect.Field


fun Array<Field>.makeAccessible(): Array<Field> {
  forEach { field ->
    field.isAccessible = true
  }

  return this
}

fun String.upper(numberToUpper: Int): String {
  return this.substring(0, numberToUpper).toUpperCase() + this.substring(numberToUpper)
}
