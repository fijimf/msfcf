package com.fijimf

import org.joda.time._

trait DayCounter {
  def yearFraction(d1: LocalDate, d2: LocalDate): Double
}

object Actual365 extends DayCounter {
  override def yearFraction(d1: LocalDate, d2: LocalDate): Double = Days.daysBetween(d2, d1).getDays / 365.0
}

object Thirty360 extends DayCounter {
  override def yearFraction(d1: LocalDate, d2: LocalDate): Double = {
    val m1 = d1.withDayOfMonth(Math.min(d1.dayOfMonth().get(), 30))
    val m2 = m1.dayOfMonth().get() match {
      case 30 => d2.withDayOfMonth(Math.min(d2.dayOfMonth().get(), 30))
      case n => d2
    }
    (360 * (m1.year().get() - m2.year().get()) + 30 * (m1.monthOfYear().get() - m2.monthOfYear().get()) + (m1.dayOfMonth().get() - m2.dayOfMonth().get())) / 360.0
  }
}
