import java.io.*;

/**
 * Summary description for Print_Invoice_Report.
 */
public class Print_Invoice_Report
{
	// Function: Print Invoice Report to File
	// Input: String buffer of report info to be written to file
	// Output: None
   static void printInvoiceReport(String reportInfo)
   {
      String[] bufferSplit, dataSplit;
      java.util.Date date = new java.util.Date();
      java.util.Date invoiceDate;
   
      String dateString = Integer.toString((date.getMonth()+1)) + "-" +
         Integer.toString(date.getDate()) + "-" +
         Integer.toString(date.getYear() + 1900);
   	
      try
      {
         PrintWriter invoiceReport = new PrintWriter(new FileWriter("Invoice " + dateString + ".txt", false));
      
         if (reportInfo.compareTo("") != 0)
         {
         	//For each tech, add to the output
            bufferSplit = reportInfo.split("\n");
            for (int i = 0; i < bufferSplit.length; i++)
            {
               dataSplit = bufferSplit[i].split("#");
            	
               long l = Long.parseLong(dataSplit[12]);
               l += Long.parseLong("2592000000");
               invoiceDate = new java.util.Date(l);
            	
               invoiceReport.write("******************** Invoice for Service Request " + dataSplit[11] + "******************************\n");
               invoiceReport.write("Invoice Date:  " + date.toString() + "\n");
               invoiceReport.write("Customer Number  : " + dataSplit[0] + "\n");
               invoiceReport.write("Customer Name    : " + dataSplit[1] + "\n");
               invoiceReport.write("Customer Address : \n");
               invoiceReport.write(dataSplit[2] + "\n");
               invoiceReport.write(dataSplit[3] + "\n");
               invoiceReport.write(dataSplit[4] + ", " + dataSplit[5] + "  " + dataSplit[6] + "\n");
               invoiceReport.write(dataSplit[7] + "\n");
               invoiceReport.write("\n\nPlease pay to:\n");
               invoiceReport.write("Osric Ormondsey\n");
               invoiceReport.write("Suite 16, Kronborg Castle\n");
               invoiceReport.write("Helsingor, Sjaelland\n");
               invoiceReport.write("Denmark\n");
               invoiceReport.write("(No C.O.D.s)\n");
               invoiceReport.write("\n\n");
               invoiceReport.write("Total Day Blocks billed:  " + dataSplit[9]);
               invoiceReport.write("\t$" + 480 * Integer.parseInt(dataSplit[9]) + "\n");
               invoiceReport.write("Total Day Blocks billed:  " + dataSplit[10]);
               invoiceReport.write("\t$" + 960 * Integer.parseInt(dataSplit[10]) + "\n");
               int total = 480 * Integer.parseInt(dataSplit[9])
                  + 960 * Integer.parseInt(dataSplit[10]);
               invoiceReport.write("Total Amount Due: $" + Integer.toString(total) + "\n");
               invoiceReport.write("Due Date:		    " + invoiceDate.toString());
            
            }
            invoiceReport.close();
         }
      }
      catch (IOException e)
      {
         System.out.println("Creating Invoice Report IO Exception:");
      }
   
   }


}
