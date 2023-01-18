/*
NOTE: This java program was submitted by myself previously as a part of the PDI worksheet 5 submission. 
PURPOSE: A program that provides various options to the user in the form of a menu, which then runs the particular program option after selection
AUTHOR: Lachlan Murray 
LAST MODIFIED: 3:24PM Apr 23 2020 
*/

import java.util.*;

public class Menu
{
    /* NAME: main
    PURPOSE: The main method. Contains the code for presenting and taking input for the main menu, calling the appropriate menu option's submodule when chosen
    IMPORTS: none
    EXPORTS: none
    ASSERTIONS: menuChoice is a valid integer choice between 0 and 9 (inclusive) */

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int menuChoice;
        do
        {
            String menuPrompt = "What would you like to do?\n\t> 1. Sum of 2 Integers\n\t> 2. Convert Temperature\n\t> 3. Convert a Character's case\n\t> 4. Print the ASCII equivalent of a character\n\t> 5. Check if 2 Integers are Divisible\n\t> 6. Split a date into its components\n\t> 7. Fill an array with random numbers and search it for one\n\t> 8. Make a times tables chart\n\t> 9. Multiply the two matrices in the external file Matrix.java\n\t> 0. Exit\n";
            int menuMin = 0, menuMax = 9;

            menuChoice = inputIntRange(menuPrompt, menuMin, menuMax);   //Calls int input validation module for given range

            switch(menuChoice)
            {
                case 1:
                    sumInt();   //Call sumInt submodule when 1st menu option chosen
                break;
                
                case 2:
                    tempConv(); //Call tempConv submodule when 2nd menu option chosen
                break;
                        
                case 3:
                    caseConv(); //Call caseConv submodule when 3rd menu option chosen
                break;
        
                case 4:
                    asciiConv();    //Call asciiConv submodule when 4th menu option chosen
                break;
                
                case 5:
                    divInt();   //Call divInt submodule when 5th menu option chosen
                break;
            
                case 6:
                    dateSplit();    //Calls dateSplit submodule when 6th menu option is chosen
                break;

                case 7:
                    arrays();   //Calls arrays submodule when the 7th menu option is chosen
                break;

                case 8:
                    twoDArrays();   //Calls twoDArrays submodule when 8th menu option is chosen
                break;

                case 9:
                    MatrixMultiplication(); //Calls MatixMultiplication submodule when 9th menu option is chosen
                break;        

                case 0:
                    System.out.println("Exiting program... ");
                break;
                
                default:
                    System.out.println();
            }
        }while (menuChoice != 0);  //Loops menu until exit option chosen
    }
    
    /* NAME: sumInt
    PURPOSE: The method for the first menu option, summing two integers and presenting the result to the user.
    IMPORTS: none
    EXPORTS: none */

    public static void sumInt()
    {
        Scanner sc = new Scanner(System.in);
        int numOne, numTwo, sum;    //Define the two variables for the ints to be summed, and the variable for the total
                
        System.out.print("Please enter the first number you wish to add: ");
        numOne = sc.nextInt();  //Takes input for numOne
            
        System.out.print("Please enter the second number you wish to add: ");
        numTwo = sc.nextInt();  //Takes input for numTwo
           
        sum = sumIntCalc(numOne, numTwo);  //Calculates sum with separate submodule

        System.out.print("The sum is: " + sum + "\n");  //Outputs value of sum
    }

    /* NAME: sumIntCalc
    PURPOSE: The method for performing the calculation step in the first menu option of summing two integers.
    IMPORTS: numOne (integer), numTwo (integer)
    EXPORTS: sum (integer) */
    
    public static int sumIntCalc(int numOne, int numTwo)
    {
        int sum;

        sum = numOne + numTwo;  //Addition calculation

        return sum; //Return sum to original submodule so it may be output
    }

    /* NAME: tempConv
    PURPOSE: The method for the second menu option, converting a temperature value in celcius to fahrenheit or vice-versa, having the result output to the user.
    IMPORTS: none
    EXPORTS: none
    ASSERTIONS: scale is a valid char between the options of 'c', 'C', 'f', and 'F'. */
    
    public static void tempConv()
    {
        Scanner sc = new Scanner(System.in);
        double c_in, f_in;  //Declare variables for real input of temp
        int c, f;   //Declare variables for output of C and F
        char scale; //Declare variable to store scale option
            
        String scalePrompt = "What temperature scale are you working with? \n\t>(C)elcius\n\t>(F)ahrenheit\n";  //Gives output text to the terminal asking for temp scale
        char scaleOptOne = 'f', scaleOptTwo = 'F', scaleOptThree = 'c', scaleOptFour = 'C'; //define the options the prompt provides
        scale = inputChar(scalePrompt, scaleOptOne, scaleOptTwo, scaleOptThree, scaleOptFour);  //Calls char input validation module

        if(scale == 'C'||scale == 'c')
        {
            System.out.print("Please enter the temperature in degrees Celcuis: ");  //Outputs text to the terminal asking for temp in C
            c_in = sc.nextDouble();   //Assigns the value of c from the input to the previous line
            f = farenCalc(c_in);   //Calculates f value from submodule
            System.out.print("The temperature in Fahrenheit is " + f + " degrees. \n");    //Outputs temperature in F
        }
        else if(scale == 'F'||scale == 'f')
        {
            System.out.print("Please enter the temperature in degrees Farhenheit: ");  //Outputs text to the terminal asking for temp in F
            f_in = sc.nextDouble();   //Assigns the value of f from the input to the previous line
            c = celsCalc(f_in);    //Calculates c value from submodule
            System.out.print("The temperature in Celcius is " + c + " degrees. \n");    //Outputs temperature in C
        }
    }

    /* NAME: farenCalc
    PURPOSE: The method for doing the conversion calculation from a temperature value in Celcius to one in Fahrenheit.
    IMPORTS: c_in (real)
    EXPORTS: f (integer) */

    public static int farenCalc(double c_in)
    {
        int f;
        
        f = (int)(((c_in*9.0)/5.0)+32.0);   //mathematical conversion from C to F

        return f;   //Return value of f to original submodule so it may be output
    }

    /* NAME: celsCalc
    PURPOSE: The method for doing the conversion calculation from a temperature value in Fahrenheit to one in Celcius.
    IMPORTS: f_in (real)
    EXPORTS: c (integer) */

    public static int celsCalc(double f_in)
    {
        int c;
        
        c = (int)(((f_in-32.0)*5.0)/9.0);   //mathematical conversion from F to C
        
        return c;   //Return value of c to the original submodule so it may be output
    }
    
    /* NAME: caseConv
    PURPOSE: The method for the third menu option, conversion from an uppercase character to a lowercase one or vice-versa, with the result output to the user.
    IMPORTS: none
    EXPORTS: none
    ASSERTIONS: choice is a valid char from the options 'u', 'U', 'l', or 'L'. */
    
    public static void caseConv()
    {
        Scanner sc = new Scanner(System.in);
        char choice, value, lower, upper;  //Initialise the two chars used in the program, value for the ascii value for the uppercase char, lower for the ascii value of the lowercase char, upper for the uppercase char
        int upperascii, lowerascii, ascii;  //Initialise the two ints used in the program, upperascii for the uppercase char, lowerascii for the lowercase char and ascii for the uppercase char    
        
        String choicePrompt = "Do you wish to convert (U)ppercase to lowercase, or (L)owercase to uppercase? ";
        char choiceOptOne = 'u', choiceOptTwo = 'U', choiceOptThree = 'l', choiceOptFour = 'L'; //Define char options that the above prompt provides

        choice = inputChar(choicePrompt, choiceOptOne, choiceOptTwo, choiceOptThree, choiceOptFour);    //Calls char input validation module

        if (choice == 'u'||choice == 'U')
        {
            System.out.print("Please enter an uppercase character: ");  //Requests input of uppercase character from user
            value = sc.next().charAt(0);    //Char value of input is read and stored in 'value'
            lower = toLower(value); //Converts using toLower submodule below this module
            System.out.println("The lowercase value of '" + value + "' is: '" + lower + "'");   //Outputs the corresponding lowercase char for the input uppercase char 'value'
        }   
        else if(choice == 'l'||choice == 'L')
        {
            System.out.print("Please enter an lowercase character: ");  //Requests input of lowercase character from user
            value = sc.next().charAt(0);    //Char value of input is read and stored in 'value'
            upper = toUpper(value); //Converts using toUpper submodule
            System.out.println("The uppercase value of '" + value + "' is: '" + upper + "'");   //Outputs the corresponding uppercase char for the input lowercase char 'value'
        }   
    }

    /* NAME: toLower
    PURPOSE: The method that performs the conversion from an uppercase character to a lowercase one.
    IMPORTS: value (character)
    EXPORTS: lower (character) */

    public static char toLower(char value)
    {
        int ascii, lowerascii;
        char lower;
        
        ascii = (int) value;    //Typecasts Char input to ASCII int value, stored in 'ascii'
        lowerascii = ascii + 32;    //Applies the formula (+32) to change the uppercase ASCII int value to its corresponding lowercase's ASCII int value
        lower = (char) lowerascii;  //Typecasts lowercase's ASCII int value to the lowercase char, stored in 'lower'
        
        return lower;   //Return lowercase char to original submodule so it may be output
    }

    /* NAME: toUpper
    PURPOSE: The method that performs the conversion from a lowercase character to an uppercase one.
    IMPORTS: value (character)
    EXPORTS: upper (character) */

    public static char toUpper(char value)
    {
        int ascii, upperascii;
        char upper;
       
        ascii = (int) value;    //Typecasts Char input to ASCII int value, stored in 'ascii'
        upperascii = ascii - 32;    //Applies the formula (-32) to change the lowercase ASCII int value to its corresponding uppercase's ASCII int value
        upper = (char) upperascii;  //Typecasts uppercase's ASCII int value to the uppercase char, stored in 'upper'
   
        return upper;   //Return uppercase char to the original submodule so it may be output
    }
 
    /* NAME: asciiConv
    PURPOSE: The method for the fourth menu option, converting a character to its ascii value, with the result output to the user.
    IMPORTS: none
    EXPORTS: none */

    public static void asciiConv()
    {
        Scanner sc = new Scanner(System.in);
        char value_asc; //Variable to hold the input character
        int ascii_asc;  //Variable to hold the output ASCII value

        System.out.print("Please enter a character to convert: ");
        value_asc = sc.next().charAt(0);    //Takes character input

        ascii_asc = toAscii(value_asc); //Calls toAscii submodule to convert to ascii

        System.out.println("The ASCII value of '" + value_asc + "' is: " + ascii_asc);  //Outputs ASCII int value
    }

    /* NAME: toAscii
    PURPOSE: The method that performs the conversion calculation to convert from character to ascii value.
    IMPORTS: value_asc
    EXPORTS: ascii_asc */

    public static int toAscii(char value_asc)
    {
        int ascii_asc;
 
        ascii_asc = (int) value_asc;    //Typecasts the input char to ASCII int value

        return ascii_asc;   //Return ascii value to original submodule so it may be output
    }

    /* NAME: divInt
    PURPOSE: The method for the fifth menu option, checking if two numbers are divisible, with the verdict output to the user.
    IMPORTS: none
    EXPORTS: none
    ASSERTIONS: numTwo_div is not 0 and a valid integer denominator. */
    
    public static void divInt()
    {
        Scanner sc = new Scanner(System.in);
        int numOne_div, numTwo_div; //Initialise the two numbers to be used in division

        System.out.print("Please enter the integer you wish to divide: "); //Ask for input of number to divide
        numOne_div = sc.nextInt(); //Store integer to divide in numOne
        
        String divPrompt = "Please enter the integer you wish to divide by: "; //line to ask for input of number to divide by
        int divCannotBe = 0;
        numTwo_div = inputIntEquals(divPrompt, divCannotBe);    //Calls the int input validation submodule to check if the denomminator of the equation isn't zero
            
        if (numOne_div%numTwo_div == 0) //If the two numbers are divisible, tell the user
        {
            System.out.println(numOne_div + " is divisible by " + numTwo_div);
        }
        else //If the two numbers are not divisible, tell the user
        {
            System.out.println(numOne_div + " is not divisible by " + numTwo_div);
        }
    }

    /* NAME: dateSplit
    PURPOSE: The method for the sixth menu option , splitting an 8 digit integer value for date into its components, with the result output as a sentence to the user.
    IMPORTS: none
    EXPORTS: none
    ASSERTIONS: date is a valid integer choice between 1010000 and 99999999 (inclusive) */

    public static void dateSplit()
    {
        Scanner sc = new Scanner (System.in);
        int day, month, year, date, dayThreshold = 0, dateCheck; //Initialises the variables to store the split components of the date and the no. days in month+1
        String suffix, monthName;   //Initialises strings to store the day suffix and name of month

        do
        {
            dateCheck = 0;
            String datePrompt = "Please enter the date  (DDMMYYYY): ";    //line that asks user for input of date in given format
            int dateMin = 1010000, dateMax = 99999999;  //declares min and max input range for date input
            date = inputIntRange(datePrompt, dateMin, dateMax); //Calls int input validation for date

            day = dayCalc(date);    //Calls dayCalc submodule to find value of day
            month = monthCalc(date);    //Calls monthCalc submodule to find value of day
            year = yearCalc(date);  //Calls yearCalc submodule to find value of year
            
            if ((day%10 == 1)&&(day != 11))    //Gives the correct suffix to the particular day depending on the last digit in the number of the day
            {
                suffix = "st";
            }   
            else if ((day%10 == 2)&&(day != 12))
            {
                suffix = "nd";
            }
            else if ((day%10 == 3)&&(day != 13))
            {
                suffix = "rd";
            }
            else
            {
                suffix = "th";
            }
        
            switch(month)   //Gives the name of the month depending on the numeric value of month derived from the date input, then gives the number of days     in that month + 1, to be used when checking if the date exists later
            {
                case 1:
                    monthName = "January";
                    dayThreshold = 32;
                break;
                case 2:
                    monthName = "February";
                    dayThreshold = 30;
                break;
                case 3:
                    monthName = "March";
                    dayThreshold = 32;
                break;
                case 4:
                    monthName = "April";
                    dayThreshold = 31;
                break;
                case 5:
                    monthName = "May";
                    dayThreshold = 32;
                break;
                case 6:
                    monthName = "June";
                    dayThreshold = 31;
                break;
                case 7:
                    monthName = "July";
                    dayThreshold = 32;
                break;
                case 8:
                    monthName = "August";
                    dayThreshold = 32;
                break;
                case 9:
                    monthName = "September";
                    dayThreshold = 31;
                break;
                case 10:
                    monthName = "October";
                    dayThreshold = 32;
                break;
                case 11:
                    monthName = "November";
                    dayThreshold = 31;
                break;
                case 12:
                    monthName = "December";
                    dayThreshold = 32;
                break;
                default:
                    monthName = "error";
            }

            if ((day < 1 || day >= dayThreshold) || (monthName.equals("error"))) //Checks if the day lies within the number of days in the month and month is valid
            {
                dateCheck = 1;
                System.out.println("Error, this date does not exist.");
            }
            else if (month == 2 && day == 29)    //Checks if the year is a leap year, if the date given is the 29th of February on any year
            {
                if (year%4 != 0)
                {
                    System.out.println("Error, this year is not a leap year.");
                }
                else
                {
                    System.out.println("It is the " + day + suffix + " day of " + monthName + ", in " + year + ". It is a Leap year.");
                }
            }   
            else    //Outputs the date if nothing is wrong
            {
                System.out.println("It is the " + day + suffix + " day of " + monthName + ", in " + year + ".");
            }   
        }while (dateCheck == 1); //Loops for correct date input
    }
    
    /* NAME: dayCalc
    PURPOSE: The method for obtaining the value for the day from the 8 digit date input.
    IMPORTS: date (integer)
    EXPORTS: day (integer) */

    public static int dayCalc(int date)
    {
        int day;
    
        day = date/1000000; //Seperates day from date variable, to be stored in "day"

        return day; //Return day value to the original submodule so it may be output
    }

    /* NAME: monthCalc
    PURPOSE: The method for obtaining the value for month from the 8 digit date input.
    IMPORTS: date (integer)
    EXPORTS: month (integer) */

    public static int monthCalc(int date)
    {
        int month;
        
        month = (date/10000)%100;   //Seperates month in number form, to be stored in "month" 
        
        return month;   //Return month value to the original submodule so it may be output
    }

    /* NAME: yearCalc
    PURPOSE: The method for obtaining the value for year from the 8 digit date integer.
    IMPORTS: date (integer)
    EXPORTS: year (integer) */
        
    public static int yearCalc(int date)
    {
        int year;

        year = date%10000;  //Seperates year from date input, to be stored in "year"

        return year;    //Return year value to the original submodule so it may be output
    }

    /* NAME: arrays
    PURPOSE: The method for the seventh menu option, creating an array of random integers, then allowing the user to search the array for a particular integer, returning the array element it was found on or the search integer if it cannot be found.
    IMPORTS: none
    EXPORTS: none
    ASSERTIONS: arraySize is a valid integer choice between 1 and 20 (inclusive) */

    public static void arrays()
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int arraySize, sizeCheck, i, searchInt, found = 0, elementLoc = 0;  //Variable initialisations
        int arrayMin = 1, arrayMax = 20;    //Define array length range
        String arrayPrompt = "Please enter the size of the array. ";   //Prompt to take input for array length
        
        arraySize = inputIntRange(arrayPrompt, arrayMin, arrayMax); //Calls int input validation submodule for array size to check its within range (1 to 20 inclusive)

        int[] myArray = new int[arraySize];  //Creates array of specified size
        
        for (i = 0; i < arraySize; i++) //Loops through each element of the array
        {
            myArray[i] = rand.nextInt(16);    //Assigns a random integer value between 1 and 15 (inclusive) to each element of the array
        }
    
        System.out.print("Enter an integer to search the array for. "); //Take input for int to search array for
        searchInt = sc.nextInt();

        for (i = 0; i < arraySize; i++) //Loops through each element of the array
        {
            if (myArray[i] == searchInt)    //Checks each element against the search value
            {
                found = 1;  //Sets found to true if search term found in array
                elementLoc = i; //Takes note of location in the array of the matching term
            }
        }

        if (found == 1)
        {
            System.out.println("Found: " + elementLoc); //If integer was found, give location in array
        }
        else
        {
            System.out.println("Not found: " + searchInt);  //If integer was not found, return search integer
        }
    }

    /* NAME: twoDArrays
    PURPOSE: The method for the eighth menu option, making a times tables chart, with dimensions specified by the user, and the chart output formatted to the user.
    IMPORTS: none
    EXPORTS: none */

    public static void twoDArrays()
    {
        Scanner sc = new Scanner(System.in);       
        int rows, columns, i, j;  //Variable initialisations
        
        System.out.print("Please enter Rows: ");   //Take input for array row length
        rows = sc.nextInt() + 1;
            
        System.out.print("Please enter Columns: ");   //Take input for array column length
        columns = sc.nextInt() + 1;
        
        int[][] myArray = new int[rows][columns];  //Creates array of specified size
        
        for (i = 0; i < rows; i++)  //Loops through each row of the array
        {
            for (j = 0; j < columns; j++)   //Loops through each column of the array
            {
                myArray[i][j] = i*j;    //Assigns the multiple of the row number and column number to the element
            }
        }
    
        System.out.print("  |"); //Formatting for the header of the table
        for (j = 1; j < columns; j++)
        {
            System.out.printf("%3d", myArray[1][j]);
        }
        System.out.println();
        System.out.println("-----------------------------------");

        for (i = 1; i < rows; i++)  //Loops through each row of the array
        {
            System.out.print(i + " |");
            for (j = 1; j < columns; j++)   //Loops through each column of the array
            {
                System.out.printf("%3d", myArray[i][j]);
            }
            System.out.println();
        }
    }

    /* NAME: MatrixMultiplication
    PURPOSE: The method for the ninth menu option, multiplying the two matrices stored in the Matrix.java file if possible, with the resultant matrix output to the user.
    IMPORTS: none
    EXPORTS: none */

    public static void MatrixMultiplication()
    {
        Scanner sc = new Scanner(System.in);
        int m, n, p, k; //Variables to store the no. rows and columns of each of the two matrices
        int i, j, d;    //Variables to be used in the nested for loop iterations required for multiplication
        int total = 0;  //Variable to store the sum of values for the "current" element of the resultant matrix that is being calcluated

        m = Matrix.ARRAY_A.length;  //No. rows of matrix A
        n = Matrix.ARRAY_A[0].length;   //No. columns of matrix A
        
        p = Matrix.ARRAY_B.length;  //No. rows of matrix B
        k = Matrix.ARRAY_B[0].length;   //No. columns of matrix B

        if (n != p) //Check if the matices can be multiplied
        {
            System.out.println("Error, the matrices provided cannot be multiplied, please edit the matrices in the Matrix.java file."); //Error message
        }        
        else
        {

            int matrixC[][] = new int[m][k]; //Create matrix to store result of multiplication         


            for (i = 0; i < m; i++) //Rows of A
            {
                for (j = 0; j < k; j++) //Columns of B
                {
                    for (d = 0; d < p; d++) //Rows of B
                    {
                        total = total + Matrix.ARRAY_A[i][d]*Matrix.ARRAY_B[d][j];  //Multiplication of corresponding elements and summing for the "current" element of C
                    }
                    matrixC[i][j] = total;  //Assigning value to element of C (resultant matrix)
                    System.out.printf("%3d", matrixC[i][j]);    //Printing and formatting
                    total = 0;  //reset total to zero
                }
                System.out.println();
            }
        }
    }

    /* NAME: inputIntRange
    PURPOSE: The integer input validation method when checking input is within a given range. Loops prompt for input until valid input is given, then returns valid input to calling module.
    IMPORTS: prompt (string), min (integer), max (integer)
    EXPORTS: intOut (integer)
    ASSERTIONS: intOut is a valid integer choice between min and max (inclusive) */

    public static int inputIntRange(String prompt, int min, int max)
    {
        Scanner sc = new Scanner(System.in);
        int intOut;
        String intError, intOutStr;

        intOut = min - 1;   //Set initial value of input value to out of range so loop occurs with no input
        intError = "Error. The input integer must be between " + min + " and " + max + " (inclusive)";   //Error message
        intOutStr = prompt;

        do
        {
            System.out.print(intOutStr);    //Asks user for input of int value
            intOut = sc.nextInt();  //sets variable intOut to input value
            intOutStr = intError + "\n" + prompt;   //sets future loop iterations output message to include errormessage
        }while ((intOut < min)||(intOut > max));    //loops while input int is out of range

        return intOut;  //returns int value after checking against range
    }

    /* NAME: inputIntEquals
    PURPOSE: The integer input validation method when checking input is not another particular integer. Loops prompt for input until valid input is given, then returns valid input to calling module.
    IMPORTS: prompt (string), cannotBe (integer)
    EXPORTS: intEqualsOut (integer)
    ASSERTIONS: inteEqualsOut is a valid integer choice and not cannotBe */
    
    public static int inputIntEquals(String prompt, int cannotBe)
    {
        Scanner sc = new Scanner(System.in);
        int intEqualsOut;
        String intEqualsError, intEqualsOutStr;

        intEqualsOut = cannotBe;    //Set initial value of input value to out of range so loop occurs with no input
        intEqualsError = "Error. The input integer must not be " + cannotBe;    //Error message
        intEqualsOutStr = prompt;

        do
        {
            System.out.print(intEqualsOutStr);  //Asks user for input int
            intEqualsOut = sc.nextInt();    //Sets variable intEqualsOut to input value
            intEqualsOutStr = intEqualsError + "\n" + prompt;   //Sets future loop iterations output message to include error message
        }while (intEqualsOut == cannotBe);   //loops while input is what it cannot be

        return intEqualsOut;    //returns int value to calling module after checking against range
    }

    /* NAME: inputReal
    PURPOSE: The real input validation method when checking input is within a given range. Loops prompt for input until valid input is given, then returns valid input to calling module.
    IMPORTS: prompt (string), min (real), max (real)
    EXPORTS: realOut (real)
    ASSERTIONS: realOut is a valid real choice between min and max (inclusive) */

    public static double inputReal(String prompt, double min, double max)
    {
        Scanner sc = new Scanner(System.in);
        double realOut;
        String realError, realOutStr;

        realOut = min - 1;    //Set initial value of input value to out of range so loop occurs with no input
        realError = "Error. The input real must be between " + min + " and " + max;    //Error message
        realOutStr = prompt;

        do
        {
            System.out.print(realOutStr);  //Asks user for input real
            realOut = sc.nextDouble();    //Sets variable realOut to input value
            realOutStr = realError + "\n" + prompt;   //Sets future loop iterations output message to include error message
        }while ((realOut < min)||(realOut > max));   //loops while input is not within range

        return realOut;    //returns real value to calling module after checking against range
    }

    /* NAME: inputChar
    PURPOSE: The char input validation method when checking input is one of up to four options. Loops prompt for input until valid input is given, then returns valid input to calling module.
    IMPORTS: prompt (string), charOptOne (character). charOptTwo (character), charOptThree (character), charOptFour (character)
    EXPORTS: charOut (character)
    ASSERTIONS: charOut is a valid char choice between the four charOpt's */

    public static char inputChar(String prompt, char charOptOne, char charOptTwo, char charOptThree, char charOptFour)
    {
        Scanner sc = new Scanner(System.in);
        char charOut;
        String charError, charOutStr;

        charOut = 'z';    //Set initial value of input value to (hopefully) out of range so loop occurs with no input
        charError = "Error. The input char must be " + charOptOne + " or " + charOptTwo + " or " + charOptThree + " or " + charOptFour;    //Error message
        charOutStr = prompt;

        do
        {
            System.out.print(charOutStr);  //Asks user for input char
            charOut = sc.next().charAt(0);    //Sets variable intEqualsOut to input value
            charOutStr = charError + "\n" + prompt;   //Sets future loop iterations output message to include error message
        }while ((charOut != charOptOne)&&(charOut != charOptTwo)&&(charOut != charOptThree)&&(charOut != charOptFour));   //loops while input is what it cannot be

        return charOut;    //returns char value to calling module after checking against range
    }

}
