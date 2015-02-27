package com.fijimf

import org.joda.time.LocalDate

class ArmContext {
   def indexValue(indexName:String, date:LocalDate):Double = 1.0+(2*date.getDayOfMonth/100.0) //Bogus value just for testing
}
