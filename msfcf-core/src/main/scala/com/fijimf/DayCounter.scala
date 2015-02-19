package com.fijimf

import org.joda.time.LocalDate

trait DayCounter {
   def yearFraction(d1:LocalDate, d2:LocalDate):Double
}
