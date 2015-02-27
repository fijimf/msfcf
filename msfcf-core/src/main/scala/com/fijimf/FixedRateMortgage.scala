package com.fijimf

import org.joda.time.LocalDate

case class FixedRateMortgage(originalBalance: Double, originalTerm: Int, origDate: LocalDate, firstPayDate: LocalDate, currentBalance: Double, currentTerm: Int, rate: Double) extends Asset[FixedRateMortgage, AssetContext] {
  def age: Int = originalTerm - currentTerm

  def factor: Double = currentBalance / originalBalance

  val periodicPayment: Double = levelPayment(rate / 12.0, originalTerm) * originalBalance

  def amortize(z: AccrualPeriod): (FixedRateMortgage, Cashflow) = {
    val interest = rate * (1.0 / 12.0) * currentBalance
    val principal = Math.min(currentBalance, periodicPayment - interest)
    val nextAccrual: AccrualPeriod = z.copy(period = z.period + 1, startDate = z.endDate, endDate = z.endDate.plusMonths(1))
    val c = Cashflow(nextAccrual, principal, interest, 0)
    (copy(currentBalance = currentBalance - principal, currentTerm = currentTerm - 1), c)
  }

  def amortize(): Stream[(FixedRateMortgage, Cashflow)] = {
    Stream.iterate(amortize(AccrualPeriod(0, origDate, origDate, 0)))((tuple: (FixedRateMortgage, Cashflow)) => {
      tuple._1.amortize(tuple._2.accrualPeriod)
    })
  }

  def cashflows(ctx: AssetContext): Stream[(FixedRateMortgage, Cashflow)] = {
    amortize()
  }
}
