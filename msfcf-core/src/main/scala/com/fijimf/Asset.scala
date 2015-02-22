package com.fijimf

trait Asset {

  def levelPayment(i: Double, n: Int) = i + (i / (Math.pow(1 + i, n) - 1))
}

case class FixedRateMortgage(originalBalance: Double, originalTerm: Int, currentBalance: Double, currentTerm: Int, rate: Double) extends Asset {
  def age: Int = originalTerm - currentTerm

  def factor: Double = currentBalance / originalBalance

  val payment: Double = levelPayment(rate / 12.0, originalTerm)

  def amortize: (FixedRateMortgage, Payment) = {
    val interest = (rate / 12.0) * currentBalance
    val principal = Math.min(currentBalance, payment - interest)
    val c = Payment(principal, interest, 0)
    (copy(currentBalance = currentBalance - principal, currentTerm = currentTerm - 1), c)
  }

}

case class AdjustableRateMortgage(originalTerm: Int, originalRate: Double)