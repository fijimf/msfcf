package com.fijimf

import org.joda.time.LocalDate

object Main {
  def main(args: Array[String]) {

    val frm: FixedRateMortgage = FixedRateMortgage(500000, 360, new LocalDate(2015, 2, 5), new LocalDate(2015, 3, 1), 500000, 360, 0.045)

    frm.amortize().take(365).foreach(println _)
    val arm: AdjustableRateMortgage = AdjustableRateMortgage(500000, 360, new LocalDate(2015, 2, 5), new LocalDate(2015, 3, 1), 500000, 360, 0.045, "LIBOR", 35, new LocalDate(2020, 3, 1), 1, 0.25, 8, 0.25)

    arm.amortize(new ArmContext).take(365).foreach(println _)
  }
}
