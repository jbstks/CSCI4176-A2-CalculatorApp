# CalculatorApp

### Description
**CSCI 4176: Mobile Computing (Fall 2018) - Assignment 2**

A basic calculator with add/subtract/multiply/divide and memory functionality.

### Features
- A 10-digit display where the user can view the input and output of their calculations. 
The default value shown is `0`. The sign (for negative numbers) and decimal point do not count as digits.
- Calculations that have more than two operands (e.g., `3 + 4 + 5`) will display the result of the first operation (such that `3 + 4` displays `7`) and continue to display the result for all subsequent operations until the user completes their calculation by pressing the `=` button.

### Basic Buttons
|Buttons  |Description							            |
|:-------:|-------------------------------------|
|`0` - `9`|Digits from `0` to `9`				        |
|`+`	    |Add									                |
|`-`	    |Subtract								              |
|`×`	    |Multiply								              |
|`÷`	    |Divide								                |
|`=`	    |Calculates and displays result		    |
|`.`	    |Decimal point						            |
|`±`	    |Toggles the sign on the current value|

### Extra Buttons
|Buttons  |Description							                                           |
|:-------:|--------------------------------------------------------------------|
|`C`	    |Clears/resets the calculator; the display will be set to `0`, any ongoing computation will be terminated, and the memory will be reset to store `0`|
|`MC`	    |Clears the memory; the default memory value is `0`                  |
|`MR`	    |Recall the value stored in the memory `MR`                          |
|`M+`	    |Adds a value to memory; such that `5 M+ 2 M+ MR` displays `7`       |
|`M-`	    |Subtracts a value from memory; such that `5 M+ 2 M- MR` displays `3`|

### Notes
- When an operator is pressed, the output gets reset to `0` as feedback that an operator was pressed.
- Some content from this README was taken from the Assignment 2 PDF on Brightspace.
