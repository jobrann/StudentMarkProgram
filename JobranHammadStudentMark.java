/**
 * Write a description of class JobranHammadStudentMark here.
 *
 * @author (Jobran Hammad)
 * @version (Final.Version)
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class JobranHammadStudentMark
{
    public static void main(String[] args)
    {
        //declare variables
        String[] strStudentsA; //holds names of te students
        byte[] bytMarksA; //holds student marks
        byte bytChoice; //holds the user choice for user interface
        int intStudents = 0; //holds the number of students
        boolean bolErrorTest = false; // error testing

        //Welcoming statement
        System.out.println("Welcome to Jobran's Student Mark program!\n");
        
        
        do
        {
            //ask user how many students
            System.out.println("How many students are in the class?");
            try
            {
                intStudents = new Scanner(System.in).nextInt();
                //store the number of students in a variable and change bolcheck to false
                bolErrorTest = false;
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "ERROR - Number of students  must be a whole number\nPlease try again");
                bolErrorTest = true; //set error to true and creat pop up error
            }

            if (!bolErrorTest  && intStudents<=0)
            {
                JOptionPane.showMessageDialog(null, "ERROR - Number of students must be more than 0\nPlease try again");
                bolErrorTest = true;
            }
        }
        while (bolErrorTest);

        
        //calls fill array methods that instantiate arrays to the number of students then fill them with user input
        strStudentsA = fillArrayString(intStudents);
        bytMarksA = fillArrayByte(intStudents, strStudentsA);

        //this loop runs as long as the user enters 1, 2, 3, or 4. if they enter anything else the loop ends
        do
        {
            //calls the menu method and sets bytChoice to whatever they entered in the menu
            bytChoice = choice();

            //switch statement that calls different methods depending on what the user entered for the menu
            switch(bytChoice)
            {
                //studentSearch and notPassing need both the student list and the marks list, but for classAverage and
                //markSearch, only the marks list is needed
                case 1: studentSearch(strStudentsA, bytMarksA); break;
                case 2: classAverage(bytMarksA); break;
                case 3: notPassing(strStudentsA, bytMarksA); break;
                case 4: markSearch(bytMarksA); break;
            }
        }
        while (bytChoice > 0 && bytChoice < 5);

        //SHOULD PROBABLY HAVE A CONCLUDING MESSAGE HERE
    }

    //returns the instantiated string array
    public static String[] fillArrayString(int num)
    {
        //instantiates the string array to the number that was passed
        String[] output = new String[num];

        //using a for loop to fill the array at each step
        //user input is scanned for for each index of the array
        for (int i=0; i<output.length; i++)
        {
            System.out.println("\nPlease enter student number " + (i+1) + "'s name:");
            output[i] = new Scanner(System.in).nextLine();
        }

        //created string array is returned
        return output;
    }

    //similar method as the above, but for a byte array
    public static byte[] fillArrayByte(int num, String[] students)
    {
        //instantiating the array
        byte[] output = new byte[num];

        //empty println for formatting
        System.out.println();

        //using a for loop to fill the indices of the array
        for (int i=0; i<output.length; i++)
        {
            System.out.println("\nPlease enter " + students[i] + "'s mark:");
            output[i] = new Scanner(System.in).nextByte();
        }

        //returning the created array
        return output;
    }

    public static byte choice()
    {
        //displaying the menu options to the user and prompting them to enter something
        //while it says 5 is for exit, any option other than 1, 2, 3, and 4 will exit the loop in the main
        System.out.println("\n1) Search for student");
        System.out.println("2) Class Average");
        System.out.println("3) Check Who Isn't Passing");
        System.out.println("4) Search for Mark");
        System.out.println("5) Exit");

        System.out.println("Please make a selection from the menu");

        //what the user enters is returned
        return new Scanner(System.in).nextByte();
    }

    public static void studentSearch(String[] students, byte[] marks)
    {
        //declaring variables
        //search is the name being searched for, found stores whether or not a student has been found
        String search;
        boolean found = false;

        //prompting the user to enter the name they want to search for
        System.out.println("\nEnter the name you would like to search for");
        search = new Scanner(System.in).nextLine();

        //for loop has to be used here since we are working with mutliple arrays
        //runs through each student and checks if their name matches and prints their name and mark if it does
        for (int i=0; i<students.length; i++)
        {
            //using indexOf here allows just first name or nicknames to be searched for like typing gui instead of his full name
            //toLowerCase is used so capitals can also be ignored
            if (students[i].toLowerCase().indexOf(search.toLowerCase()) != -1)
            {
                System.out.println(students[i] + " has a " + marks[i] + "\n");
                found = true;
            }
        }

        //triggers if the entire loop ran and no one was found
        if (!found)
        {
            System.out.println("No students found with that name\n");
        }
    }

    //making it void because the instructions said "display" average, not return it
    public static void classAverage(byte[] marks)
    {
        //declaring variable to hold the sum of marks
        int sum = 0;

        //using a for each loop to take each element of the array and add it to the sum
        for (int element: marks)
        {
            sum += element;
        }

        //printing out the average rounded to two decimal places using printf
        //sum casted as a float is divided by the length of the array (number of marks added together)
        System.out.printf("\nThe class average is %.2f\n\n",(float)sum/marks.length);
    }

    public static void notPassing(String[] students, byte[] marks)
    {
        //declaring an array to hold the list of students not passing and their marks
        //added onto as the method runs
        String list = "\nStudents not passing:\n";

        //using a for loop instead of a for each loop since we are working with multiple arrays at once
        for (int i=0; i<students.length; i++)
        {
            //if the mark is less than 50, the student's name and mark are addedo nto the string
            if (marks[i] < 50)
            {
                list += students[i] + " - " + marks[i] + "%\n";
            }
        }

        //string is printed at the end
        System.out.println(list);
    }

    //not sure if this one is supposed to be void or return the number so i decided to make it void
    public static void markSearch(byte[] marks)
    {
        //declaring variables
        //benchmark is the mark being compared to that the user sets and counter holds the number of people with marks
        //above the benchmark
        int benchmark, counter = 0 ;

        //prompts the user for the mark they want to compare against
        System.out.println("\nWhich mark would you like to check?");
        benchmark = new Scanner(System.in).nextInt();

        //if the element of the array is greater or equal to the mark being compared to, the counter is incremented
        for (int element: marks)
        {
            if (element >= benchmark)
            {
                counter++;
            }
        }

        //prints out the number of students about the mark
        System.out.println("There are " + counter + " student(s) at or above " + benchmark + "%");
    }
}

