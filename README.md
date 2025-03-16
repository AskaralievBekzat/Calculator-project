1. Project Overview

The console-based calculator is a Java program that performs basic arithmetic operations and additional mathematical functions. It allows users to input expressions dynamically, evaluates them, and displays results. The program also maintains a history of calculations and handles errors gracefully.

⸻

2. Design Choices

2.1 Core Functionalities

The calculator supports:
• Arithmetic operations: Addition (+), Subtraction (-), Multiplication (*), Division (/), and Modulus (%).
• Mathematical functions:
• power(base, exponent): Computes the exponentiation of a base number.
• sqrt(number): Computes the square root of a given number.
• abs(number): Returns the absolute value.
• round(number): Rounds a number to the nearest integer.
• User-friendly interface: Accepts input dynamically and provides meaningful error messages.
• History feature: Stores previous calculations for future reference.

2.2 Algorithmic Approach
• Regex-based Parsing: We use Regular Expressions (Regex) to identify and extract function calls (e.g., power(2,3)) and arithmetic expressions.
• String Handling: The program removes whitespace and processes expressions systematically.
• Switch Expressions: Arithmetic operations are handled using a switch statement, improving readability and efficiency.
• Exception Handling: The program catches and displays errors like:
• Divide by zero
• Invalid input format
• Negative square roots
• Syntax errors in function calls

2.3 Data Structures Used
• ArrayList (List<String> history): Stores calculation history.
• Regular Expressions (Pattern and Matcher): Extract numerical values and operations from user input.
• String Parsing (String.split() and replaceAll()): Cleans and formats user input.

⸻

3. Challenges Encountered

3.1 Handling Function Inputs

The initial approach attempted to split input using simple string methods. However, complex functions like power(2,3) required Regex matching to properly extract numerical arguments.

3.2 Error Handling

Ensuring robust error messages for incorrect expressions was difficult. The solution involved:
• Implementing custom exceptions for cases like division by zero.
• Using try-catch blocks to prevent crashes.

3.3 Handling Multiple Data Types

The calculator had to work with both integers and decimals. Java’s Double.parseDouble() method ensured that numbers were parsed correctly, but operations like round() required explicit type conversion.