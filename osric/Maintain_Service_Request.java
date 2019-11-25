/**
 * Summary description for Maintain_Service_Request.
 */
public class Maintain_Service_Request
{
   int lastPriority;

   public Maintain_Service_Request()
   {
   }


	// Function: Estimate Service Request Time based on given worse case definition
	// Input: boolean worse case - to do worse case or not
	// Output: total wait time (+ worse case pentality)
   public double Estimate_Service_Request_Time(boolean worstCase)
   {
      Service_Request	serviceRequest = new Service_Request();
      int priority;
      double worstCasePenalty = 0.0;
   
   	//Check if we're doing worst case or not - if so, there's an extra penalty.
      if (!worstCase)
      {
      	//Find the customer
         Customer customer = new Customer();		
         if (!customer.findCustomer())
         {
            System.out.println("Customer not found - assuming new customer with Priority 1.");
            priority = 1;
         } 
         else 
         {
            priority = customer.getCustomerPriority();
         }
         lastPriority = priority;
      } 
      else 
      {
         priority = lastPriority;
         worstCasePenalty = 24 * (4 - lastPriority);
      }
   
   	
      String pendingRequests = Service_Request.getNumberServiceRequestsWithHigherPriority(priority, Long.MAX_VALUE);
      String[] requestSplit;
   
      double totalWaitTime = 0.0;
   
      if (pendingRequests.compareTo("") != 0)
      {
         requestSplit = pendingRequests.split("#");
         for (int i = 0; i < requestSplit.length; i++)
         {
            double std = 0;
            while (true)
            {
               std = 5.5 + 9.8 * Maintain_Service_Request.StdGaussian();
               if (std > 1.9 && std < 23.1)
                  break;
            }
            serviceRequest.findServiceRequest(Integer.parseInt(requestSplit[i]));
         	//If assigned, credit back the # of estimated completd 4-hour blocks
            if (serviceRequest.getStatus() == 'a')
            {
               totalWaitTime += Math.min(0.0, std - 4 * serviceRequest.getEstTotalBlocksUsed());
            } 
            else 
            {
               totalWaitTime += std;
            }
         }
      }
   
      return totalWaitTime + worstCasePenalty;
   }



	// Function: Estimate Service Request Time for Customer given customer number
	// Input: int customer number
	// Output: double estimated total wait time
   public double Estimate_Service_Request_Time_For_Customer(int customerNumber)
   {
      Service_Request	serviceRequest = new Service_Request();
      int priority;
      Customer customer = new Customer();
   
   	//Find the customer
      customer.findCustomer(customerNumber);
      priority = customer.getCustomerPriority();
   	
      String pendingRequests = Service_Request.getNumberServiceRequestsWithHigherPriority(priority, Long.MAX_VALUE);
      String[] requestSplit;
   
      double totalWaitTime = 0.0;
   
      if (pendingRequests.compareTo("") != 0)
      {
         requestSplit = pendingRequests.split("#");
         for (int i = 0; i < requestSplit.length; i++)
         {
            double std = 0;
            while (true)
            {
               std = 5.5 + 9.8 * Maintain_Service_Request.StdGaussian();
               if (std > 1.9 && std < 23.1)
                  break;
            }
            serviceRequest.findServiceRequest(Integer.parseInt(requestSplit[i]));
         	//If assigned, credit back the # of estimated completd 4-hour blocks
            if (serviceRequest.getStatus() == 'a')
            {
               totalWaitTime += Math.min(0.0, std - 4 * serviceRequest.getEstTotalBlocksUsed());
            } 
            else 
            {
               totalWaitTime += std;
            }
         }
      }
      return totalWaitTime;
   }


	// Function: Add Service Request control class to call find customer and new SR
	// Input: None
	// Output: None
   public void Add_Service_Request()
   {
      Customer customer = new Customer();
   	
   	//Find the customer
      if (!customer.findCustomer())
      {
         System.out.println("Returning to main menu.");
         return;
      }
   
      Service_Request serviceRequest;
      serviceRequest = new Service_Request();
   	
      serviceRequest.setServiceRequestInformation(customer.getCustomerNumber(),
         										customer.getCustomerPriority());		
   	
      System.out.println("Service request " + 
         				Integer.toString(serviceRequest.getServiceRequestNumber()) + 
         				" has been added.");
   
   }


	// Function: Complete Service Request Control class
	// Input: None
	// Output: None
   public void Complete_Service_Request()
   {
      int completedServiceRequests;
      Service_Request serviceRequest = new Service_Request();
   
   	//Getting Service Request Number to be completed
      if (!serviceRequest.findServiceRequest())
         return;
   	
   	//Set the information needed to complete the request
      completedServiceRequests = serviceRequest.getCompletedInformation();
   
   	//Update customer's status based on the number of completed requests, if needed
      Customer customer = new Customer();
      customer.findCustomer(serviceRequest.getCustomerNumber());
   
      customer.setPriorityFromCompletedServiceRequests(completedServiceRequests);
      System.out.println("\nService Request successfully completed.");
   }


	// Function: Assign Service Requests control class & get shift for assignments to be made
	// Input: None
	// Ouput: None
   public void Assign_Service_Requests()
   {
      Service_Request serviceRequest = new Service_Request();
      int shift;
   
      System.out.print("What shift are you assigning technicians for? (1 = day, 2 = night1, 3 = night2) ");
      shift = Get_Input.readSelection(3);
            
      shift = shift - 1;
   
      System.out.println("The current date/time is " + Get_Input.getCurrentDateTime() + ".");
      System.out.println("Please enter requests were assigned: ");
      long dateAssigned = Get_Input.getDateTime();
   
      serviceRequest.assignServiceRequests(shift, dateAssigned);
      System.out.println("\nService Request successfully assigned.");
   				
   }




	// Function: Request Night Technician: find service request to request night technician
	//	update with y or n for night technician for this customer's service request
	//	update service request file
	// Input: None
	// Output: None	
   public void Request_Night_Technician()
   {
      Service_Request serviceRequest = new Service_Request();
      String yn;
   
      if (!serviceRequest.findServiceRequest())
         return;
   
      System.out.print("Does the customer wish to request a night tech for this service request? (y/n) ");
      yn = Get_Input.readString(1);
      while (!yn.startsWith("n") && !yn.startsWith("y") )
         yn = Get_Input.readString(1);
   
   
      serviceRequest.setRequestNightTechnician(yn.charAt(0));
   
      serviceRequest.updateServiceRequest();	
      System.out.println("\nService Request successfully updated.");
   				
   }


	
	// Function: standard Gaussian function to help calculate wait estimate
	// Input: None
	// Output: randomly generated gaussian algorith output
   public static double StdGaussian()
   {
      double r, x, y;
      
   	// find a uniform random point (x, y) inside unit circle
      do 
      {
         x = 2.0 * Math.random() - 1.0;
         y = 2.0 * Math.random() - 1.0;
         r = x*x + y*y;
      } while (r > 1 || r == 0);	// loop executed 4 / pi = 1.273.. times on average
   								// http://en.wikipedia.org/wiki/Box-Muller_transform
   
   	// apply the Box-Muller formula to get standard Gaussian
      return x * Math.sqrt(-2.0 * Math.log(r) / r);
   }
}
