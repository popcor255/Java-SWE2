import java.io.*;

/**
 * Summary description for Service_Request.
 */
public class Service_Request
{

   private int serviceRequestNumber, customerNumber, customerPriority;
   private int dayTechnician, nightTechnician1, nightTechnician2;
   private int totalDayBlocks, totalNightBlocks, totalDayBlocksWaited;
   private int estTotalBlocksUsed;
   private char Status, requestNightTechnician;
	
   private String Comments;
   long dateTimeEntered, dateTimeCompleted, dateTimeAssigned;

   public Service_Request()
   {
   }


	// Function: Set default private attributes of Service_Request class
	// Input: Service_Request attributes
	// Output: None
   public Service_Request(String id, String Status, String Comments, String CustomerNumber,
   						String DateTimeEntered, String DateTimeCompleted,
   						String DateTimeAssigned, String CustomerPriority,
   						String DayTech, String NightTech1, String NightTech2,
   						String RequestNightTech, String DayBlocks,
   						String NightBlocks, String DayBlocksWaited)
   {
      this.serviceRequestNumber = Integer.parseInt(id);
      this.Status = Status.charAt(0);
      this.Comments = Comments;
      this.customerNumber = Integer.parseInt(CustomerNumber);
      this.dateTimeEntered = Long.parseLong(DateTimeEntered);
      this.dateTimeCompleted = Long.parseLong(DateTimeCompleted);
      this.dateTimeAssigned = Long.parseLong(DateTimeAssigned);
      this.customerPriority = Integer.parseInt(CustomerPriority);
      this.dayTechnician = Integer.parseInt(DayTech);
      this.nightTechnician1 = Integer.parseInt(NightTech1);
      this.nightTechnician2 = Integer.parseInt(NightTech2);
      this.requestNightTechnician = RequestNightTech.charAt(0);
      this.totalDayBlocks = Integer.parseInt(DayBlocks);
      this.totalNightBlocks = Integer.parseInt(NightBlocks);
      this.totalDayBlocksWaited = Integer.parseInt(DayBlocksWaited);
   }


//****************************** Set Functions ************************************

	// Function: Set Service Request information call given Service Request buffer info
	// Input: Buffer String of Service Request information
	// Output: None
   public Service_Request(String bufferinfo)
   {
      this.setServiceRequestInformation(bufferinfo);
   }

   void setCustomerNumber(int newCustomerNumber)
   {
      customerNumber = newCustomerNumber;
   }

   void setCustomerPriority(int newCustomerPriority)
   {
      customerPriority = newCustomerPriority;
   }	

   void setDayTechnician(int newDayTechnician)
   {
      dayTechnician = newDayTechnician;
   }

   void setNightTechnician1(int newNightTechnician1)
   {
      nightTechnician1 = newNightTechnician1;
   }

   void setNightTechnician2(int newNightTechnician2)
   {
      nightTechnician2 = newNightTechnician2;
   }

   void setTotalDayBlocks(int newTotalDayBlocks)
   {
      totalDayBlocks = newTotalDayBlocks;
   }

   void setTotalNightBlocks(int newTotalNightBlocks)
   {
      totalNightBlocks = newTotalNightBlocks;
   }

   void setTotalBlocksWaited(int newTotalDayBlocksWaited)
   {
      totalDayBlocksWaited = newTotalDayBlocksWaited;
   }

   void setRequestNightTechnician(char newRequestNightTechnician)
   {
      requestNightTechnician = newRequestNightTechnician;
   }

   void setStatus(char newStatus)
   {
      Status = newStatus;
   }

   void setComments(String newComments)
   {
      Comments = newComments;
   }

   void setDateTimeEntered(long newDateTimeEntered)
   {
      dateTimeEntered = newDateTimeEntered;
   }

   void setDateTimeCompleted(long newDateTimeCompleted)
   {
      dateTimeCompleted = newDateTimeCompleted;
   }

   void setDateTimeAssigned(long newDateTimeAssigned)
   {
      dateTimeAssigned = newDateTimeAssigned;
   }

   void setEstTotalBlocksUsed(int newEstTotalBlocksUsed)
   {
      estTotalBlocksUsed = newEstTotalBlocksUsed;
   }


	// Function: Set Service Request information given customer number, priority and comments from user
	// Input: int customer number, int customer priority
	// Output: int new service request number assigned
   int setServiceRequestInformation(int customerNumber, int customerPriority)
   {
      Service_Request_File srFile = new Service_Request_File();
            
            //Get comments
      System.out.println("Please enter any user comments:");
      this.Comments = Get_Input.readString(250);
   
            //Get a new number
      this.serviceRequestNumber = srFile.getNewNumber();
   
      this.Status = 'p';
      this.customerNumber = customerNumber;
            
      System.out.println("The current date/time is " + Get_Input.getCurrentDateTime() + ".");
      System.out.println("Please enter time request was entered: ");
            
      this.dateTimeEntered = Get_Input.getDateTime();
      this.dateTimeCompleted = 0;
      this.dateTimeAssigned = 0;
      this.customerPriority = customerPriority;
      this.dayTechnician = 0;
      this.nightTechnician1 = 0;
      this.nightTechnician2 = 0;
      this.requestNightTechnician = 'n';
      this.totalDayBlocks = 0;
      this.totalNightBlocks = 0;
      this.totalDayBlocksWaited = 0;
      this.estTotalBlocksUsed = 0;
   
      srFile.addServiceRequest(Integer.toString(getServiceRequestNumber()) + "#" + 
                  getStatus() + "#" + 
                  getComments() + "#" + 
                  Integer.toString(getCustomerNumber()) + "#" + 
                  Long.toString(getDateTimeEntered()) + "#" + 
                  Long.toString(getDateTimeCompleted())  + "#" + 
                  Long.toString(getDateTimeAssigned()) + "#" + 
                  Integer.toString(getCustomerPriority()) + "#" + 
                  Integer.toString(getDayTechnician()) + "#" + 
                  Integer.toString(getNightTechnician1()) + "#" + 
                  Integer.toString(getNightTechnician2()) + "#" + 
                  getRequestNightTechnician() + "#" + 
                  Integer.toString(getTotalDayBlocks()) + "#" + 
                  Integer.toString(getTotalNightBlocks()) + "#" + 
                  Integer.toString(getTotalDayBlocksWaited()) + "#" +
                  Integer.toString(getEstTotalBlocksUsed()));
            
      return serviceRequestNumber;
   }



	// Function: Set Service Request Information given String buffer of SR Info
	// Input: String buffer of service request information
	// Output: None	
   private void setServiceRequestInformation(String buffer)
   {
      String buffersplit[] = buffer.split("#");
   
      serviceRequestNumber = Integer.parseInt(buffersplit[0]);
      Status = buffersplit[1].charAt(0);
      Comments = buffersplit[2];
      customerNumber = Integer.parseInt(buffersplit[3]);
      dateTimeEntered = Long.parseLong(buffersplit[4]);
      dateTimeCompleted = Long.parseLong(buffersplit[5]);
      dateTimeAssigned = Long.parseLong(buffersplit[6]);
      customerPriority = Integer.parseInt(buffersplit[7]);
      dayTechnician = Integer.parseInt(buffersplit[8]);
      nightTechnician1 = Integer.parseInt(buffersplit[9]);
      nightTechnician2 = Integer.parseInt(buffersplit[10]);
      requestNightTechnician = buffersplit[11].charAt(0);
      totalDayBlocks = Integer.parseInt(buffersplit[12]);
      totalNightBlocks = Integer.parseInt(buffersplit[13]);
      totalDayBlocksWaited = Integer.parseInt(buffersplit[14]);
   }

//********************************* Get Functions *********************************

   int getServiceRequestNumber()
   {
      return serviceRequestNumber;
   }
	
   int getCustomerNumber()
   {
      return customerNumber;
   }
	
   int getCustomerPriority()
   {
      return customerPriority;
   }
	

   int getDayTechnician()
   {
      return dayTechnician;
   }
	
   int getNightTechnician1()
   {
      return nightTechnician1;
   }
		
   int getNightTechnician2()
   {
      return nightTechnician2;
   }
		
   int getTotalDayBlocks()
   {
      return totalDayBlocks;
   }
		
   int getTotalNightBlocks()
   {
      return totalNightBlocks;
   }
	
	
   int getTotalDayBlocksWaited()
   {
      return totalDayBlocksWaited;
   }
		
   char getStatus()
   {
      return Status;
   }


   char getRequestNightTechnician()
   {
      return requestNightTechnician;
   }
	
   String getComments()
   {
      return Comments;
   }
	
   long getDateTimeEntered()
   {
      return dateTimeEntered;
   }

   long getDateTimeCompleted()
   {
      return dateTimeCompleted;
   }

   long getDateTimeAssigned()
   {
      return dateTimeAssigned;
   }

   int getEstTotalBlocksUsed()
   {
      return estTotalBlocksUsed;
   }

		
	
//************************************ Update Functions *********************************
	
	// Function: Format Update Service Request Information and call Service Request File
	//	Class to update information
	// Input: None
	// Output: None
   void updateServiceRequest()
   {
      Service_Request_File srFile = new Service_Request_File();
   
      srFile.updateServiceRequest(Integer.toString(getServiceRequestNumber()) + "#" + 
         getStatus() + "#" + 
         getComments() + "#" + 
         Integer.toString(getCustomerNumber()) + "#" + 
         Long.toString(getDateTimeEntered()) + "#" + 
         Long.toString(getDateTimeCompleted())  + "#" + 
         Long.toString(getDateTimeAssigned()) + "#" + 
         Integer.toString(getCustomerPriority()) + "#" + 
         Integer.toString(getDayTechnician()) + "#" + 
         Integer.toString(getNightTechnician1()) + "#" + 
         Integer.toString(getNightTechnician2()) + "#" + 
         getRequestNightTechnician() + "#" + 
         Integer.toString(getTotalDayBlocks()) + "#" + 
         Integer.toString(getTotalNightBlocks()) + "#" + 
         Integer.toString(getTotalDayBlocksWaited()) + "#" +
         Integer.toString(getEstTotalBlocksUsed()));
   
   }


	// Function: Assign Service Requests - Gather currently assigned srs (not completed)
	//	first, then once those are reassigned to day\night techs appropriatly if day shift
	//	assign pending service requests
	// Input: Shift assignments should be made for
	// Output: None
   void assignServiceRequests(int shift, long dateAssigned)
   {
            //Get all techs by shift
      Technician_File tFile = new Technician_File();
      Service_Request_File srFile = new Service_Request_File();
   
      String techs, requests, assignedTech;
      String[] techsplit, requestsplit;
   
      techs = tFile.getTechniciansByShift(shift);
      techsplit = techs.split("#");
   
      requests = srFile.getAssignedServiceRequestsByShift(shift);
   
      if (requests.compareTo("") != 0)
      {
         requestsplit = requests.split("#");
      
                  //Get all currently assigned requests and reassign that tech,
                  //remove that tech from available pool, and update the estimated total blocks used field
         for (int i = 0; i < requestsplit.length; i++)
         {
            this.setServiceRequestInformation(srFile.getServiceRequest(Integer.parseInt(requestsplit[i])));
            this.setEstTotalBlocksUsed(this.getEstTotalBlocksUsed() + 1);
            this.updateServiceRequest();
         
            assignedTech = Integer.toString(getDayTechnician());
            for (int j = 0; j < techsplit.length; j++)
            {
               if (techsplit[j].compareTo(assignedTech) == 0)
               {
                  techsplit[j] = "";
                  break;
               }
            }
         }
      }
   
            //Go through the open requests, in order of priority and time, and assign a tech
      for (int priority = 4; priority > 0; priority--)
      {
         boolean isNightShift = false;
         if (shift == 1 || shift == 2)
            isNightShift = true;
      
         requests = srFile.getUnassignedServiceRequestsByPriority(priority, isNightShift);
                  
         if (requests.compareTo("") != 0)
         {
            requestsplit = requests.split("#");
         
            for (int i = 0; i < requestsplit.length; i++)
            {
               for (int j = 0; j < techsplit.length; j++)
               {
                  if (techsplit[j].compareTo("") != 0)
                  {
                                          //Assign this tech to this service request
                     this.setServiceRequestInformation(srFile.getServiceRequest(Integer.parseInt(requestsplit[i])));
                                          //serviceRequest = new Service_Request(srFile.getServiceRequest(Integer.parseInt(requestsplit[i])));
                  
                     if (shift == 0)
                        setDayTechnician(Integer.parseInt(techsplit[j]));
                     else if (shift == 1)
                        setNightTechnician1(Integer.parseInt(techsplit[j]));
                     else
                        setNightTechnician2(Integer.parseInt(techsplit[j]));
                  
                     setStatus('a');
                     setEstTotalBlocksUsed(1);
                     setDateTimeAssigned(dateAssigned);
                     techsplit[j] = "";
                  
                     this.updateServiceRequest();
                     break;
                  
                  }
               }     //end of technician loop
            }     //end of request loop
         } //end of checking if we returned any results for this priority
      }     //end of priority loop
   
   
            //If its a day shift, update all pending (unassigned) requests to increment how long
            // they've waited, and update their priority if need be
      if (shift == 0)
      {
         for (int priority = 4; priority > 0; priority--)
         {
            requests = srFile.getUnassignedServiceRequestsByPriority(priority, false);
                        
            if (requests.compareTo("") != 0)
            {
               requestsplit = requests.split("#");
               for (int j = 0; j < requestsplit.length; j++)
               {
                                    //serviceRequest = new Service_Request(srFile.getServiceRequest(Integer.parseInt(requestsplit[j])));
                  this.setServiceRequestInformation(srFile.getServiceRequest(Integer.parseInt(requestsplit[j])));
               
                                    //Update the number of day blocks
                  setTotalBlocksWaited(getTotalDayBlocksWaited() + 1);
               
                  int requestPriority = getCustomerPriority();
               
                  if (getTotalDayBlocksWaited() > 0
                                          && getTotalDayBlocksWaited() % 2 == 0)
                     requestPriority++;
               
                  if (requestPriority > 4) requestPriority = 4;
                  setCustomerPriority(requestPriority);
               
                  this.updateServiceRequest();
               }
            }
         }     //
      } //end of updated day blocks waited
   }


	// Function: Find Service Request based on service request number
	// Input: int service request number
	// Output: service request found - true\false
   boolean findServiceRequest(int serviceRequestNumber)
   {
      Service_Request_File srFile = new Service_Request_File();
      String buffer = srFile.getServiceRequest(serviceRequestNumber);
   
      if (buffer == null)
      {
         System.out.println("Service Request not found.\n Returning to main menu.\n");
         return false;
      } 
      else 
      {
         this.setServiceRequestInformation(buffer);
         return true;
      }	
   }

	
	// Function: Find Service request given user input of service request number
	// Input: None
	// Output: Service request found - true\false
   boolean findServiceRequest()
   {
      int serviceRequestNumber;
   
      System.out.print("Please enter Service Request Number: ");
      serviceRequestNumber = Get_Input.readNumber(9999999);
      return findServiceRequest(serviceRequestNumber);
   
   }

	
	// Function: get Completed Information for Service Request from user and update service Request
	//	and call Service Request File class to update the file
	// Input: None
	// Output: return total # of completed requests for this client
   int getCompletedInformation()
   {
      Service_Request_File srFile = new Service_Request_File();
            
      System.out.print("Please enter the total number of day blocks needed to complete job: ");
            
      this.totalDayBlocks = Get_Input.readNumber(100);
   
      System.out.print("Please enter the total number of night blocks needed to complete job: ");
      this.totalNightBlocks = Get_Input.readNumber(100);
   
      System.out.println("The current date/time is " + Get_Input.getCurrentDateTime() + ".");
      System.out.println("Please enter when the request was completed: ");
   
      this.dateTimeCompleted = Get_Input.getDateTime();
      this.Status = 'c';
   
      this.updateServiceRequest();
   
      return srFile.getNumberCompletedRequestsForClient(getCustomerNumber());
   
   }



	// Function: Get Number of Service Request with Higher Priority
	// Input: int priority to compare to, and long timestamp of the date\time
	// Output: List of service request in buffer string that have higher priority
   static String getNumberServiceRequestsWithHigherPriority(int priority, long timestamp)
   {
      Service_Request_File srFile = new Service_Request_File();
      return srFile.getServiceRequestsWithHigherPriority(priority, timestamp);
   }


	// Function: Check to see if Service Request has been assigned for particular Technician
	// Input: int shift assigned, and technician number
	// Output: true\false if assignment was found
   boolean getServiceRequestForTech(int shift, int technicianNumber)
   {
      Service_Request_File srFile = new Service_Request_File();
      String request;
   
      request = srFile.getServiceRequestForTechnician(shift, technicianNumber);
      if (request.compareTo("") != 0)
      {
         setServiceRequestInformation(request);
         return true;
      }
      return false;
   }


	// Function: Get list of service requests given a certain status
	// Input: string status (p-pending, a-assigned, c-complete, b-billed)
	// Output: String buffer list of service request numbers
   static String getServiceRequestsByStatus(String status)
   {
      Service_Request_File srFile = new Service_Request_File();
      return srFile.getServiceRequestsByStatus(status);
   }


	// Function: Get list of service requests given a certain status and priority
	// Input: string status (p-pending, a-assigned, c-complete, b-billed),
	//	priority 1-4
	// Output: String buffer list of service request numbers
   static String getServiceRequestsByStatusAndPriority(String status, int priority)
   {
      Service_Request_File srFile = new Service_Request_File();
      return srFile.getServiceRequestsByStatusByPriority(status, priority);
   }


	// Function: call to print service request assignments report given set service requset info & shift
	// Input: Shift for assignments & string buffer of service request info
	// Output: None
   void printServiceRequestAssignmentsReport(int shift, String reportInfo)
   {
      Print_Service_Request_Assignment_Report.printServiceRequestAssignmentsReport(shift, reportInfo);
   }


	// Function: call to print invoice report given service requset info needing to be billed
	// Input: string buffer of service request\customer info for invoice report
	// Output: None
   void printInvoiceReport(String reportInfo)
   {
      Print_Invoice_Report.printInvoiceReport(reportInfo);
   }

	
	// Function: call to print waiting list report given service request\customer info on waiting list
	//	including new estimated service wait time
	// Input: String buffer of information to be printed to file
	// Output: None
   void printWaitingListReport(String reportInfo)
   {
      Print_Waiting_List_Report.printWaitingListReport(reportInfo);
   }

	
	// Function: call to print Outstanding Job report given current assigned jobs not yet completed
	//	and customer information so assitant can call customer for night tech 
	// Input: String buffer of information to be printed to file
	// Output: None
   void printOutstandingJobReport(String reportInfo)
   {
      Print_Outstanding_Job_Report.printOutstandingJobReport(reportInfo);
   }


	// Function: call to print Statistics to file
	// Input: string buffer of statistics info to be printed to file
	// Output: None
   void printStatisticsReport(String reportInfo)
   {
      Print_Statistics_Report.printStatisticsReport(reportInfo);
   }
}
