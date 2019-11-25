import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.*;

/**
 * Summary description for Class1.
 */
public class Osric
{

	// Function: Main Osric function to run the main menu loop
	// Input: Standard String[] args
	// Ouput: None
   public static void main(String[] args)
   {
      int Selection = 0;			//user's selection
      Maintain_Customer maintainCustomer;
      Maintain_Service_Request maintainServiceRequest;
      int customerNumber;
   	
   	// Loop for default employee menu for Oasis
      while(Selection != 11)
      {
         System.out.println();
         System.out.println();
         System.out.println("*****************************************");
         System.out.println("    Osric's OOAD Main Screen		     ");
         System.out.println("*****************************************");
         System.out.println(" ");
         System.out.println("Please select from the options below:");
         System.out.println("	1) Add a customer");
         System.out.println("	2) Search for customer");
         System.out.println("	3) Update Customer Priority");
         System.out.println("	4) Estimate Wait Time");
         System.out.println("	5) Add Service Request");
         System.out.println("	6) Complete Service Request");
         System.out.println("	7) Maintain Technicians");
         System.out.println("	8) Assign Technicians to Requests");
         System.out.println("	9) Update Service Request");
         System.out.println("	10) Reports");
         System.out.println("	11) Quit");	
         System.out.println("   12) Delete Customer");
      
      	// reading in input from user for menu choice
         System.out.print("Please choose a menu option: ");
         Selection = Get_Input.readSelection(12); 
         while (Selection < 0) Selection = Get_Input.readNumber(11);
      
         switch (Selection)
         {
            case 1:
            	//Add a customer
               maintainCustomer = new Maintain_Customer();
               customerNumber = maintainCustomer.addCustomer();
               System.out.println("New customer number " + Integer.toString(customerNumber) + " added");
               break;
            case 2:
            	//Search for a customer
               maintainCustomer = new Maintain_Customer();
            	
               String buffer = maintainCustomer.searchCustomer();
               System.out.println(buffer);
               break;
            case 3:
            	//update customer priority
               System.out.println("Not yet implemented.  Try back later");
               break;
            case 4:
            	//Estimate wait time
            	
               double estimatedWaitTime, worstCaseEstimatedWaitTime;
               maintainServiceRequest = new Maintain_Service_Request();
               estimatedWaitTime = maintainServiceRequest.Estimate_Service_Request_Time(false);
            
               worstCaseEstimatedWaitTime = maintainServiceRequest.Estimate_Service_Request_Time(true);
            
               System.out.println("The estimated wait time is " + Double.toString(estimatedWaitTime) + " hours.");
               System.out.println("The worst-case estimated wait time is " + Double.toString(worstCaseEstimatedWaitTime) + " hours.");
            
            
               break;
            case 5:
            	//add service request
               maintainServiceRequest = new Maintain_Service_Request();
               maintainServiceRequest.Add_Service_Request();
               break;
            case 6:
            	//Complete service request
               maintainServiceRequest = new Maintain_Service_Request();
               maintainServiceRequest.Complete_Service_Request();
               break;
            case 7:
            	//Maintain tech submenu
               MaintainTechnician();
               break;
            case 8:
            	//Assign requests
               maintainServiceRequest = new Maintain_Service_Request();
               maintainServiceRequest.Assign_Service_Requests();
               break;
            case 9: 
            	//Update service request
               maintainServiceRequest = new Maintain_Service_Request();
               maintainServiceRequest.Request_Night_Technician();
               break;
            case 10:
            	//Reports submenu
               RunReports();
               break;
            case 12:
               maintainCustomer = new Maintain_Customer();
               customerNumber = maintainCustomer.deleteCustomer();
               System.out.println("Customer Deleted customer number " + Integer.toString(customerNumber) + " added");
               
               
               break;
            default:
               break;
         }
         System.out.println("\nPress return to continue");
         Get_Input.readLine();
      }
   }	//End of main function


	// Functions: Technician Maintain Menu to give technician options
	// Input: None
	// Output: None
   public static void MaintainTechnician()
   {
      int Selection = 0;
      Maintain_Technician maintainTechnician;
   	
      while(Selection != 4)
      {
      
         System.out.println();
         System.out.println();
         System.out.println("*****************************************");
         System.out.println("    Osric's OOAD Maintain Tech Screen    ");
         System.out.println("*****************************************");
         System.out.println(" ");
         System.out.println("Please select from the options below:");
         System.out.println("	1) Add technician");
         System.out.println("	2) Update technician");
         System.out.println("	3) Delete technician");
         System.out.println("	4) Quit");
      
      	// reading in input from user for menu choice
         System.out.print("Please choose a menu option: ");
         Selection = Get_Input.readSelection(4); 
         while (Selection < 0) Selection = Get_Input.readNumber(4);
      
         switch (Selection)
         {
            case 1:
               maintainTechnician = new Maintain_Technician();
               maintainTechnician.addTechnician();
               System.out.println("\nTechnician successfully added.");
               break;
            case 2:
               maintainTechnician = new Maintain_Technician();
               maintainTechnician.updateTechnician();
               System.out.println("\nTechnician successfully updated.");
               break;
            case 3:
               maintainTechnician = new Maintain_Technician();
               maintainTechnician.deleteTechnician();
               System.out.println("\nTechnician successfully deleted.");
               break;
            case 4:
            default:
               return;
         }
      	
         System.out.println("\nPress return to continue");
         Get_Input.readLine();
      
      }
   }

	
	// Function: Reports Main Menu
	// Input: None
	// Output: None
   static void RunReports()
   {
      Print_Reports printReports;
      int Selection = 0;
   
      while(Selection != 6)
      {
      
         System.out.println();
         System.out.println();
         System.out.println("*****************************************");
         System.out.println("    Osric's OOAD Reports Screen		     ");
         System.out.println("*****************************************");
         System.out.println(" ");
         System.out.println("Please select from the options below:");
         System.out.println("	1) Print Outstanding Job Report");
         System.out.println("	2) Print Service Request Assignments Report");
         System.out.println("	3) Print Invoice Report");
         System.out.println("	4) Print Waiting List Report");
         System.out.println("	5) Print Statistics Report");
         System.out.println("	6) Quit");
      
      	// reading in input from user for menu choice
         System.out.print("Please choose a menu option: ");
         Selection = Get_Input.readSelection(6); 
         while (Selection < 0) Selection = Get_Input.readNumber(6);
      
         switch (Selection)
         {
            case 1:
               printReports = new Print_Reports();
               printReports.PrintOutstandingJobReport();
               break;
            case 2:
               printReports = new Print_Reports();
               printReports.PrintServiceAssignmentsReport();
               break;
            case 3:
               printReports = new Print_Reports();
               printReports.PrintInvoiceReport();
               break;
            case 4:
               printReports = new Print_Reports();
               printReports.PrintWaitingListReport();
               break;
            case 5:
               printReports = new Print_Reports();
               printReports.PrintStatisticsReport();
               break;
            default:
               return;
         }
         System.out.println("\nReport printed successfully");
         System.out.println("\nPress return to continue");
         Get_Input.readLine();
      }
   }
}
