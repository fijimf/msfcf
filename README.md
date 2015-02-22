# msfcf
##Modelling Structured Finance Cashflows, not with Microsoft Excel

This is an attempt to work thorugh the basic ideas in the the book _Modelling Structured Finance Cashflows with Microsoft Excel_ by Keith Allman, using Scala and some basic functional programming constructs rather than Microsoft Excel.  

I don't expect anything groundbreaking -- this is more of a a series of programming and design katas than a product.

###Input and Output
Since the ppoint of the exercise is to eschew Excel and VBA, it makes no sense to use spreadsheets (Excel, OpenOffice or even CSV's) as either input or output.  Instead I have opted for JSON, which has certain characteristics which make it desirable:
1. extensive & comprehensive library support in most modern languages
2. semi-structured syntax
3. Ease of integration with web browsers bidirectionally.

###Excel Functions
In the text where the author relies on an Excel internal function, I may punt (at least for the time being.)  For example implementing every day counter under the sun is not what I am particularly interested in right now.  However I should be able to define an interface, stub it out and move forward

##Introduction
The three basic elements of a cashflow model are inputs, structure and outputs.  Makes sense.  Lets call or bit of software a CashflowEngine.  Then I think this looks like:

    CashflowEngine(inputs, structure)=outputs

FUNCTIONAL!

##Chapter 1

###Dates and Times
* We will use the Joda library to represent dates and times.
* We will describe a DayCounter interfa

=======
# msfcf
##Modelling Structured Finance Cashflows, not with Microsoft Excel

This is an attempt to work thorugh the basic ideas in the the book _Modelling Structured Finance Cashflows with Microsoft Excel_ by Keith Allman, using Scala and some basic functional programming constructs rather than Microsoft Excel.  

I don't expect anything groundbreaking -- this is more of a a series of programming and design katas than a product.

###Input and Output
Since the ppoint of the exercise is to eschew Excel and VBA, it makes no sense to use spreadsheets (Excel, OpenOffice or even CSV's) as either input or output.  Instead I have opted for JSON, which has certain characteristics which make it desirable:
1. extensive & comprehensive library support in most modern languages
2. semi-structured syntax
3. Ease of integration with web browsers bidirectionally.

###Excel Functions
In the text where the author relies on an Excel internal function, I may punt (at least for the time being.)  For example implementing every day counter under the sun is not what I am particularly interested in right now.  However I should be able to define an interface, stub it out and move forward

##Introduction
The three basic elements of a cashflow model are inputs, structure and outputs.  Makes sense.  Lets call or bit of software a CashflowEngine.  Then I think this looks like:

    CashflowEngine(inputs, structure)=outputs

FUNCTIONAL!

##Chapter 1

###Dates and Times
* We will use the Joda library to represent dates and times.
* We will describe a DayCounter interface.

Chapter 1 is straight forward, but a couple of things pop up which are not immediately addressed.
* Its not clear the methodology for writing our own Day Counters.  We will stub out an actual/actual counter, and presumably when the time comes we can use Wikipedia to help us out there.
* Its is also not clear the methodology EDATE uses for incrementing dates.  Certainly a cashflow date has to be a business date, and some of those dates are weekends or holidays.  A date adjustment strategy needs to be put in place for when a cashflowdate lands ona holiday or weekend.  Additionally we need a method of tracking holidays.
* Finally, while we're not actually up to actual cashflows, we do create rows of period number and date.  How many?  We don't have a deal, and he makes no mention of how we arrive at the number of 'cashflow' rows.  This final issue, however gives us an opportunity to take advantage of a great functional programming construct: streams.  Wioth streams the cashflow list is described recursively -- just a as it os in Excel.


There is some stuff about validation that we sould take seriously, but right now, we're skipping by.  On to Cahpter 2

##Chapter 2

###Loans vs. Replines
Loans versu replines is an interesting question in its own right, but one which like the author we will put off for a later date.  We will assume for now that if we have generated replines, they will contain roughly the same informationas loans themselves, at least insofar as calculation of monthly payments.  

###ARM vs Fixed
We are going to talke the simplification of the author and for simplicity's sake assume that we are only dealing with residential mortgages.  The big distinction there is between fixed rate and adjustable rate.

####Fixed rate
for fixed rate mortgages, the interest rate and term explicitly determine the level payment to maturity.  The author uses the Microsoft Excel function for that, but we have to code it up. I don't have the level pay formula in my head and don't feel like deriving it -- Google is our frined here:
A = P\frac{i(1 + i)^n}{(1 + i)^n - 1} = \frac{P \times i}{1 - (1 + i)^{-n}} = P\left(i + \frac{i} {(1 + i)^n - 1}\right)
Where:

A = periodic payment amount
P = amount of principal, net of initial payments, meaning "subtract any down-payments"
i = periodic interest rate
n = total number of payments


####Adjustable Rate
ARMs have 


