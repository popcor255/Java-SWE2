public class Maintain_Customer
{
   public Maintain_Customer()
   {
   }
	// Function: add customer control class to set\add new customer information
	//  and to return new customer number
	// Input: None
	// Output: int new customer number
   int addCustomer()
   {
   	//Get customer information
      Customer customer = new Customer();
      customer.setCustomerInformation();
   
      return customer.getCustomerNumber();
   }

   int deleteCustomer()
   {
      Customer customer = new Customer();
      String buffer = customer.searchCustomer();
      String ret;
      if (buffer.compareTo("") != 0)
      {
         customer = new Customer(buffer);
         System.out.println("Customer found:\n" +
              "Number   : " + Integer.toString(customer.getCustomerNumber()) + "\n" +
              "Name     : " + customer.getCustomerName() + "\n" +
              "Priority : " + Integer.toString(customer.getCustomerPriority()));	
         customer.deleteCustomer();
         System.out.println("Now deleting this ....");			
      }	 
      else 
      {
         System.out.println("Customer not found.");
      }
   	
      return 1;
   }

	/*
	// Function: Update customer given current customer information and new updated info
	//	function currently not enabled due to no current requirement to update any other
	//	information beyond customer priority
	// Input: customer number for customer to be updated
	// Output: None */
   
   void updateCustomer(int customerNumber)
   {
    //Get the current customer's information
      Customer_File cFile = new Customer_File();
      Customer customer = new Customer(cFile.getCustomer(customerNumber));
   
    //Get customer information
      String yn;
   
      System.out.print("Customer's current name: " + customer.getCustomerName());
      System.out.print("Please enter the customer's name: ");
      customer.setCustomerName(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter Address Line 1: ");
      customer.setCustomerAddress1(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter Address Line 2: ");
      customer.setCustomerAddress2(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter City: ");
      customer.setCustomerCity(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter Province: ");
      customer.setCustomerProvince(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter Postal Code: ");
      customer.setCustomerPostalCode(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter Country: ");
      customer.setCustomerCountry(Get_Input.readString(50));
   
      System.out.print("Customer's current  : " + customer);
      System.out.print("Please enter Phone Number (no dashes or spaces): ");
      customer.setCustomerPhoneNumber(Get_Input.readString(50));
   
      System.out.print("Has this customer had an office decorated by Osric? (y/n): ");
      yn = Get_Input.readString(1);
      while (yn != "n" && yn != "N" && yn != "y" && yn != "Y")
         yn = Get_Input.readString(1);
   
      if (yn == "n" || yn == "N")
         customer.setCustomerPriority(1);
      else
         customer.setCustomerPriority(4);
   
      int number = cFile.getNewNumber();
      customer.setCustomerNumber(Integer.toString(number));
   }
	 


	// Function: update customer priority 
	// Input: customer number and new customer priority
	// Output: None
   void updateCustomerPriority(int customerNumber, int Priority)
   {
      Customer customer = new Customer();
      customer.findCustomer(customerNumber);
      customer.setCustomerPriority(Priority);
      customer.updateCustomer();
   }

	
	// Function: search customer control function
	// Input: None
	// Output: Customer Name, Number, and Priority if customer found; Customer Not found otherwise
   String searchCustomer()
   {
      Customer customer = new Customer();
      String buffer = customer.searchCustomer();
      String ret;
      if (buffer.compareTo("") != 0)
      {
         customer = new Customer(buffer);
         ret = "Customer found:\n" +
              "Number   : " + Integer.toString(customer.getCustomerNumber()) + "\n" +
              "Name     : " + customer.getCustomerName() + "\n" +
              "Priority : " + Integer.toString(customer.getCustomerPriority());				
      }	 
      else 
      {
         ret = "Customer not found.";
      }
   	
      return ret;
   }
}
