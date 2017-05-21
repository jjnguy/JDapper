package jjnguy.jdapper

fun higherOrder1(function: Int.() -> String) {
  val str: String = function(6)
}

fun run() {

  higherOrder1({ toString() })

}
