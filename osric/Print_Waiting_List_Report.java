import java.io.*;

/**
 * Summary description for Print_Waiting_List_Report.
 */
public class Print_Waiting_List_Report
{

	// Function: Print Waiting List Report information to file
	// Input: Buffer of waiting lists information to be printed
	// Output: None
   static void printWaitingListReport(String reportInfo)
   {
      String[] bufferSplit, dataSplit;
      java.util.Date date = new java.util.Date();
   
      String dateString = Integer.toString((date.getMonth()+1)) + "-" +
         Integer.toString(date.getDate()) + "-" +
         Integer.toString(date.getYear() + 1900);
   	
      try
      {
         PrintWriter waitingListReport = new PrintWriter(new FileWriter("Waiting list " + dateString + ".txt", false));
         waitingListReport.write("******************** Waiting List Report " + dateString + " ******************************\n");
      			
         if (reportInfo.compareTo("") != 0)
         {
         	//For each tech, add to the output
            bufferSplit = reportInfo.split("\n");
            for (int i = 0; i < bufferSplit.length; i++)
            {
               dataSplit = bufferSplit[i].split("#");
               java.util.Date requestDate = new java.util.Date(Long.parseLong(dataSplit[6]));
            
               waitingListReport.write("Customer Number  : " + dataSplit[0] + "\n");
               waitingListReport.write("Customer Name    : " + dataSplit[1] + "\n");
               waitingListReport.write("Customer Phone # : " + dataSplit[2] + "\n");
               waitingListReport.write("Serive Request   : " + dataSplit[3] + "\tPriority : " + dataSplit[4] + "\n");
               waitingListReport.write("Date of request  : " + requestDate.toString() + "\n");
               waitingListReport.write("Estimated Wait   : " + dataSplit[7]);
               waitingListReport.write("\n\n\n");
            }
            waitingListReport.close();
         }
      }
      catch (IOException e)
      {
         System.out.println("Creating Waiting List Report IO Exception:");
      }
   
   }
}
