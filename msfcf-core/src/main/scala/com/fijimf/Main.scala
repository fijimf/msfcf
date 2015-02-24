package com.fijimf

import org.joda.time.LocalDate

object Main {
  def main(args: Array[String]) {

    val frm: FixedRateMortgage = FixedRateMortgage(500000, 360, 500000,360, 0.045)

    frm.amortize(new LocalDate(2015,2,5),new LocalDate(2015,3,1)).take(365).foreach(println _)
  }
}
