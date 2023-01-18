/*
NOTE: This Java program was previously submitted by myself as a part of my PDI worksheet 6 submission.
NAME: Date.java
PURPOSE: A class designed for creating objects of type Date, with the class-fields of day, month and year.
AUTHOR: Lachlan Murray (19769390)
LAST MOD: 2:48PM 28/05/2020
*/


public class Date
{
    //Classfields
    private int day;
    private int month;
    private int year;
    
    /*
    Default Constructor
    IMPORT: none
    EXPORT: address of new Date object
    ASSERTION: day 26, month 01, year 2001
    */
    public Date()
    {
        day = 26;
        month = 01;
        year = 2001;
    }

    /*
    Alternate Constructor
    IMPORT: inDay (integer), inMonth (integer), inYear (integer)
    EXPORT: address of new Date object
    ASSERTION: Creates the object if the imports are valid and fails otherwise
    */
    public Date(int inDay, int inMonth, int inYear)
    {
        if (validateMonth(inMonth))
        {
            if (validateDay(inDay, inMonth, inYear))
            {
                setDay(inDay);
                setMonth(inMonth);
                setYear(inYear);
            }
            else
            {
                throw new IllegalArgumentException("Invalid Day.");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid Month.");
        }
    }
    
    /*
    Copy Constructor
    IMPORT: inDate (Date)
    EXPORT: address of new Date object
    ASSERTION: Creates copy of input Date object of identical "state"
    */
    public Date(Date inDate)
    {
        day = inDate.getDay();
        month = inDate.getMonth();
        year = inDate.getYear();
    }

    //MUTATORS (Setters)
    /*
    SUBMODULE: setDay
    IMPORT: inDay (integer)
    EXPORT: none
    ASSERTION: day classfield will be set to inDay
    */
    public void setDay(int inDay)
    {
        day = inDay;
    }

    /*
    SUBMODULE: setMonth
    IMPORT: inMonth (integer)
    EXPORT: none
    ASSERTION: month classfield will be set to inMonth
    */
    public void setMonth(int inMonth)
    {
        month = inMonth;   
    }

    /*
    SUBMODULE: setYear
    IMPORT: inYear (integer)
    EXPORT: none
    ASSERTION: year classfield will be set to inYear
    */
    public void setYear(int inYear)
    {
        year = inYear;   
    }

    //ACCESSORS (Getters)
    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    /*
    SUBMODULE: equals
    IMPORT: obj (Object)
    EXPORT: isEqual (boolean)
    ASSERTION: Two Dates are interchangeable if they have the same day, month and year
    */
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        Date inDate;

        if(obj instanceof Date)
        {
            inDate = (Date)obj; //Typecast
            if(inDate.getDay() == day)
            {
                if(inDate.getMonth() == month)
                {
                    if(inDate.getYear() == year)
                    {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }

    //Clone (clones the current object)
    public Date clone()
    {
        return new Date(this);
    }

    //toString (formats output of object of type Date)
    public String toString()
    {
        String outString;
        
        outString = day + "-" + month + "-" + year;

        return outString;
    }
    
    //PRIVATE SUBMODULES
    /*
    SUBMODULE: validateMonth
    IMPORT: inMonth (integer)
    EXPORT: valid (boolean)
    ASSERTION: month is an integer between 1 and 12 (inclusive)
    */
    private boolean validateMonth(int inMonth)
    {
        boolean valid = false;
        
        if((inMonth <= 12)&&(inMonth >= 1))
        {
            valid = true;
        }
        
        return valid;
    }
    
    /*
    SUBMODULE: validateDay
    IMPORT: inDay (integer), inMonth (integer), inYear (integer)
    EXPORT: valid (boolean)
    ASSERTION: day is in the range for the given month and given year
    */
    private boolean validateDay(int inDay, int inMonth, int inYear)
    {
        boolean valid = false;
        int dayThreshold = 0;        

        switch(inMonth)
        {
            case 1:
                dayThreshold = 32;
            break;
            case 2:
                dayThreshold = 29;
                if(inYear%4 == 0)   //leap year check
                {
                    dayThreshold = 30;
                }
            break;
            case 3:
                dayThreshold = 32;
            break;
            case 4:
                dayThreshold = 31;
            break;
            case 5:
                dayThreshold = 32;
            break;
            case 6:
                dayThreshold = 31;
            break;
            case 7:
                dayThreshold = 32;
            break;
            case 8:
                dayThreshold = 32;
            break;
            case 9:
                dayThreshold = 31;
            break;
            case 10:
                dayThreshold = 32;
            break;
            case 11:
                dayThreshold = 31;
            break;
            case 12:
                dayThreshold = 32;
            break;
            default:
        }
        
        if((inDay > 0)&&(inDay < dayThreshold))
        {
            valid = true;
        }
           
        return valid;
    }
}
