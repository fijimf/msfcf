package com.fijimf

import org.joda.time.LocalDate

/* This is a potentially ill-named class, but I am on page one of the book, so for the time being we will keep it. */
case class InputSheet(closingDate:LocalDate, firstPaymentDate:LocalDate, dayCount:String, frequency:Int) {

}
