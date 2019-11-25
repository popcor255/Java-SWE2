

import java.io.*;

/*
 * Summary description for Service_Request_File.
 * Service_Request_File.txt - id, status, comments, customerNumber, dateTimeEntered, 
 *								dateTimeCompleted, dateTimeAssigned, customerPriority,
 *								dayTech, nightTech1, nightTech2, requestNightTech,
 *								dayBlocks, nightBlocks, dayBlocksWaited, estTotalBlocksUsed
 */
public class Service_Request_File
{
	// Function: get service request information given customer number
	// Input: int service request number
	// Output: String buffer of service request information if found, null if not
   String getServiceRequest(int ServiceRequestNumber)
   {
      String buffer = null;
   	
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
      
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
               if (tempNumber == ServiceRequestNumber)
               {
                  return buffer;
               }	
               else	
                  buffer = j.readLine();
            } //end of while   
         }  //end of else
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Service_Request_File.txt IO Exception:");}
   
      return null;
   }


	// Function to get service requests with higher priority
	// Input: int priority to compare against, and timestamp of date\time entered
	// Output: String buffer of service request numbers meeting the higher priority criteria
   String getServiceRequestsWithHigherPriority(int priority, long timestamp)
   {
      String buffer = null, requests = "";
   	
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
      
         buffer = j.readLine();
         if (buffer == null)
            return "";
         else
         {
         	//finding the number in the file and writing fileinfo back
            while (buffer != null)
            {
               String buffersplit[] = buffer.split("#");
               int tempPriority = Integer.parseInt(buffersplit[7]);
               long tempTimestamp = Long.parseLong(buffersplit[4]);
               String status = buffersplit[1];
            
               if (((status.compareTo("p") == 0) || (status.compareTo("a") == 0))
               	&& ((tempPriority == priority && tempTimestamp < timestamp)
               	|| (tempPriority > priority))  )
               {
                  if (requests.compareTo("") == 0)
                     requests = buffersplit[0];
                  else
                     requests += "#" + buffersplit[0];
               }
            		
            
               buffer = j.readLine();
            } //end of while   
         }  //end of else
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Service_Request_File.txt IO Exception:");}
   	
      return requests;
   }


	// Function: get new service request number given current service requests in file
	// Input: None
	// Output: int new service request number
   public int getNewNumber()
   {
      String endoffile="\0";
      int newNumber = 0;
      String buffer = "\0";
   
      try
      {
         BufferedReader i = new BufferedReader (new FileReader("Service_Request_File.txt"));
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
      
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


	// Function: adding new service request information to file
	// Input: String buffer of new service request info to be added
	// Output: Int Success
   int addServiceRequest(String bufferinfo)
   {
      try
      {
         PrintWriter out = new PrintWriter(new FileWriter("Service_Request_File.txt", true));
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


	// Function: updating service request information to file
	// Input: String buffer of service request info to be updated
	// Output: Int Success
   int updateServiceRequest(String bufferinfo)
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
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
         	//Load the entire file into memory, separated with newlines.
         	//This is how we get around not being able to update a particular "row"...
            while (check != null)
            {
               check = j.readLine();
               i++;
               if (check != null)
               {
                  buffer = buffer.concat("\n");
                  buffer = buffer.concat(check);
               }
            	
            } //end of while
            j.close();
            buffer = buffer.concat("\n");
         
         	// way to write out to file to overwrite data that has been updated
            PrintWriter out = new PrintWriter(new FileWriter("Service_Request_File.txt", false));
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
      
      catch (IOException e) {System.out.println("Printing Service_Request_File.txt IO Exception:");}
   	
      if (updatedData == 0)
      {	
         return(0);
      }
   
      return(1);
   } // end of updateServiceRequest function


	// Function: get number of completed service requests for particular customer saved in service request file
	// Input: customer number
	// Output: number of completes service requests
   int getNumberCompletedRequestsForClient(int customerNumber)
   {
      String[] buffersplit;
      String buffer;
      int count = 0;
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
            while (buffer != null)
            {
               buffersplit = buffer.split("#");
               if ((buffersplit[1].compareTo("c") == 0 || buffersplit[1].compareTo("b") == 0) 
               	&& buffersplit[3].compareTo(Integer.toString(customerNumber)) == 0 )
                  count++;		
            
               buffer = j.readLine();
            } //end of while
            j.close();
         }
      }
      catch (IOException e) {System.out.println("Printing Service_Request_File.txt IO Exception:");}
   
      return count;
   }


	// Function: get assigned service requests by shift for Assignments function
	// Input: int Shift
	// Output: String buffer of list of service request numbers assigned for that shift
   String getAssignedServiceRequestsByShift(int shift)
   {
      String[] buffersplit;
      String buffer;
      String requests = "";
   	
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
            while (buffer != null)
            {
               buffersplit = buffer.split("#");
               if (buffersplit[1].compareTo("a") == 0)
               {
               	//Create or append to the list of technicians
                  if (requests.compareTo("") == 0)
                     requests = buffersplit[0];
                  else
                  {
                     requests += "#" + buffersplit[0];
                  }
               }
            
               buffer = j.readLine();
            } //end of while
            j.close();
         }
      }
      catch (IOException e) {System.out.println("Printing Service_Request_File.txt IO Exception:");}
   
      return requests;
   }

	
	// Function: get all unassigned (pending) jobs from file by priority - and whether its for a night shift or not
	// Input: int priority & if it is a night shift or not
	// Output: String Buffer list of service request numbers not currently assigned for particular shift type & priority
   String getUnassignedServiceRequestsByPriority(int priority, boolean isNightShift)
   {
      String[] buffersplit;
      String buffer;
      String requests = "";
   
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
            while (buffer != null)
            {
               buffersplit = buffer.split("#");
            
               if (Integer.parseInt(buffersplit[7]) == priority)
               {
               	//Day shift, status is 'p'ending
                  if (!isNightShift && buffersplit[1].compareTo("p") == 0)
                  {
                     if (requests.compareTo("") == 0)
                        requests = buffersplit[0];
                     else
                        requests += "#" + buffersplit[0];
                  } 
                  //Else, night shift, and request that's already assigned
                  // and is requesting night tech, we add it.
                  else if (isNightShift && buffersplit[11].compareTo("y") == 0
                  	&& buffersplit[1].compareTo("a") == 0)
                  {
                     if (requests.compareTo("") == 0)
                        requests = buffersplit[0];
                     else
                        requests += "#" + buffersplit[0];
                  }
               }
            
               buffer = j.readLine();
            } //end of while
            j.close();
         }
      }
      catch (IOException e) {System.out.println("Printing Service_Request_File.txt IO Exception:");}
   
      return requests;
   }
	

	// Function: get service request for specific technician and shift
	// Input: int shift & int customer number
	// Output: If found - return service request information; else return null
   String getServiceRequestForTechnician(int shift, int technicianNumber)
   {
      String[] buffersplit;
      String buffer;
   	
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
            while (buffer != null)
            {
               buffersplit = buffer.split("#");
            
            	//See if the tech passed in is assigned to this request 
               if (buffersplit[1].compareTo("a") == 0)
               {
                  if (shift == 0 && buffersplit[8].compareTo(Integer.toString(technicianNumber)) == 0)
                  {
                     return buffer;
                  } 
                  else if (shift == 1 && buffersplit[9].compareTo(Integer.toString(technicianNumber)) == 0)
                  {
                     return buffer;
                  }
                  else if (shift == 2 && buffersplit[10].compareTo(Integer.toString(technicianNumber)) == 0)
                  {
                     return buffer;
                  }
               }
            
               buffer = j.readLine();
            } //end of while
            j.close();
         }
      }
      catch (IOException e) {System.out.println("Printing Service_Request_File.txt IO Exception:");}
   
      return "";
   }


	// Function: Get list of service requests by given status
	// Input: String Status (p-pending, a-assigned, c-completed, b-billed)
	// Output: String buffer list of service request numbers of given status in the file
   String getServiceRequestsByStatus(String status)
   {
      String buffer = null, requests = "";
   	
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
      
         buffer = j.readLine();
         if (buffer == null)
            return "";
         else
         {
         	//finding the number in the file and writing fileinfo back
            while (buffer != null)
            {
               String buffersplit[] = buffer.split("#");
               String srStatus = buffersplit[1];
            	
               if (srStatus.compareTo(status) == 0)
               {
                  if (requests.compareTo("") == 0)
                     requests = buffersplit[0];
                  else
                     requests += "#" + buffersplit[0];
               }
            	
               buffer = j.readLine();
            } //end of while   
         }  //end of else
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Service_Request_File.txt IO Exception:");}
   	
      return requests;
   }


	// Function: get service request by status and priority
	// Input: String Status & int priority
	// Output: String buffer list of service request numbers found in file
   String getServiceRequestsByStatusByPriority(String status, int priority)
   {
      String buffer = null, requests = "";
   	
      try
      {
      	// reading from service file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Service_Request_File.txt"));
      
         buffer = j.readLine();
         if (buffer == null)
            return "";
         else
         {
         	//finding the number in the file and writing fileinfo back
            while (buffer != null)
            {
               String buffersplit[] = buffer.split("#");
               String srStatus = buffersplit[1];
               String srPriority = buffersplit[7];
            	
               if (srStatus.compareTo(status) == 0 
               	&& srPriority.compareTo(Integer.toString(priority)) == 0)
               {
                  if (requests.compareTo("") == 0)
                     requests = buffersplit[0];
                  else
                     requests += "#" + buffersplit[0];
               }
            	
               buffer = j.readLine();
            } //end of while   
         }  //end of else
      }  //end of try
      catch (IOException e) {System.out.println("Error reading Service_Request_File.txt IO Exception:");}
   	
      return requests;
   }

}