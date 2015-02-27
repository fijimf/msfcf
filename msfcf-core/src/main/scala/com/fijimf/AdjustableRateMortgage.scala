package com.fijimf

import org.joda.time.{Months, LocalDate}

case class AdjustableRateMortgage
(
  originalBalance: Double,
  originalTerm: Int,
  origDate: LocalDate,
  firstPayDate: LocalDate,
  currentBalance: Double,
  currentTerm: Int,
  currentRate: Double,
  index: String,
  margin: Double,
  nextRateReset: LocalDate,
  rateResetFreq: Int,
  periodicCapFloor: Double,
  lifetimeCap: Double,
  lifetimeFloor: Double
  ) extends Asset[AdjustableRateMortgage, ArmContext] {

  def amortize(z: AccrualPeriod, ctx: ArmContext): (AdjustableRateMortgage, Cashflow) = {
    val payment = levelPayment(currentRate / 12.0, currentTerm) * currentBalance
    val interest = currentRate * (1.0 / 12.0) * currentBalance
    val principal = Math.min(currentBalance, payment - interest)
    val nextAccrual: AccrualPeriod = z.copy(period = z.period + 1, startDate = z.endDate, endDate = z.endDate.plusMonths(1))
    val c = Cashflow(nextAccrual, principal, interest, 0)
    val newArm: AdjustableRateMortgage = copy(currentBalance = currentBalance - principal, currentTerm = currentTerm - 1)
    if (c.accrualPeriod.startDate.isEqual(nextRateReset) || c.accrualPeriod.startDate.isAfter(nextRateReset)) {
      (resetRate(ctx, newArm), c)
    } else {
      (newArm, c)
    }
  }

  def resetRate(ctx: ArmContext, arm: AdjustableRateMortgage): AdjustableRateMortgage = {
    val indexValue: Double = ctx.indexValue(index, nextRateReset)
    val chg: Double = ((indexValue + margin / 100.0) / 100.0) - currentRate
    val newRate = currentRate + Math.min(Math.max(-periodicCapFloor/100, chg), periodicCapFloor/100)
    arm.copy(currentRate = Math.max(Math.min(newRate, lifetimeCap/100), lifetimeFloor/100), nextRateReset = nextRateReset.plusMonths(12 / rateResetFreq))
  }


  def amortize(ctx: ArmContext): Stream[(AdjustableRateMortgage, Cashflow)] = {
    Stream.iterate(amortize(AccrualPeriod(0, origDate, origDate, 0), ctx))((tuple: (AdjustableRateMortgage, Cashflow)) => {
      tuple._1.amortize(tuple._2.accrualPeriod, ctx)
    })
  }

  override def cashflows(ctx: ArmContext): Stream[(AdjustableRateMortgage, Cashflow)] = {
    amortize(ctx)
  }


}