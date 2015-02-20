package com.fijimf

import org.joda.time.LocalDate

object Main {
  def main(args: Array[String]) {
    val deal: InputSheet = InputSheet(new LocalDate(2015,2,5),new LocalDate(2015,3,1), Actual365, 12)

    deal.cashflows().take(36).foreach(println _)
  }
}
