# msfcf
Modelling Structured Finance Cashflows, not in Excel

This is an attempt to work thorugh the basic ideas in the the book Modelling Structured Finance Cashflows with Microsoft Excel by Keith Allman, using Scala and some basic functional programming constructs rather than Microsoft Excel.  

I don't expect anything groundbreaking -- this is more of a a series of programming and design katas than a product.

Input and Output
Since the ppoint of the exercise is to eschew Excel and VBA, it makes no sense to use spreadsheets (Excel, OpenOffice or even CSV's) as either input or output.  Instead I have opted for JSON, which has certain characteristics which make it desirable:
1) extensive & comprehensive library support in most modern languages; 2) semi-structured syntax; 3) Ease of integration with web browsers bidirectionally.

Excel Functions
In the text where the author relies on an Excel internal function, I may punt (at least for the time being.)  For example implementing every day counter under the sun is not what I am particularly interested in right now.  However I should be able to define an interface, stub it out and move forward

Introduction
The three basic elements of a cashflow model are inputs, structure and outputs.  Makes sense.  Lets call or bit of software a CashflowEngine.  Then I think this looks like:

CashflowEngine(inputs, structure)=outputs

FUNCTIONAL!



