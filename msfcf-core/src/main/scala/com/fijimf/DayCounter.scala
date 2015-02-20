package com.fijimf

import org.joda.time.{Days, LocalDate}

trait DayCounter {
  def yearFraction(d1: LocalDate, d2: LocalDate): Double
}

object Actual365 extends DayCounter {
  override def yearFraction(d1: LocalDate, d2: LocalDate): Double = Days.daysBetween(d2, d1).getDays / 365.0
}
