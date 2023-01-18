/*
NOTE: This program was previously submitted by myself as part of the PDI worksheet 7 submission.
NAME PDIMath.java
PURPOSE: Provides the user with a range of different math-related function, such as finding the min or max of 2 numbers, round up or down a real value, finding the absolute value of an integer and providing approximations of pi and e constants.
AUTHOR: Lachlan Murray (19769390)
LAST MOD: 31/05/2020
*/

public class PDIMath
{
    /*
    NAME: min
    IMPORT: a (int), b (int)
    EXPORT: min (int)
    ASSERTION: min is the minimum number between options a and b
    */
    public static int min(int a, int b)
    {
        int min = 0;
        
        if(a <= b)
        {
            min = a;
        }
        else
        {
            min = b;
        }
        
        return min;
    }
    
    /*
    NAME: min
    IMPORT: a (double), b (double)
    EXPORT: min (double)
    ASSERTION: min is the minimum number between options a and b
    */
    public static double min(double a, double b)
    {
        double min = 0.0;
        
        if(a <= b)
        {
            min = a;
        }
        else
        {
            min = b;
        }
        
        return min;
    }

    /*
    NAME: min
    IMPORT: a (long), b (long)
    EXPORT: min (long)
    ASSERTION: min is the minimum number between options a and b
    */
    public static long min(long a, long b)
    {
        long min = 0;
        
        if(a <= b)
        {
            min = a;
        }
        else
        {
            min = b;
        }
        
        return min;
    }
    
    /*
    NAME: min
    IMPORT: a (float), b (float)
    EXPORT: min (float)
    ASSERTION: min is the minimum number between options a and b
    */
    public static float min(float a, float b)
    {
        float min = 0;
        
        if(a <= b)
        {
            min = a;
        }
        else
        {
            min = b;
        }
        
        return min;
    }

    /*
    NAME: max
    IMPORT: a (int), b (int)
    EXPORT: max (int)
    ASSERTION: max is the maximum number between options a and b
    */
    public static int max(int a, int b)
    {
        int max = 0;
        
        if(a >= b)
        {
            max = a;
        }
        else
        {
            max = b;
        }
        
        return max;
    }
    
    /*
    NAME: max
    IMPORT: a (double), b (double)
    EXPORT: max (double)
    ASSERTION: max is the maximum number between options a and b
    */
    public static double max(double a, double b)
    {
        double max = 0.0;
        
        if(a >= b)
        {
            max = a;
        }
        else
        {
            max = b;
        }
        
        return max;
    }

    /*
    NAME: max
    IMPORT: a (long), b (long)
    EXPORT: max (long)
    ASSERTION: max is the maximum number between options a and b
    */
    public static long max(long a, long b)
    {
        long max = 0;
        
        if(a >= b)
        {
            max = a;
        }
        else
        {
            max = b;
        }
        
        return max;
    }
    
    /*
    NAME: max
    IMPORT: a (float), b (float)
    EXPORT: max (float)
    ASSERTION: max is the maximum number between options a and b
    */
    public static float max(float a, float b)
    {
        float max = 0;
        
        if(a >= b)
        {
            max = a;
        }
        else
        {
            max = b;
        }
        
        return max;
    }

    /*
    NAME: abs
    IMPORT: a (int)
    EXPORT: abs (int)
    ASSERTION: abs is the absolute value of input a
    */
    public static int abs(int a)
    {
        int abs = 0;

        if(a*(-1) > 0)  //a is negative
        {
            abs = (a*(-1));
        }
        else
        {
            abs = a;
        }

        return abs;
    }
    
    /*
    NAME: abs
    IMPORT: a (double)
    EXPORT: abs (double)
    ASSERTION: abs is the absolute value of input a
    */
    public static double abs(double a)
    {
        double abs = 0.0;

        if(a*(-1) > 0.0)  //a is negative
        {
            abs = (a*(-1));
        }
        else
        {
            abs = a;
        }

        return abs;
    }

    /*
    NAME: abs
    IMPORT: a (long)
    EXPORT: abs (long)
    ASSERTION: abs is the absolute value of input a
    */
    public static long abs(long a)
    {
        long abs = 0;

        if(a*(-1) > 0)  //a is negative
        {
            abs = (a*(-1));
        }
        else
        {
            abs = a;
        }

        return abs;
    }
    
    /*
    NAME: abs
    IMPORT: a (float)
    EXPORT: abs (float)
    ASSERTION: abs is the absolute value of input a
    */
    public static float abs(float a)
    {
        float abs = 0;

        if(a*(-1) > 0)  //a is negative
        {
            abs = (a*(-1));
        }
        else
        {
            abs = a;
        }

        return abs;
    }

    /*
    NAME: floor
    IMPORT: a (double)
    EXPORT: down (int)
    ASSERTION: down is the integer value of real a when rounded down
    */
    public static int floor(double a)
    {
        int down = (int)a;
        
        return down;
    }

    /*
    NAME: ceil
    IMPORT: a (double)
    EXPORT: up (int)
    ASSERTION: up is the integer value of real a when rounded up
    */
    public static int ceil(double a)
    {
        int up = (int)(a + 1.0);

        return up;
    }

    /*
    NAME: pow
    IMPORT: base (double), exponent (int)
    EXPORT: product (double)
    ASSERTION: product is the result of raising the base to the power of exponent
    */
    public static double pow(double base, int exponent)
    {
        int i;
        double product = 1.0;   //when exponent = 0, product will be 1.0

        if(exponent > 0)    //if exponent is positive
        {
            for(i = 1; i <= exponent; i++)
            {
                product = product*base;
            }
        }
        else if(exponent < 0)   //if exponent is negative
        {
            for(i = 1; i <= (exponent*(-1)); i++)
            {
                product = product*base;
            }
            product = (1.0/product);    //exponential worked out normally, then one is divided by value obtained
        }
        
        return product;
    }

    /*
    NAME: pi
    IMPORT: precision (int)
    EXPORT: sum (double)
    ASSERTION: sum is the approximation of pi from the series calculation
    */
    public static double pi(int precision)
    {
        int i;
        double sum = 0.0, radians = 3.14159;   //Variable initialisations        

        for (i = 0; i < precision; i++)
        {
            sum = sum + ((Math.sin(radians*((2*i)+1)))/((2*i)+1)); //Ads term value to sum after doing calculation for term
        } 
        sum = (sum * 4.0);  //Sets sum to PI

        return sum; //return approximation of pi
    }

    /*
    NAME: e
    IMPORT: precision (int)
    EXPORT: sum (double)
    ASSERTION: sum is the approximation of e from the series calculation
    */
    public static double e(int precision)
    {
        int i, j, numTerms;
        double sum = 0.0, factprod;   //Variable initialisations        

        for (i = 1; i < precision; i++)
        {
            factprod = 1.0;
            for (j = 1; j <= i; j++)
            {
                factprod = factprod*j;    //Loop to calculate the denominatorof each array term (the term number's factorial)
            }
            sum = sum + (1.0/factprod);  //Sums the series terms as they are calculated
        } 
        sum = sum + 1.0;  //Adds to the sum the value which was outside the above loop

        return sum;
    }
}
