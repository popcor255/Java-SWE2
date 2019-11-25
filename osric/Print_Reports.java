/**
 * Summary description for Customer.
 */
public class Print_Reports
{

	// Function: Gather information to print Service Request Assignements report
	// Input: None
	// Output: None
   void PrintServiceAssignmentsReport()
   {
      int shift;
      String techs;
      String[] techsplit;
      String reportOutput = "";	
   
      Service_Request serviceRequest = new Service_Request();
      Technician tech = new Technician();
   
      System.out.print("What shift are you printing assignments for? (1 = day, 2 = night1, 3 = night2) ");
      shift = Get_Input.readSelection(3);
      shift--;
   
      techs = tech.getTechniciansByShift(shift);
   	
      if (techs.compareTo("") != 0)
      {
         techsplit = techs.split("#");
         for (int i = 0; i < techsplit.length; i++)
         {
         	//get request for this technician (and shift)
            if (serviceRequest.getServiceRequestForTech(shift, Integer.parseInt(techsplit[i])))
            {
            	//Get customer from this request
               Customer customer = new Customer();
               customer.findCustomer(serviceRequest.getCustomerNumber());
            	
            	//And the tech
               tech.findTechnician(Integer.parseInt(techsplit[i]));
            
               if (reportOutput.compareTo("") != 0)
                  reportOutput += "\n";
            	
               reportOutput += Integer.toString(customer.getCustomerNumber()) + "#" +
                  		customer.getCustomerName() + "#" + 
                  		customer.getCustomerAddress1() + "#" + 
                  		customer.getCustomerAddress2() + "#" + 
                  		customer.getCustomerCity() + "#" + 
                  		customer.getCustomerProvince() + "#" + 
                  		customer.getCustomerPostalCode() + "#" + 
                  		customer.getCustomerCountry() + "#" + 
                  		customer.getCustomerPhoneNumber() + "#" + 
                  		tech.getTechnicianNumber() + "#" +	//9 
                  		tech.getTechnicianName() + "#" + 
                  		serviceRequest.getServiceRequestNumber() + "#" + 
                  		serviceRequest.getComments();
            }
         }	//end of looping over all techs
      	
         serviceRequest.printServiceRequestAssignmentsReport(shift, reportOutput);
      }
   }


	// Function: Gather information to print Invoice Report & update all service requests that are billed successfully
	//	to a B(illed) status so as to not be printed again
	// Input: None
	// Output: None
   void PrintInvoiceReport()
   {
      Service_Request serviceRequest = new Service_Request();
      Customer customer = new Customer();
   
      String requests, reportOutput = "";
      String[] requestSplit;
   
      requests = serviceRequest.getServiceRequestsByStatus("c");
   
      if (requests.compareTo("") != 0)
      {
         requestSplit = requests.split("#");
         for (int i = 0; i < requestSplit.length; i++)
         {
            if (serviceRequest.findServiceRequest(Integer.parseInt(requestSplit[i])))
            {
            	//Get customer info
               customer.findCustomer(serviceRequest.getCustomerNumber());
            
               if (reportOutput.compareTo("") != 0)
                  reportOutput += "\n";
            	
               reportOutput += Integer.toString(customer.getCustomerNumber()) + "#" + //0
                  customer.getCustomerName() + "#" + 
                  customer.getCustomerAddress1() + "#" + 
                  customer.getCustomerAddress2() + "#" + 
                  customer.getCustomerCity() + "#" + 
                  customer.getCustomerProvince() + "#" + //5
                  customer.getCustomerPostalCode() + "#" + 
                  customer.getCustomerCountry() + "#" + 
                  customer.getCustomerPhoneNumber() + "#" + 
                  serviceRequest.getTotalDayBlocks() + "#" +	 
                  serviceRequest.getTotalNightBlocks() + "#" + //10
                  serviceRequest.getServiceRequestNumber() + "#" + 
                  serviceRequest.getDateTimeCompleted();	
            
            	//and update that the request was billed
               serviceRequest.setStatus('b');
               serviceRequest.updateServiceRequest();
            }
         	
         }
         serviceRequest.printInvoiceReport(reportOutput);
      }
   }


	// Function: Gather information and call to print Waiting List Report
	//	As part of this also need to estimate service wait time to be included on the report
	// Input: None
	// Output: None
   void PrintWaitingListReport()
   {
      Service_Request serviceRequest = new Service_Request();
      Customer customer = new Customer();
   
      String requests, reportOutput = "";
      String[] requestSplit;
   
   	//get all pending requests by status
      for (int priority = 4; priority > 0; priority--)
      {
      
      	// get service requests by priority and status (of pending - waiting to be assigned)
         requests = serviceRequest.getServiceRequestsByStatusAndPriority("p", priority);
      
         if (requests.compareTo("") != 0)
         {
            requestSplit = requests.split("#");
            for (int i = 0; i < requestSplit.length; i++)
            {
            	//Get each service request
               if (serviceRequest.findServiceRequest(Integer.parseInt(requestSplit[i])))
               {
               	//Get customer info
                  customer.findCustomer(serviceRequest.getCustomerNumber());
               
               	// Estimate new service wait time to be printed on report
                  Maintain_Service_Request maintain = new Maintain_Service_Request();
                  double estimatedTime = maintain.Estimate_Service_Request_Time_For_Customer(customer.getCustomerNumber());
               
                  if (reportOutput.compareTo("") != 0)
                     reportOutput += "\n";
               
                  reportOutput += customer.getCustomerNumber() + "#" +
                     customer.getCustomerName() + "#" +
                     customer.getCustomerPhoneNumber() + "#" +
                     serviceRequest.getServiceRequestNumber() + "#" +
                     serviceRequest.getCustomerPriority() + "#" +
                     serviceRequest.getComments() + "#" + //5
                     serviceRequest.getDateTimeEntered() + "#" +
                     Double.toString(estimatedTime);
               }
            }
         }	//end of testing if we had any matching requests
      } //end of looping through priorities
   	
   	//And print the report
      serviceRequest.printWaitingListReport(reportOutput);
   }


	// Function: Gather information & call print of Outstanding Job Report: This report will be used by the Assistant
	//	at 4pm to call each customer to see if they want a night technician for every assigned service request
	//	Report is ordered by priority\datetime entered
	// Input: None
	// Output: None
   void PrintOutstandingJobReport()
   {
      Service_Request serviceRequest = new Service_Request();
      Customer customer = new Customer();
   
      String requests, reportOutput = "";
      String[] requestSplit;
   
   	//get all pending requests by status
      for (int priority = 4; priority > 0; priority--)
      {
      
         requests = serviceRequest.getServiceRequestsByStatusAndPriority("a", priority);
      
         if (requests.compareTo("") != 0)
         {
            requestSplit = requests.split("#");
            for (int i = 0; i < requestSplit.length; i++)
            {
            	//Get each service request
               if (serviceRequest.findServiceRequest(Integer.parseInt(requestSplit[i])))
               {
               	//Get customer info
                  customer.findCustomer(serviceRequest.getCustomerNumber());
               
                  if (reportOutput.compareTo("") != 0)
                     reportOutput += "\n";
               
                  reportOutput += customer.getCustomerNumber() + "#" +
                     customer.getCustomerName() + "#" +
                     customer.getCustomerPhoneNumber() + "#" +
                     serviceRequest.getServiceRequestNumber() + "#" +
                     serviceRequest.getCustomerPriority() + "#" +
                     serviceRequest.getComments() + "#" + //5
                     serviceRequest.getDateTimeEntered() + "#";
               }
            }
         }	//end of testing if we had any matching requests
      } //end of looping through priorities
   	
   	//And print the report
      serviceRequest.printOutstandingJobReport(reportOutput);
   }


	// Function: Gather Statistics and call print Statistics class
	// Input: None
	// Output: None
   void PrintStatisticsReport()
   {
      String techs, requests;
      String[] techsplit, requestsplit; 
      Service_Request serviceRequest = new Service_Request();
      Technician tech = new Technician();
   	//Customer customer = new Customer();
   
   	//Setting default variables first
      int queueLength, shift, unavailableNightTechs = 0, numIdleTechs = 0, numAssignedRequests = 0;
      long priority1wait = 0, priority2wait = 0, priority3wait = 0, priority4wait = 0;
   	
   	//Get the shift we're running this for
      System.out.print("What shift are you generating statistics for? (1 = day, 2 = night1, 3 = night2) ");
      shift = Get_Input.readSelection(3);
      shift--;
   
   	//# techs, to determine # idle techs
      techs = tech.getTechniciansByShift(shift);
      techsplit = techs.split("#");
   	
   	//# pending requests  Find: queue length, % time empty
      requests = serviceRequest.getServiceRequestsByStatus("p");
      requestsplit = requests.split("#");
      queueLength = requestsplit.length;
   
   	//Get assigned requests.  Find: # idle techs, jobs that can't be continued b/c no night tech
      requests = serviceRequest.getServiceRequestsByStatus("a");
   	
      if (requests.compareTo("") != 0)
      {
         requestsplit = requests.split("#");
      	//numAssignedRequests = requestsplit.length;
      	
         for (int i = 0; i < requestsplit.length; i++)
         {
            serviceRequest.findServiceRequest(Integer.parseInt(requestsplit[i]));
         	//customer.findCustomer(serviceRequest.getCustomerNumber());
         
         	//Get time waited per customer priority
         	//switch(customer.getCustomerPriority())
            switch(serviceRequest.getCustomerPriority())
            {
               case 1:	priority1wait = serviceRequest.getDateTimeAssigned() - serviceRequest.getDateTimeEntered();
                  break;
               case 2:	priority2wait = serviceRequest.getDateTimeAssigned() - serviceRequest.getDateTimeEntered();
                  break;
               case 3:	priority3wait = serviceRequest.getDateTimeAssigned() - serviceRequest.getDateTimeEntered();
                  break;
               case 4:	priority4wait = serviceRequest.getDateTimeAssigned() - serviceRequest.getDateTimeEntered();
                  break;
            }
         
            switch (shift)
            {
               case 0:
                  if (serviceRequest.getDayTechnician() != 0)
                     numAssignedRequests++;
                  break;
               case 1:
                  if (serviceRequest.getNightTechnician1() == 0)
                  {
                     if (serviceRequest.getRequestNightTechnician() == 'y')
                        unavailableNightTechs++;
                  }
                  else
                     numAssignedRequests++;
                  break;
               case 2:
                  if (serviceRequest.getNightTechnician2() == 0)
                  {
                     if (serviceRequest.getRequestNightTechnician() == 'y')
                        unavailableNightTechs++;
                  }
                  else
                     numAssignedRequests++;
                  break;
            }
         }	//end of looping through assigned service requests
      }
   
   	//Find the # of idle techs
      numIdleTechs = techsplit.length - numAssignedRequests;
   
   	//new record to pass in!
      String reportInfo = Integer.toString(shift) + "#" +
         Integer.toString(queueLength) + "#" +
         Integer.toString(numIdleTechs) + "#" +
         Integer.toString(unavailableNightTechs) + "#" + 
         Long.toString(priority1wait) + "#" +
         Long.toString(priority2wait) + "#" +				//5
         Long.toString(priority3wait) + "#" +
         Long.toString(priority4wait);
   
   	//And add the new row
      serviceRequest.printStatisticsReport(reportInfo);
   
   }
}
