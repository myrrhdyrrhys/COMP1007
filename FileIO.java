/*
NOTE: This Java program was submitted previously by myself for the PDI worksheet 8 submission. This program also contains modules named readPNG and writePNG, which were obtained from the worksheet 8's specifications, and were not written by me.
NAME: FileIO.java
PURPOSE: A static class with modules designed to open files and read in its contents to a 2D array and return that to caller, one to write 2D arrays to a file, one to open .png files and construct a 2D array to return to the caller, and one to write a 2D array to a .png file
AUTHOR: Lachlan Murray
LAST MOD: 5:40PM 15/05/20
*/

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class FileIO
{
    /*
    NAME: readFile 
    IMPORTS: fileName (String)
    EXPORTS: readArray (int[][])
    ASSERTION: The module will attempt to open the file of the given filename, read its contents into a 2D array and then if valid (rectangular), returns the array to the caller.
    */
    public static int[][] readFile(String fileName)
    {
        int[][] readArray = setSize(fileName);   //set size of read array
        int counter = 0;    //Counter for number of rows in matrix, used to check if matrix is rectangular
        FileInputStream fileStream = null;  //Create a stream object for the file to be read
        InputStreamReader rdr;  //Create an object that can read the stream
        BufferedReader bufRdr;  //Create a buffered reader object for reading a line at a time
        String line;    //String to store the line read
        try
        {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();   //reads first line
            if(line != null)    //Check if file is empty by checking first line isn't null
            {
                while(line != null)
                {
                    counter = processLine(line, readArray, counter);  //method to do while reading lines (e.g. storing)
                    line = bufRdr.readLine();
                }
                boolean rectangular = validateShape(readArray); //Call module to validate matrix is not ragged or staggered
                if(rectangular = false)
                {
                    UserInterface.displayError("Error. The input matrix is not rectangular.");
                    readArray = null;
                }
            }
            else
            {
                throw new IllegalArgumentException("The chosen file is empty.");
            }        
            fileStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Couldn't find your file " + e.getMessage() + " Try again!");    //Typo/file doesnt exist
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage()); //Error when scanning empty file
        }
        catch(IOException e)    //Change to multiple specififc exceptions in order of most precise to most broad (e.g. include FileNotFoundException)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {
                    //
                }
            }
            System.out.println("Error in fileProcessing: " + e.getMessage());
        }
        
        return readArray;
    }

    /*
    NAME: setSize 
    IMPORTS: fileName (String)
    EXPORTS: readArray (int[][])
    ASSERTION: The module will attempt to open the file of the given filename, read its contents for the no. lines and length of lines to set the size of the readArray.
    */
    public static int[][] setSize(String fileName)
    {
        int[][] readArray = null;
        int rows = 0;    //Counter for number of rows in matrix
        int columns = 0;    //Counter for number of columns in matrix
        FileInputStream fileStream = null;  //Create a stream object for the file to be read
        InputStreamReader rdr;  //Create an object that can read the stream
        BufferedReader bufRdr;  //Create a buffered reader object for reading a line at a time
        String line;    //String to store the line read
        try
        {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();   //reads first line
            if(line != null)    //Check if file is empty by checking first line isn't null
            {
                while(line != null)
                {
                    String[] splitLine; //Array of String to store the split line's components
                    splitLine = line.split(",");  //Splits read line according to comma seperated values and stores an expected 3 values in array
                    columns = splitLine.length; //sets columns of array to number of elements in last row of file
                    rows += 1;   //increases line/row number counter
                
                    line = bufRdr.readLine();   //next line
                }
                readArray = new int[rows][columns]; //set size of readArray
            }
            else
            {
                throw new IllegalArgumentException("The chosen file is empty.");
            }        
            fileStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Couldn't find your file " + e.getMessage() + " Try again!");    //Typo/file doesnt exist
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage()); //Error when scanning empty file
        }
        catch(IOException e)    //Change to multiple specififc exceptions in order of most precise to most broad (e.g. include FileNotFoundException)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {
                    //
                }
            }
            System.out.println("Error in fileProcessing: " + e.getMessage());
        }

        return readArray;
    }
            
    /*
    SUBMODULE: processLine
    IMPORT: csvRow (String), readArray (2D array of integers), counter (int)
    EXPORT: counter (int)
    ASSERTION: The module will split the csv rows line by line and add it to the readArray
    */
    private static int processLine(String csvRow, int[][] readArray, int counter)
    {
        String[] splitLine; //Array of String to store the split line's components
        splitLine = csvRow.split(",");  //Splits read line according to comma seperated values and stores an expected 3 values in array
        try
        {
            for(int i = 0; i < splitLine.length; i++)   //Iterates through each column of readArray
            {
                readArray[counter][i] = Integer.parseInt(splitLine[i]); //Parse int value to readArray
            }
            counter += 1;   //increases line/row number counter
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            UserInterface.displayError("Error. Matrix in file is not rectangular");
        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
        }
        
        return counter;
    }            

    /*
    SUBMODULE: validateShape
    IMPORT: readArray (int[][])
    EXPORT: rectangular (boolean)
    ASSERTION: readArray is rectangular if rectangular is true
    */
    private static boolean validateShape(int[][] readArray)
    {
        boolean rectangular = true;
        
        for(int i = 1; i < readArray.length; i++)   //for each row in the matrix
        {
            if(readArray[i].length != readArray[i-1].length)    //if the length of row i is not the same as the previous rows' length, it is not rectangular
            {
                rectangular = false;
            }
        }
        
        return rectangular;
    }

    /*
    NAME: writeFile
    IMPORTS: fileName (String), writeArray (int[][])
    EXPORTS: none
    ASSERTION: The module will attempt to write the writeArray to the fileName.
    */
    public static void writeFile(String fileName, int[][] writeArray)
    {   
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        
        try
        {
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            for(int i = 0; i < writeArray.length; i++)    //Loop through each entry in array
            {
                for(int j = 0; j < writeArray[0].length; j++)
                {
                    if(j == ((writeArray[0].length) - 1))   //if last entry in row
                    {
                        pw.print(writeArray[i][j]); //outputs last element in row without a comma following it
                    }
                    else
                    {
                        pw.print(writeArray[i][j] + ",");   //outputs as csv file
                    }
                }
                pw.println();
            }
            pw.close();
        }
/*
        catch(FileNotFoundException e)
        {
            System.out.println("Couldn't find your file " + e.getMessage() + " Try again!");    //Typo/file doesnt exist
        }
*/
        catch(IOException e)    //IOException thrown when writing to file
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)  //Exception thrown when attempting to close file
                {}
            }
            System.out.println("Error in writing to file: " + e.getMessage());  //error message output
        }
    }

    /*
NOTE: The following module was taken from Programming Design and Implementation Worksheet 8
    NAME: readPNG
    IMPORTS: fileName (String)
    EXPORTS: image (int[][])
    ASSERTION: The module will attempt to open a .png file and construct a 2D array to return to the caller.
    */
    public static int[][] readPNG(String fileName)
    {
        BufferedImage img;
        File inputFile;
        int[][] image = null;

        try
        {
            //Open the file
            inputFile = new File(fileName);

            //Turn file into an Image
            img = ImageIO.read(inputFile);

            //Construct array to hold image
            image = new int[img.getHeight()][img.getWidth()];

            //Loop through each pixel
            for(int y = 0; y < img.getHeight(); y++)
            {
                for(int x = 0; x < img.getWidth(); x++)
                {
                    //Turn the pixel into a Color object.
                    Color pixel = new Color(img.getRGB(x, y), true);
                    
                    //Converts each pixel to a grayscale equivalent using weightings on each colour.
                    image[y][x] = (int)((pixel.getRed() * 0.299) + (pixel.getBlue() * 0.587) + (pixel.getGreen() * 0.114));
                }
            }
        }
        catch(IOException e)
        {
            UserInterface.displayError("Error with .png reading: " + e.getMessage());
        }

        return image;
    }

    /*
NOTE: The following module was taken from Programming Design and Implementation Worksheet 8
    NAME: writePNG
    IMPORTS: fileName (String), writeArray (int[][])
    EXPORTS: none
    ASSERTION: The module will attempt to write a 2D array to a .png file.
    */
    public static void writePNG(String fileName, int[][] writeArray) 
    {
        BufferedImage theImage;
        File outputfile;
        
        try
        {
            //Open the file
            outputfile = new File(fileName);

            //Construct a BufferedImage, with dimensions and of type RGB
            theImage = new BufferedImage(writeArray[0].length, writeArray.length, BufferedImage.TYPE_INT_RGB);
            
            //Step through each element of writeArray
            for(int y = 0; y < writeArray.length; y++)
            {
                for(int x = 0; x < writeArray[0].length; x++)
                {
                    //Ensure output value is between 0 and 255 (8-bit colour depth)
                    int value = Math.abs(writeArray[y][x] % 256);

                    //Turns greyscale pixel into a colour representation
                    Color newColor = new Color(value, value, value);

                    //Set the value of the pixel within the .png
                    theImage.setRGB(x, y, newColor.getRGB());
                }
            }
            
            //Write image to a .png
            ImageIO.write(theImage, "png", outputfile);
        }
        catch(IOException e)
        {
            UserInterface.displayError("Error with .png reading: " + e.getMessage());
        }
    }
}
