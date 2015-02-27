package com.fijimf

import org.joda.time.LocalDate

trait Asset[K,C] {
  def levelPayment(i: Double, n: Int) = i + (i / (Math.pow(1 + i, n) - 1))

  def cashflows(context:C): Stream[(K,Cashflow)]
}



