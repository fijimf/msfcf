package com.fijimf

import org.joda.time.LocalDate

trait Asset {
  def levelPayment(i: Double, n: Int) = i + (i / (Math.pow(1 + i, n) - 1))

  def cashflows(closingDate: LocalDate, firstPayDate: LocalDate): Stream[Cashflow]
}

case class FixedRateMortgage(originalBalance: Double, originalTerm: Int, currentBalance: Double, currentTerm: Int, rate: Double) extends Asset {
  def age: Int = originalTerm - currentTerm

  def factor: Double = currentBalance / originalBalance

  val periodicPayment: Double = levelPayment(rate / 12.0, originalTerm) * originalBalance

  def zerothAccrualPeriod(closingDate: LocalDate, firstPayDate: LocalDate) = AccrualPeriod(0, closingDate, closingDate, 0)

  def zerothCashflow(closingDate: LocalDate, firstPayDate: LocalDate) = Cashflow(zerothAccrualPeriod(closingDate, firstPayDate), 0, 0, 0)

  def amortize(z: AccrualPeriod): (FixedRateMortgage, Cashflow) = {
    val interest = rate * (1.0 / 12.0) * currentBalance
    val principal = Math.min(currentBalance, periodicPayment - interest)
    val nextAccrual: AccrualPeriod = z.copy(period = z.period + 1, startDate = z.endDate, endDate = z.endDate.plusMonths(1))
    val c = Cashflow(nextAccrual, principal, interest, 0)
    (copy(currentBalance = currentBalance - principal, currentTerm = currentTerm - 1), c)
  }

  def amortize(closingDate: LocalDate, firstPayDate: LocalDate): Stream[(FixedRateMortgage, Cashflow)] = {
    Stream.iterate(amortize(zerothAccrualPeriod(closingDate, firstPayDate)))((tuple: (FixedRateMortgage, Cashflow)) => {
      tuple._1.amortize(tuple._2.accrualPeriod)
    })
  }

  def cashflows(closingDate: LocalDate, firstPayDate: LocalDate): Stream[Cashflow] = {
    amortize(closingDate, firstPayDate).map(_._2)
  }


}

case class AdjustableRateMortgage(originalTerm: Int, originalRate: Double)