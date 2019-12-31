package de.smartiis.webservice

import java.lang.StringBuilder
import java.security.SecureRandom

object SecureIdGenerator {
  private const val NUMBERS = "123456789"
  private const val ID_LENGTH = 7

  fun next(): Int {
    val random = SecureRandom()
    val bytes = ByteArray(ID_LENGTH)
    random.nextBytes(bytes)
    val builder = StringBuilder(ID_LENGTH)

    for (byte in bytes) {
      val value = positiveMod(byte.toInt(), NUMBERS.length)
      builder.append(NUMBERS[value])
    }

    return builder.toString().toInt()
  }

  private fun positiveMod(n: Int, mod: Int): Int {
    val result = n % mod
    return if (result < 0) result + mod else result
  }
}
