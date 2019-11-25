import java.io.*;

public class Print_Outstanding_Job_Report
{
	
	// Function: Print Outstanding Job Report to file
	// Input: String buffer of report info to print to file
	// Output: None
   static void printOutstandingJobReport(String reportInfo)
   {
      String[] bufferSplit, dataSplit;
      java.util.Date date = new java.util.Date();
   
      String dateString = Integer.toString((date.getMonth()+1)) + "-" +
         Integer.toString(date.getDate()) + "-" +
         Integer.toString(date.getYear() + 1900);
   	
      try
      {
         PrintWriter outstandingJobReport = new PrintWriter(new FileWriter("Outstanding Job Report " + dateString + ".txt", false));
         outstandingJobReport.write("******************** Outstanding Job Report " + dateString + " ******************************\n");
      			
         if (reportInfo.compareTo("") != 0)
         {
         	//For each tech, add to the output
            bufferSplit = reportInfo.split("\n");
            for (int i = 0; i < bufferSplit.length; i++)
            {
               dataSplit = bufferSplit[i].split("#");
               java.util.Date requestDate = new java.util.Date(Long.parseLong(dataSplit[6]));
            
               outstandingJobReport.write("Customer Number  : " + dataSplit[0] + "\n");
               outstandingJobReport.write("Customer Name    : " + dataSplit[1] + "\n");
               outstandingJobReport.write("Customer Phone # : " + dataSplit[2] + "\n");
               outstandingJobReport.write("Serive Request   : " + dataSplit[3] + "\tPriority : " + dataSplit[4] + "\n");
               outstandingJobReport.write("Date of request  : " + requestDate.toString() + "\n");
               outstandingJobReport.write("\n\n\n");
            }
            outstandingJobReport.close();
         }
      }
      catch (IOException e)
      {
         System.out.println("Creating Outstanding Job Report IO Exception:");
      }
   
   }
}
