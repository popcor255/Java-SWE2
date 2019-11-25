import java.io.*;

/**
 * Summary description for Print_Service_Request_Assignment_Report.
 */
public class Print_Service_Request_Assignment_Report
{

	// Function: Print Service Request Assignments Report information to file
	// Input: Shift of the Assignment being printed & service requests jobs assigned for that shift
	// Output: None
   static void printServiceRequestAssignmentsReport(int shift, String reportInfo)
   {
      String[] bufferSplit, dataSplit;
      java.util.Date date = new java.util.Date();
   	
      String dateString = Integer.toString((date.getMonth()+1)) + "-" +
         Integer.toString(date.getDate()) + "-" +
         Integer.toString(date.getYear() + 1900);
      if (shift == 0)
         dateString += " Day Shift";
      if (shift == 1)
         dateString += " Night Shift 1";
      if (shift == 2)
         dateString += " Night Shift 2";
   	
   	
      try
      {
         PrintWriter assignmentsReport = new PrintWriter(new FileWriter("Service_Request_Assignments " + dateString + ".txt", false));
      
         if (reportInfo.compareTo("") != 0)
         {
         	//For each tech, add to the output
            bufferSplit = reportInfo.split("\n");
            for (int i = 0; i < bufferSplit.length; i++)
            {
               dataSplit = bufferSplit[i].split("#");
               assignmentsReport.write("******************** Service Request " + dataSplit[11] + "******************************\n");
               assignmentsReport.write("Technican Number : " + dataSplit[9] + "\n");
               assignmentsReport.write("Technican Name   : " + dataSplit[10] + "\n");
               assignmentsReport.write("Customer Number  : " + dataSplit[0] + "\n");
               assignmentsReport.write("Customer Name    : " + dataSplit[1] + "\n");
               assignmentsReport.write("Customer Address : \n");
               assignmentsReport.write(dataSplit[2] + "\n");
               assignmentsReport.write(dataSplit[3] + "\n");
               assignmentsReport.write(dataSplit[4] + ", " + dataSplit[5] + "  " + dataSplit[6] + "\n");
               assignmentsReport.write(dataSplit[7] + "\n");
               assignmentsReport.write("Phone Number: " + dataSplit[8] + "\n");
               assignmentsReport.write("Comments: " + dataSplit[12] + "\n");
               assignmentsReport.write("\n\n\n\n\n");
            }
            assignmentsReport.close();
         }
      }
      catch (IOException e)
      {
         System.out.println("Creating Service_Request_Assignments.txt IO Exception:");
      	//return(0);
      }
   
   }

}