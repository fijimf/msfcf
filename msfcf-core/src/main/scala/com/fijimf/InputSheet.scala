package com.fijimf

import org.joda.time.LocalDate

/* This is a potentially ill-named class, but I am on page one of the book, so for the time being we will keep it. */
case class InputSheet(closingDate: LocalDate, firstPaymentDate: LocalDate, dayCount: DayCounter, frequency: Int) {
  def zeroethCashflow: AccrualPeriod = AccrualPeriod(0, closingDate, closingDate, 0);

  def nextCashflow(c: AccrualPeriod): AccrualPeriod = {
    c.period match {
      case 0 => AccrualPeriod(1, closingDate, firstPaymentDate, dayCount.yearFraction(firstPaymentDate, closingDate))
      case n =>
        val date: LocalDate = c.endDate.plusMonths(12 / frequency)
        AccrualPeriod(n + 1, c.endDate, date, dayCount.yearFraction(date, c.endDate))
    }
  }

  def cashflows():Stream[AccrualPeriod] = {
    Stream.iterate(zeroethCashflow)(nextCashflow)
  }
}


