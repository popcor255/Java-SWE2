import java.io.*;

/**
 * Summary description for Technician_File.
 * Technician_File.txt - id, name, shift
 */
public class Technician_File
{
	// Function: Find Technician number given technician name
	// Input: String technican name
	// Output: int technician number if found, 0 if not
   int findTechnicianNumber(String searchName)
   {
   
      String buffer = "\0";
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Technician_File.txt"));
      
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
                  return Integer.valueOf(buffersplit[0]).intValue();
               }	
               else	
                  buffer = j.readLine();
            } //end of while   
         	
         }  //end of else
         j.close();
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Technician_File.txt IO Exception:");}
   
      return 0;
   }

	
	// Function: get technicaian information given technician number
	// Input: int technician number
	// Output: String buffer of technician information, null if not found
   String getTechnician(int technicianNumber)
   {
      String buffer = null;
   	
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Technician_File.txt"));
      
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
               if (tempNumber == technicianNumber)
               {
                  return buffer;
               }	
               else	
                  buffer = j.readLine();
            } //end of while   
         }  //end of else
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Technician_File.txt IO Exception:");}
   
      return null;
   }


	// Function: get new techncian number from technician file given current techs in system
	// Input: None
	// Output: new technician number
   public int getNewNumber()
   {
      String endoffile="\0";
      int newNumber = 0;
      String buffer = "\0";
   
      try
      {
         BufferedReader i = new BufferedReader (new FileReader("Technician_file.txt"));
         BufferedReader j = new BufferedReader (new FileReader("Technician_file.txt"));
      
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
   } //end of GetNewNumber function


	// Function: add new technician information to file
	// Input: String buffer of new technician information
	// Output: Int success
   int addTechnician(String bufferinfo)
   {
      try
      {
         PrintWriter out = new PrintWriter(new FileWriter("Technician_File.txt", true));
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


	// Function: update technician infomration to file
	// Input: String buffer of updated information of technician
	// Output: int success
   int updateTechnician(String bufferinfo)
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
         BufferedReader j = new BufferedReader (new FileReader("Technician_File.txt"));
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
            PrintWriter out = new PrintWriter(new FileWriter("Technician_File.txt", false));
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
      
      catch (IOException e) {System.out.println("Printing Technician_file.txt IO Exception:");}
   	
      if (updatedData == 0)
      {	
         return(0);
      }
   
      return(1);
   } // end of updateTechnician function

	
	// Function: Delete technician given technician number
	// Input: int technician number
	// Output: int success
   int deleteTechnician(int techniciannumber)
   {
      String buffer;
      String buffersplit[];
      int newnum = 0, i = 0, k = 0;
      int deletedData = 0;
      String bufferstring = "", check = "";
   
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Technician_File.txt"));
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
            j.close();
         
         	// way to write out to file to overwrite data that has been updated
            PrintWriter out = new PrintWriter(new FileWriter("Technician_File.txt", false));
         
            String bufferarray[] = buffer.split("\n");
         
            for (k = 0; k < i; k++)
            {	
               bufferstring = bufferarray[k];
               buffersplit = bufferstring.split("#");
               newnum = Integer.parseInt(buffersplit[0]);
               if (newnum == techniciannumber)
               {
                  deletedData = 1;
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
      
      catch (IOException e) {System.out.println("Printing Technician_file.txt IO Exception:");}
   	
      if (deletedData == 0)
      {	
         return(0);
      }
   
      return(1);
   } // end of updateTechnician function


	// Function: get technicians available given a certain shift
	// Input: int shift
	// Output: Buffer string of list of technician numbers available for that shift
   String getTechniciansByShift(int shift)
   {
      String technicians = "";
      String buffer;
      String buffersplit[];
   
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Technician_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
         	//Load the entire file into memory, separated with newlines.
         	//This is how we get around not being able to update a particular "row"...
            while (buffer != null)
            {
               buffersplit = buffer.split("#");
               if (buffersplit[2].startsWith(Integer.toString(shift)))
               {
               	//Create or append to the list of technicians
                  if (technicians.compareTo("") == 0)
                  {
                     technicians = buffersplit[0];
                  }
                  else
                  {
                     technicians += "#" + buffersplit[0];
                  }
               }
               buffer = j.readLine();
            } //end of while
            j.close();
         
         }  //end of else
      	
      }  //end of try
      
      catch (IOException e) {System.out.println("Printing Technician_file.txt IO Exception:");}
   
      return technicians;
   }
}