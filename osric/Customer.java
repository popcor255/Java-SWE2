/**
 * Summary description for Customer.
 */
public class Customer
{
   private int Number, Priority;
   private String Name, Address1, Address2, City, Province, PostalCode, Country, PhoneNumber;

   public Customer()
   {
   }

	// Function setting Customer attributes
	// Input: Customer attributes
	// Output: None
   public Customer(String id, String name, String address1, String address2,
   				String city, String province, String postalcode, 
   				String country, String phonenumber, String priority)
   {
      Number = Integer.parseInt(id);
      Name = name;
      Address1 = address1;
      Address2 = address2;
      City = city;
      Province = province;
      PostalCode = postalcode;
      Country = country;
      PhoneNumber = phonenumber;
      Priority = Integer.parseInt(priority);
   }


	// Function calling Set Information
	// Input: Customer buffer information to be set
	// Output: None
   public Customer(String bufferinfo)
   {
      setInformation(bufferinfo);
   }



//****************************  Set Functions  ********************************  

   void setCustomerNumber(String newNumber)
   {
      Number = Integer.parseInt(newNumber);
   }


   void setCustomerName(String newName)
   {
      Name = newName;
   }
   String getCustomerName()
   {
      return Name;
   }


   void setCustomerAddress1(String newAddress1)
   {
      Address1 = newAddress1;
   }

	
   void setCustomerAddress2(String newAddress2)
   {
      Address2 = newAddress2;
   }


   void setCustomerCity(String newCity)
   {
      City = newCity;
   }

   void setCustomerPostalCode(String newPostalCode)
   {
      PostalCode = newPostalCode;
   }

   void setCustomerProvince(String newProvince)
   {
      Province = newProvince;
   }

   void setCustomerCountry(String newCountry)
   {
      Country = newCountry;
   }


   void setCustomerPhoneNumber(String newPhoneNumber)
   {
      PhoneNumber = newPhoneNumber;
   }


   void setCustomerPriority(int newPriority)
   {
      Priority = newPriority;
   }

	
	// Function: Function to get new customer information from user, set new information
	//	and save information to Customer_File
	// Input: None
	// Output: int new customer number
   int setCustomerInformation()
   {
      String name;
      Customer_File cFile = new Customer_File();
      String yn;
   
      System.out.print("Please enter the customer's name: ");
      name = Get_Input.readString(50);
   	
   	//Customer not found, get a new number
      int number = cFile.getNewNumber();
      setCustomerNumber(Integer.toString(number));
   
      setCustomerName(name);
   	
      System.out.print("Please enter Address Line 1: ");
      setCustomerAddress1(Get_Input.readString(50));
   
      System.out.print("Please enter Address Line 2: ");
      setCustomerAddress2(Get_Input.readString(50));
   
      System.out.print("Please enter City: ");
      setCustomerCity(Get_Input.readString(50));
   
      System.out.print("Please enter State/Province: ");
      setCustomerProvince(Get_Input.readString(50));
   
      System.out.print("Please enter Postal Code: ");
      setCustomerPostalCode(Get_Input.readString(50));
   
      System.out.print("Please enter Country: ");
      setCustomerCountry(Get_Input.readString(50));
   
      System.out.print("Please enter Phone Number: ");
      setCustomerPhoneNumber(Get_Input.readString(50));
   
      do
      {
         System.out.print("Has this customer had an office decorated by Osric? (y/n): ");
         yn = Get_Input.readString(1);
      } while (!yn.startsWith("y") && !yn.startsWith("n"));
   
      if (yn.startsWith("y"))
         setCustomerPriority(4);
      else
         setCustomerPriority(1);
   
      cFile.addCustomer(getCustomerNumber() + "#" +
         getCustomerName() + "#" +
         getCustomerAddress1() + "#" + 
         getCustomerAddress2() + "#" + 
         getCustomerCity() + "#" + 
         getCustomerProvince() + "#" + 
         getCustomerPostalCode() + "#" + 
         getCustomerCountry() + "#" + 
         getCustomerPhoneNumber() + "#" + 
         getCustomerPriority());
   
      return this.getCustomerNumber();
   }

   public int deleteCustomer(){
      Customer_File cFile = new Customer_File();
      cFile.deleteCustomer(getCustomerNumber() + "#" +
         getCustomerName() + "#" +
         getCustomerAddress1() + "#" + 
         getCustomerAddress2() + "#" + 
         getCustomerCity() + "#" + 
         getCustomerProvince() + "#" + 
         getCustomerPostalCode() + "#" + 
         getCustomerCountry() + "#" + 
         getCustomerPhoneNumber() + "#" + 
         getCustomerPriority());
   
      return 1;
   }
	
	// Function: Set Information based on String Buffer returned from Customer_File
	// Input: String Buffer of customer Information
	// Output: None
   private void setInformation(String buffer)
   {
      String buffersplit[] = buffer.split("#");
      Number = Integer.parseInt(buffersplit[0]);
      Name = buffersplit[1];
      Address1 = buffersplit[2];
      Address2 = buffersplit[3];
      City = buffersplit[4];
      Province = buffersplit[5];
      PostalCode = buffersplit[6];
      Country = buffersplit[7];
      PhoneNumber = buffersplit[8];
      Priority = Integer.parseInt(buffersplit[9]);
   }

//****************************  Get Functions ***************************

   int getCustomerNumber()
   {
      return Number;
   }

   String getCustomerAddress1()
   {
      return Address1;
   }

   String getCustomerAddress2()
   {
      return Address2;
   }

   String getCustomerCity()
   {
      return City;
   }

   String getCustomerPostalCode()
   {
      return PostalCode;
   }

   String getCustomerProvince()
   {
      return Province;
   }

	
   String getCustomerCountry()
   {
      return Country;
   }

   String getCustomerPhoneNumber()
   {
      return PhoneNumber;
   }

   int getCustomerPriority()
   {
      return Priority;
   }


//****************************  Search Functions ***************************
	
	// Function: Search Customer Based on Input of Customer Name
	// Input: None
	// Output: String buffer of Customer's information if found
   String searchCustomer()
   {
      System.out.print("Name of customer to search for: ");
      String customerName = Get_Input.readString(50);
   			
      Customer_File cFile = new Customer_File();
      return cFile.findCustomerNumber(customerName);
   }

	
	// Function: Find Customer based on inputted customer number
	// Input: None
	// Output: true\false customer found
   boolean findCustomer()
   {
      System.out.print("Please enter the customer number: ");
      int customerNumber = Get_Input.readNumber(9999999);
   	
      return findCustomer(customerNumber);
   }


	// Function: Find Customer based on customer number passed in
	// Input: int customer number
	// Output: true\false customer found
   boolean findCustomer(int customerNumber)
   {
      Customer_File cFile = new Customer_File();
      String buffer;
      try 
      {
         buffer = cFile.getCustomer(customerNumber);
         setInformation(buffer);
         return true;
      } 
      catch (Exception e)
      {
         System.out.println("Customer number " + customerNumber + " not found.");
      	//System.out.println("Returning to main menu.");
         return false;
      }
   }


	// Function: Set customer priority based on completed service requests
	// Input: int number of completed service requests
	// Output: None
   void setPriorityFromCompletedServiceRequests(int numComletedServiceRequests)
   {
      if (getCustomerPriority() < 4)
      {
         if (getCustomerPriority() < 3 && numComletedServiceRequests >= 3)
         {
            this.Priority = 3;
            updateCustomer();
         	
         }
         else if (getCustomerPriority() < 2 && numComletedServiceRequests >= 2)
         {
            this.Priority = 2;
            updateCustomer();
         }
      }
   }


	// Function: Call to Customer_File to update customer file with set customer information
	// Input: None
	// Output: None
   void updateCustomer()
   {
      Customer_File cFile = new Customer_File();
   
      cFile.updateCustomer(getCustomerNumber() + "#" +
         getCustomerName() + "#" +
         getCustomerAddress1() + "#" + 
         getCustomerAddress2() + "#" + 
         getCustomerCity() + "#" + 
         getCustomerProvince() + "#" + 
         getCustomerPostalCode() + "#" + 
         getCustomerCountry() + "#" + 
         getCustomerPhoneNumber() + "#" + 
         getCustomerPriority());
   
   }
}
