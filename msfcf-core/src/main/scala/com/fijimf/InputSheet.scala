package com.fijimf

import org.joda.time.LocalDate

/* This is a potentially ill-named class, but I am on page one of the book, so for the time being we will keep it. */
case class InputSheet(closingDate: LocalDate, firstPaymentDate: LocalDate, dayCount: DayCounter, frequency: Int) {
  def zeroethCashflow: Cashflow = Cashflow(0, closingDate, 0);

  def nextCashflow(c: Cashflow): Cashflow = {
    c.period match {
      case 0 => Cashflow(1, firstPaymentDate, dayCount.yearFraction(firstPaymentDate, closingDate))
      case n =>
        val date: LocalDate = c.date.plusMonths(12 / frequency)
        Cashflow(n + 1, date, dayCount.yearFraction(date, c.date))
    }
  }

  def cashflows():Stream[Cashflow] = {
    Stream.iterate(zeroethCashflow)(nextCashflow)
  }
}


