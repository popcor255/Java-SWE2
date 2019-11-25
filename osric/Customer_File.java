import java.io.*;
import java.util.*;

/**
 * Summary description for Customer_File.
 * Customer_File.txt - id, name, address1, address2, city, province, postalcode, country, phonenumber, priority
 */
public class Customer_File
{
	
	// Function: find customer number based on customer name
	// Input: String customer name
	// Output: String buffer of the customer's information if found, null if not found
   String findCustomerNumber(String searchName)
   {
      String buffer = "\0";
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Customer_File.txt"));
      
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
         	//finding the number in the file and writing fileinfo back
            while (buffer != null)
            {
               String buffersplit[] = buffer.split("#");
               String tempName = buffersplit[1];
               if (tempName.compareTo(searchName) == 0)
               {
               	//return Integer.valueOf(buffersplit[0]).intValue();
                  return buffer;
               }	
               else	
                  buffer = j.readLine();
            } //end of while   
         	
         }  //end of else
         j.close();
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Customer_File.txt IO Exception:");}
   
      return "";
   }

	
	// Function: get customer information based on customer number
	// Input: int customer number
	// Output: String buffer of customer information if found, null if not found
   String getCustomer(int CustomerNumber)
   {
      String buffer = null;
   	
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Customer_File.txt"));
      
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
         	//finding the number in the file and writing fileinfo back
            while (buffer != null)
            {
               String buffersplit[] = buffer.split("#");
               int tempNumber = Integer.parseInt(buffersplit[0]);
               if (tempNumber == CustomerNumber)
               {
                  return buffer;
               }	
               else	
                  buffer = j.readLine();
            } //end of while   
         }  //end of else
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Customer_File.txt IO Exception:");}
   
      return null;
   }


	// Function: get new customer number
	// Input: None
	// Output: int new customer number based on either no customers, or last customer number + 1
   public int getNewNumber()
   {
      String endoffile="\0";
      int newNumber = 0;
      String buffer = "\0";
   
      try
      {
         BufferedReader i = new BufferedReader (new FileReader("Customer_File.txt"));
         BufferedReader j = new BufferedReader (new FileReader("Customer_File.txt"));
      
         endoffile = i.readLine();
         buffer = j.readLine();
      
         if (endoffile == null)
            newNumber = 10000;
      
      	//getting to the end of the file
         while (endoffile != null)
         {
            endoffile = i.readLine();
            if (endoffile != null)
            {
               buffer = j.readLine();
            }
         }
      }
      catch (IOException e) {System.out.println("Printing error reading file IO Exception:");}
   
      if (newNumber != 10000)  // if file not empty - finding last number used and incrementing
      {
         String buffersplit[] = buffer.split("#");
         int newnum = Integer.parseInt(buffersplit[0]);
         newNumber = newnum + 1;
      }
   
   	// returning the new number
      return(newNumber);
   }   
	
	// Function: add customer information to file
	// Input: String buffer of customer information to be added
	// Output: int Success 
   int addCustomer(String bufferinfo)
   {
      try
      {
         PrintWriter out = new PrintWriter(new FileWriter("Customer_File.txt", true));
         out.write(bufferinfo + "\n");
         out.close();
      }
      catch (IOException e)
      {
         System.out.println("Printing output.txt IO Exception:");
         return(0);
      }
      return(1);
   }
   
   int deleteCustomer(String id)
   {
      try
      {
         
         Scanner scan = new Scanner(new File("Customer_File.txt"));
        
         String value = "";
         String output = "";
         
         while(scan.hasNext()){
          
            value = scan.nextLine();
            
            if(!(value.contains(id))){
               output += value + "\n";
            }
            
         } 
       
         PrintWriter out = new PrintWriter("Customer_File.txt");
      
         out.write(output);
         out.close();
      }
      catch (IOException e)
      {
         System.out.println("Printing output.txt IO Exception:");
         return(0);
      }
      return(1);
   }



	
	// Function: update customer information to file
	// Input: String buffer of customer information to be updated
	// Output: int success
   int updateCustomer(String bufferinfo)
   {
      String buffer;
      String buffersplit[];
      int newnum = 0, i = 0, k = 0;
      int updatedData = 0, techNum = 0;
      String bufferstring = "", check = "";
   
      buffersplit = bufferinfo.split("#");
      techNum = Integer.parseInt(buffersplit[0]);
   
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Customer_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
         	//Load the entire file into memory, separated with newlines.
         	//This is how we get around not being able to update a particular "row"...
            while (check != null)
            {
               i++;
               check = j.readLine();
               if (check != null)
               {
                  buffer = buffer.concat("\n");
                  buffer = buffer.concat(check);
               }
            } //end of while
            buffer = buffer.concat("\n");
            j.close();
         
         	// way to write out to file to overwrite data that has been updated
            PrintWriter out = new PrintWriter(new FileWriter("Customer_File.txt", false));
            String bufferarray[] = buffer.split("\n");
         
            for (k = 0; k < i; k++)
            {	
               bufferstring = bufferarray[k];
               buffersplit = bufferstring.split("#");
               newnum = Integer.parseInt(buffersplit[0]);
               if (newnum == techNum)
               {
                  out.write(bufferinfo);
                  out.write("\n");
                  updatedData = 1;
               }
               else
               {
                  out.write(bufferarray[k]);
                  out.write("\n");      
               }
            } // end of for loop
            out.close();
         
         }  //end of else
      	
      }  //end of try
      
      catch (IOException e) {System.out.println("Printing Customer_File.txt IO Exception:");}
   	
      if (updatedData == 0)
      {	
         return(0);
      }
   
      return(1);
   } // end of updateCustomer function

}
