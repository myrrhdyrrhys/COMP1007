Read Me file for DetectEdges.java

PURPOSE: DetectEdges is a program that will provide five options to the user, all to do with importing images, performing convolutions and/or smoothing on them, then writing the result to a file.
AUTHOR: Lachlan Murray (19769390)

MENU OPTIONS:

    1. Import Image
    (This option must be done before any of the other menu options may be used)
    This option is for importing images from one of 3 sources.    
    You will first be given a choice from importing an image from a file or importing from user input
        1. A File
        After choosing this option, you will then be asked to import from either a .csv or .png file.
        After choosing the type of file and inputting the file name, the program should import the image if it is valid
        2. User Input
        After choosing this option, you will be asked to specify the image matrix's dimensions, and then input the integer value (between 0 and 255) of each cell when the cell's coordinates are printed to the screen.

    2. Import Kernel
    This option is for importing a kernel matrix from one of two sources.
    You will be given a choice to import a kernel from a (.csv) file or from user input.
        1. A File
        After choosing this option, you will be asked to enter the filename. If the filename is valid, the kernel will be imported.
        2. User Input
        After choosing this option, you will be asked to specify the kernel matrix's dimensions, and then input the integer value of each cell in the matrix as they are printed to the screen.
    
    3. Convolute
    This option is for performing the convolution operation on the image and kernel matrices currently stored in the program.
    Once this option is chosen, the program will perform the convolution and rewrite the image matrix with the result if it is successful.

    4. Export Image
    This option is for exporting the currently stored image matrix to a file with details specified by you, the user.
    You will be given the option to enter the following details: the file-name, the type of file (between .csv and .png) and the date to save with.
    The program will export the image with the specified details if they are all valid.

    5. Smooth Image
    This option is for performing the smoothing operation on the currently stored image.
    Once this option is chosen, you will be the asked for the following details: the size of the smoothing area (length of the smoothing square's side), the coordinates of the pixel at the center of the smoothing square and the smoothing factor.
    The operation will be performed and have the stored image overwritten with the result if the details are valid.

    0. Exit
    Exits the program.

FILES PRESENT FOR USE/TESTING:
    Images:
        Image_A.csv
        Image_B.csv
        Balloon.png
        Insta.png
        YouTube.png
    Kernels:
        VerticalKernel.csv
        HorizontalKernel.csv
