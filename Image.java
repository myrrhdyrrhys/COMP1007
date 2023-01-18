/*
NOTE: This Java program was submitted previously by myself for the PDI worksheet 6 submission. This program has been slightly edited since then however, with the addition of a module named "smooth" on line 210.
NAME: Image.java
PURPOSE: A class designed for creating objects of type Image, with the class-field of originalImage, the image matrix. Outside of the regular modules, the class provides a convolution module and smoothing module to allow for those operatios to be performed on Image objects.
AUTHOR: Lachlan Murray (19769390)
LAST MOD: 2:49PM 28/05/2020
*/

public class Image
{
    //Class Constants
    public static final int[][] kernelMatrix = {{1, 0, -1},
                                                {1, 0, -1},
                                                {1, 0, -1}};

    public static final int[][] defaultImage = {{10, 10, 10,  0,  0,  0},
                        {10, 10, 10,  0,  0,  0},
                        {10, 10, 10,  0,  0,  0},
                        {10, 10, 10,  0,  0,  0},
                        {10, 10, 10,  0,  0,  0},
                        {10, 10, 10,  0,  0,  0}}; 

    //Private Classfields
    private int[][] originalImage;
    
    /*
    Default Constructor
    IMPORT: none
    EXPORT: address of new Image object
    ASSERTION: originalImage is the below two-dimensional matrix
    */
    public Image()
    {
        originalImage = defaultImage;
    }

    /*
    Alternate Constructor
    IMPORT: inOriginalImage (two-dimensional array of integer)
    EXPORT: address of new Image object
    ASSERTION: Creates the object if the imports are valid and fails otherwise
    */
    public Image(int[][] inOriginalImage)
    {
        if(inOriginalImage != null)
        {
            setOriginalImage(inOriginalImage);
        }
    }
    
    /*
    Copy Constructor
    IMPORT: inOriginalImage (Image)
    EXPORT: address of new Image object
    ASSERTION: Creates copy of input Image object of identical "state"
    */
    public Image(Image inImage)
    {
        originalImage = inImage.getOriginalImage();
    }

    //MUTATORS (Setters)
    /*
    SUBMODULE: setOriginalImage
    IMPORT: inOriginalImage (two-dimensional array of integer)
    EXPORT: none
    ASSERTION: originalImage classfield will be set to inOriginalImage
    */
    public void setOriginalImage(int[][] inOriginalImage)
    {
        originalImage = inOriginalImage;
    }

    //ACCESSORS (Getters)
    public int[][] getOriginalImage()
    {
        return originalImage;
    }

    /*
    SUBMODULE: equals
    IMPORT: obj (Object)
    EXPORT: isEqual (boolean)
    ASSERTION: Two Images are interchangeable if they have the same array elements
    */
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        boolean isEqualTwo = true;
        Image inImage;
        int p, q, m, n;
        p = originalImage.length;  //No. rows of matrix 
        q = originalImage[0].length;   //No. columns of matrix 
        
        if(obj instanceof Image)
        {
            inImage = (Image)obj; //Typecast
            int[][] newOriginalImage = inImage.getOriginalImage();
            if((newOriginalImage == null)&&(originalImage == null))
            {
                isEqual = true;
            }
            else if((p == newOriginalImage.length)&&(q == newOriginalImage[0].length))
            {
                for (m = 0; m < p; m++)
                {
                    for (n = 0; n < q; n++)
                    {
                        if(originalImage[m][n] == newOriginalImage[m][n])
                        {
                            isEqual = true;
                        }
                        else
                        {
                            if(isEqualTwo == true)
                            {
                                isEqualTwo = false;
                            }
                        }
                    }
               }//INSTEAD OF if(inOriginalImage.getOriginalImage() == originalImage)
            }
        }

        if(isEqualTwo == false)
        {
            isEqual = false;
        }

        return isEqual;
    }

    //Clone (clones the current object)
    public Image clone()
    {
        return new Image(this);
    }

    //toString (formats output of object of type Image)
    public String toString()
    {
        String outString = "";
        int t, p, q, m, n;
        
        p = originalImage.length;  //No. rows of matrix 
        q = originalImage[0].length;   //No. columns of matrix         

        for (t = 0; t < q; t++)
        {
            outString += "-----"; //Formatting for ----- line at top
        }
        outString += "--\n";
        
        for (m = 0; m < p; m++)
        {
            for (n = 0; n < q; n++)
            {
                outString += " |";
                outString += originalImage[m][n]; //Printing and formatting for output matrix
            }
            outString += " |\n";
            for (t = 0; t < q; t++)
            {
                outString += "-----"; //Formatting of new line
            }
            outString += "--\n";
        }
        
        return outString;
    }

    /*
    SUBMODULE: convolution
    IMPORT: kernelMatrix (two-dimensional array of integer)
    EXPORT: resultArray
    ASSERTION:
    */
    public int[][] convolution(int[][] kernelMatrix)
    {       
        int p, q, k, m, n, d, i, f, j, sum = 0, total = 0;
        
        p = originalImage.length;  //No. rows of matrix A
        q = originalImage[0].length;   //No. columns of matrix A
        k = kernelMatrix.length; //No. rows and columns of kernel matrix (square)
        d = p - k + 1;  //No. rows of result matrix
        f = q - k + 1;  //No. columns of result matrix        

        int resultArray[][] = new int[d][f]; //Create matrix to store the result of convolution
        
        for (m = 0; m < d; m++)
        {
            for (n = 0; n < f; n++)
            {
                for (i = 0; i < k; i++)
                {
                    for (j = 0; j < k; j++)
                    {
                        sum = sum + ((originalImage[m + i][n + j])*(kernelMatrix[i][j]));   //Takes sum of first row of kernel multiplying first row of Convolute matrix
                    }
                    total = total + sum;    //Takes total of above sum for each column this is done on
                    sum = 0;    //Resets sum for j for loop
                }
                resultArray[m][n] = total;   //Setes element of resulting matrix as total
                total = 0;  //Resets total for i for loop
            }
        }    
        return resultArray;
    }

    /*
    NAME: smooth
    IMPORTS: length (int), x (int), y (int), factor (double)
    EXPORTS: originalImage (int[][])
    ASSERTION: The module will take in the smoothing square size, the coordinates for the square center and the smoothing factor, and perform smoothing on originalImage.
    */
    public int[][] smooth(int length, int x, int y, double factor)
    {
        int numCells = length * length; //find area of smoothing square
        int sum = 0;
        int smoothValue = 0;

        if((((x - 1) - (length / 2)) < 0)||(((y - 1) - (length / 2)) < 0)||(((x - 1) + (length / 2)) > originalImage[0].length)||(((y - 1) + (length / 2)) > originalImage.length)) //validate boundaries are not crossed
        {
            UserInterface.displayError("Error, the smoothing surface overlaps the boundaries of the image matrix.");   //Display error message for boundary overlap
        }
        else
        {
            //coordinates need to have 1 minused from them to get array location
            for(int i = ((x - 1) - (length / 2)); i < ((x - 1) + (length / 2)); i++)
            {
                for(int j = ((y - 1) - (length / 2)); j < ((y - 1) + (length / 2)); j++)
                {
                    sum = sum + originalImage[i][j];    //take sum of values in smoothing square
                }
            }

            double average = (sum / numCells);  //Find average of cells in smoothing square

            smoothValue = PDIMath.ceil(sum * factor); //round up the value of the sum

            //write value back to cells in smoothing square 
            for(int i = ((x - 1) - (length / 2)); i < ((x - 1) + (length / 2)); i++)
            {
                for(int j = ((x - 1) - (length / 2)); j < ((x - 1) + (length / 2)); j++)
                {
                    originalImage[i][j] = smoothValue;    //write cells in squares values to smooth value
                }
            }
        }
        
        return originalImage;
    }
    //PRIVATE SUBMODULE
    //Validate length of matrices (no. columns of convolute >= no. columns of kernel. Same for rows. Kernel must be square)
    /*
    SUBMODULE: validateKernelSize
    IMPORT: kernel (two-dimensional array of integer)
    EXPORT: valid (boolean)
    ASSERTION: kernel matrix is square
    */
    private boolean validateKernelSize(int[][] kernel)
    {
        boolean valid = false;
        
        if((kernel.length) == (kernel[0].length))
        {
            valid = true;
        }
        
        return valid;
    }

    /*
    SUBMODULE: validateLength
    IMPORT: convolute (two-dimensional array of integer), kernel (two-dimensional array of integer)
    EXPORT: valid (boolean)
    ASSERTION: convolute matrix is same size or larger than kernel
    */
    private boolean validateLength(int[][] convolute, int[][] kernel)
    {
        boolean valid = false;
        
        if(((convolute.length)>=(kernel.length))&&((convolute[0].length)>=(kernel[0].length)))
        {
            valid = true;
        }
        
        return valid;
    }
}
