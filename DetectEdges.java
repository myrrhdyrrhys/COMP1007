/*
NAME: DetectEdges.java
PURPOSE: The program will provide five options to the user, all to do with importing images, performing convolutions and/or smoothing on them, then writing the result to a file.
AUTHOR: Lachlan Murray (19769390)
LAST MOD: 1:50PM 30/05/2020
*/

import java.util.*;

public class DetectEdges
{
    /*
    NAME: Main
    PURPOSE: Acts as the center-point for all the options provided by the menu. Provides the menu options to the user and calls the appropriate submodule for the task.
    */
    public static void main(String[] args)
    {
        int menuChoice;       
        int[][] image = null;
        int[][] kernel = null; 
        String fileName = ""; 

        menuChoice = UserInterface.userInput("Please choose an option from the following. (Note that option 1 must be executed before any of the other options)\n\t1. Import Image\n\t2. Import Kernel\n\t3. Convolute\n\t4. Export Image\n\t5. Smooth Image\n\t0. Exit\n", 0, 1);
        switch(menuChoice)  //First instance of the menu, where option 1 or 0 must be chosen
        {
            case 1:
                image = importImage();
            break;
            case 0:
                System.out.println("Program exiting... Have a nice day!");
            break;
        }
        
        if (menuChoice != 0)    //If they chose not to exit on the first menu screen
        {
            do
            {
                menuChoice = UserInterface.userInput("Please choose an option from the following.\n\t1. Import Image\n\t2. Import Kernel\n\t3. Convolute\n\t4. Export Image\n\t5. Smooth Image\n\t0. Exit\n", 0, 5);
                switch(menuChoice)
                {
                    case 1:
                        image = importImage();  //Call importImage submodule for the Import Image menu option
                    break;
                    case 2:
                        kernel = importKernel();   //Call importKernel submodule for the Import Kernel menu option
                    break;
                    case 3:
                        image = convolute(image, kernel);   //Call the convolute submodule to perform a convolution between the currently stored image and kernel matrices
                    break;
                    case 4:
                        exportImage(fileName, image);   //Call the exportImage submodule for writing the currently stored image to a file
                    break;
                    case 5:
                        image = smoothImage(image); //Call the smoothImage submodule to perform a smoothing on the currently stored image
                    break;
                    case 0:
                        System.out.println("Program exiting... Have a nice day!");  //exit message
                    break;
                }
            }while(menuChoice != 0);    //Loop menu options while option to exit has not been chosen
        }
    }

    /*
    NAME: importImage
    IMPORTS: none
    EXPORTS: image (two dimensional array of int)
    ASSERTION: This module corresponds to option 1 of the menu, importing an image. The user will be give two options of where to source the image, from a file or from user input, and if 'from a file' is chosen, the user will be given two options for the type of file to import the image from (.csv or .png). The image will then be imported using the chosen option.
    */
    private static int[][] importImage()
    {
        String fileName = "";
        int[][] image = null;

        int imageInputChoice = UserInterface.userInput("Where would you like to source the image from?\n\t1. A File\n\t2. User Input\n", 1, 2); //Takes user input for the source of the image to import (either file or user input)
        switch(imageInputChoice)
        {
            case 1:
                int imageTypeChoice = UserInterface.userInput("What file type is the file in which the image is stored in?\n\t1. .csv\n\t2. .png\n", 1, 2); //Takes user input for the type of file in which the image is stored
                switch(imageTypeChoice)
                {
                    case 1:
                        fileName = UserInterface.userInput("Please enter the filename of the .csv: ");  //takes user input for the filename of the .csv file
                        image = FileIO.readFile(fileName);  //Calls readFile module to import image matrix from given .csv file
                    break;
                    case 2:
                        fileName = UserInterface.userInput("Please enter the filename of the .png: ");  //takes user input for the filename of the .png file
                        image = FileIO.readPNG(fileName);   //Calls readPNG module to import image from given .png file
                    break;
                }
            break;
            case 2:
                fileName = "";
                int x = UserInterface.userInput("Please enter in the x-dimension: ", 1, 100);   //takes user input for x dimension of user-input image
                int y = UserInterface.userInput("Please enter in the y-dimension: ", 1, 100);   //takes user input for y dimension of user-input image
                image = new int[x][y];  //Create two dimensional array of integer of given dimensions to store input image
                System.out.println("These Coordinates are in (x,y)");   
                for (int i = 0; i < x; i++)
                {
                    for (int j = 0; j < y; j++)
                    {
                        image[i][j] = UserInterface.userInput("(" + (i + 1) + "," + (j + 1) + "): ", 0, 255);   //takes user input for each cell in input image array
                        System.out.println();
                    }
                }
            break;
        }

        return image;
    }

    /*
    NAME: importKernel
    IMPORTS: none
    EXPORTS: kernel (two dimensional array of int)
    ASSERTION: This module corresponds to option 2 of the menu, import kernel. The user will be given two options from which to import the kernel matrix, from a file or user input. The kernel matrix is then imported using the chosen method. 
    */
    private static int[][] importKernel()
    { 
        String fileName;
        int[][] kernel = null;

        int kernelInputChoice = UserInterface.userInput("Where would you like to source the kernel from?\n\t1. A File\n\t2. User Input\n", 1, 2);    //Takes user input for the source of the kernel matrix (either file or user input)
        switch(kernelInputChoice)
        {
            case 1:
                    fileName = UserInterface.userInput("Please enter the filename of the .csv: ");  //takes user input for the name of the .csv file the kernel is stored in
                    kernel = FileIO.readFile(fileName); //Calls readFile module to import the kernel matrix
            break;
            case 2:
                int k = UserInterface.userInput("Please enter in the kernel size k: ", 1, 100); //takes user input for the length of the kernel's sides
                kernel = new int[k][k]; //creates two dimensional array of integer of given dimensions to store input image
                System.out.println("These Coordinates are in (x,y)");
                for (int i = 0; i < k; i++)
                {
                    for (int j = 0; j < k; j++)
                    {
                        kernel[i][j] = UserInterface.userInput("(" + (i + 1) + "," + (j + 1) + "): ", -100, 100);   //takes user input for each cell in input image array
                        System.out.println();
                    }
                }
            break;
        }
      
        return kernel;
    }

    /*
    NAME: convolute
    IMPORTS: image (two dimensional array of int). kernel (two dimensional array of int)
    EXPORTS: image (two dimensional array of int)
    ASSERTION: This module corresponds to option 3 of the menu, convolute. The module will take in the currently stored image and kernel matrices and perform a convolution.
    */
    private static int[][] convolute(int[][] image, int[][] kernel)
    {
        if (kernel != null) //check if kernel has been imported yet
        {
            Image imageObj = new Image(image);  //make new image object with imported image
            image = imageObj.convolution(kernel);   //perform convolution using image class method "convolution"
        }
        else
        {
            System.out.println("You must first import a kernel to do this.");   //warning message as convolution requires both image and kernel
        }

        return image;
    }

    /*
    NAME: exportImage
    IMPORTS: fileName (String), image (two dimensional array of integer)
    EXPORTS: none
    ASSERTION: This module corresponds to option 4 of the menu, export image. The module will take in the name of the file the image was stored in (if it was imported from a file), and the image matrix as currently stored. The module then asks the user to input the details of the file to be saved, and saves the file with the appropriate naming convention if inputs are valid.
    */    
    private static void exportImage(String fileName, int[][] image)
    {
        if (fileName.equals(""))
        {
            fileName = UserInterface.userInput("Please enter the File Name: "); //Take input for image name if image was imported from user input
        }
        char fileType = UserInterface.userInputChar("What file type would you like to save with? (C)SV or (P)NG: ", 'c', 'C', 'p', 'P');
        
        int date = UserInterface.userInput("Please enter a Date to save with: ", 1010000, 31129999);    //takes user input for date to save file with
        int month = Menu.monthCalc(date);
        int day = Menu.dayCalc(date);
        int year = Menu.yearCalc(date); //Calls modules from Menu.java that split date into its components

        Date dateObj = new Date(day, month, year);  //Create date object using input date

        if ((fileType == 'C')||(fileType == 'c'))
        {
            FileIO.writeFile(dateObj.toString() + "_Processed_" + fileName + ".csv", image);
        }
        else
        {
            FileIO.writeFile(dateObj.toString() + "_Processed_" + fileName + ".png", image);
        }
        System.out.println("Image written to file!");
    }
    
    /*
    NAME: smoothImage
    IMPORTS: image (two dimensional array of int)
    EXPORTS: image (two dimensional array of int)
    ASSERTION: This module corresponds to option 5 of the menu, smooth image. The module takes in the image matrix as it is currently stored, takes input for the details of the smoothing, then performs the smoothing operation on the image matrix.
    */
    private static int[][] smoothImage(int[][] image)
    {
        Image imageObj = new Image(image);  //Create Image object using imported image
        int length; //variable to hold the length of smoothing square

        int width = PDIMath.min(image.length, image[0].length); //retrieves and compares the length of the sides of the imported image for its smallest side
        
        do
        {
            length = UserInterface.userInput("Please enter a smoothing surface: ", 1, width);    //takes user input for the length of the area which will be smoothed
            if(length % 2 == 0)
            {
                UserInterface.displayError("Error, the smoothing surface length must not be even.");    //display error for even length
            }
        }while(length % 2 == 0);    //loops for input while input is even

        String pixel = UserInterface.userInput("Please enter a pixel to smooth (x,y): ");   //takes user input for the string containing x and y coordinates
//Error of wrong input string (no commas, too many etc.)
        String[] splitLine = pixel.split(",");  //Split string according to comma to access x and y
        int[] splitLineInt = new int[2];
        try
        {
            for (int i = 0; i < 2; i++)
            {
                splitLineInt[i] = Integer.parseInt(splitLine[i]);   //Parse from string to int
            }
        }
        catch(NumberFormatException e)
        {
            UserInterface.displayError("Error when parsing coordinates to integer type from type string.");
        }

        double factor = UserInterface.userInput("Please enter a smoothness factor (between 0.0 and 1.0 (not including 0.0): ", 0.000001, 1); //take user input for the smoothness factor

        image = imageObj.smooth(length, splitLineInt[0], splitLineInt[1], factor);  //smooth image with module "smooth" in image class and set image to smoothed result
        
        return image;
    }
}
