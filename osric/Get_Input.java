import java.io.*;
import java.util.*;

// Get_Input: interface between dos prompt and user entering information
// Called by: All other classes functions
// Calls: none
class Get_Input extends Object
{

    // Function to read in a number
    // Input: maxvalue of digits the number should be
    // Output: int containing the number entered in by the user
   static int readNumber(int maxvalue)
   {
      int n = 0;
      try
      {
         BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
         String s = r.readLine();
         n = Integer.valueOf(s).intValue();
      } 
      catch (NumberFormatException e)
      {
         System.out.print( "Incorrect input. Please type an integer of ");
         System.out.print(maxvalue);
         System.out.println(" digits");
         return readNumber(maxvalue);
      }
      catch (IOException e)
      {
         System.out.println( "IO Exception");
      }
   
      return n;
   } // end of readNumber


    // Function to read in a number
    // Input: maxvalue of digits the number should be
    // Output: int containing the number entered in by the user
   static float readFloat()
   {
      float n = 0;
      try
      {
         BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
         String s = r.readLine();
         Float N = Float.valueOf(s);
         n = N.floatValue();
      } 
      catch (NumberFormatException e)
      {
         System.out.print( "Incorrect input. Please type a float");
         return readFloat();
      }
      catch (IOException e)
      {
         System.out.println( "IO Exception");
      }
   
      return n;
   } // end of readFloat


    // Function to read in a number
    // Input: maxvalue of digits the number should be
    // Output: int containing the number entered in by the user
   static double readDouble()
   {
      double n = 0;
      try
      {
         BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
         String s = r.readLine();
         Double N = Double.valueOf(s);
         n = N.doubleValue();
      } 
      catch (NumberFormatException e)
      {
         System.out.print( "Incorrect input. Please type a float");
         return readFloat();
      }
      catch (IOException e)
      {
         System.out.println( "IO Exception");
      }
   
      return n;
   } // end of readDouble


    // Function to read in a number
    // Input: maxvalue of digits the number should be
    // Output: int containing the number entered in by the user
   static long readLong()
   {
      long n = 0;
      try
      {
         BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
         String s = r.readLine();
         Long N = Long.valueOf(s);
         n = N.longValue();
      } 
      catch (NumberFormatException e)
      {
         System.out.print( "Incorrect input. Please type a long number");
         return readLong();
      }
      catch (IOException e)
      {
         System.out.println( "IO Exception");
      }
   
      return n;
   } // end of readLong


    // Function to read in a string
    // Input: maxvalue of digits the string should be
    // Output: String containing the string entered in by the user
   static String readString(int maxlength)
   {
      String tempstring = "\0";
      int done = 0;
   
      while (done != 1)
      {
         try
         {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            tempstring = r.readLine();		
            if (tempstring.length() > maxlength)
            {
               System.out.println("String entered is too long, please enter a string with max chars " + maxlength);
            }
            else if ((tempstring.length() == 0) && (maxlength != 100))
            {
               System.out.println("You must enter a value.  Please try again.");
            }
            else done = 1;
         }
         catch (IOException e)
         {
            System.out.println( "IO Exception");
         }
      } //end of while !done
   
      return(tempstring);
   
   } //end of readString


	// Function to read in an int for a selection
    // Input: maxnumber for the selection statement
    // Output: int containing users selection choice
   static int readSelection(int maxNumber)
   {
      int n = 0;
      try
      {
         BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
         String s = r.readLine();
         n = Integer.valueOf(s).intValue();
      } 
      catch (NumberFormatException e)
      {
         System.out.println( "Incorrect input. Please type an integer between 1 and " + maxNumber + ". ");
         return readSelection(maxNumber);
      }
      catch (IOException e)
      {
         System.out.println( "IO Exception");
      }
   //making sure user enters in a 
      if (n < 1 || n > maxNumber)
      {
         System.out.println("Invalid Selection.  Please enter in a number between 1 and " + maxNumber + ".");
         return readSelection(maxNumber);
      }
      return n;
   } // end of readSelection


	// Function to return the current year
	// Input: none
	// Output: current year, used by readDate function to verify date entered is current year
   static int getYear()
   {
      Calendar timenow = Calendar.getInstance();
      int year = timenow.get(timenow.YEAR);
      return(year);
   }


	// Function to return the current date
	// Input: none
	// Output: string date (mm-dd-yyyy), used to write to file current date
   public static String getCurrentDateTime()
   {
   
      return new java.util.Date().toString();
   
   }


	// Function to get date\time from user
	// Input: None
	// Output: current date\timestamp if hit enter, entered date\time if otherwise
   public static long getDateTime()
   {
      while (true)
      {
         try
         {
                        //Read the string
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            String s = r.readLine();
         
                        //And parse it according to the format below
            if (s.compareTo("") == 0)
               return Get_Input.getCurrentTimestamp();
         
            java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yy kk:mm");
            Date date = dateFormat.parse(s);
            return date.getTime();
         } 
         catch (Exception e)
         {
            System.out.println("Invalid entry. Please enter a date in the format mm/dd/yyyy hh:mm (military time)");
         }
      }
   }


	
	// Function: get current time stamp from java standard function give current system date\time
	// Input: None
	// Output: Current date\time difference between java defined date\time and sysdate\time
   public static long getCurrentTimestamp()
   {
      return new java.util.Date().getTime();
   }


	// Funtion: read line in from file
	// Input: None
	// Output: None
   static void readLine()
   {
      BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
      try
      {
         r.readLine();
      }
      catch(IOException e)
      {
      }
   }
} //end of Get_input class
