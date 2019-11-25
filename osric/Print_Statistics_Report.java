import java.io.*;

public class Print_Statistics_Report
{

	// Function: Print Statistics information to file
	// Input: String buffer of statistics information to be printed
	// Output: None
   static void printStatisticsReport(String reportInfo)
   {
   	//Open file, scan through it, waiting for the line with ####
   	//As we scan, we build up a recordcount and 
   
      String buffer;
      String[] buffersplit, reportsplit;
      int i = 1, k = 0;
      int queueLengthTotal = 0, emptyQueueCount = 0;
      int idleTechTotal = 0, noNightTechTotal = 0;
   
      String bufferstring = "", check = "";
   
      buffersplit = reportInfo.split("#");
   
      try
      {
      	// reading from provider file to get all data
         BufferedReader j = new BufferedReader (new FileReader("Statistics.txt"));
         buffer = j.readLine();
      
         if (buffer == null)
            System.out.println("No data in file trying to read");
         else
         {
         	//Load the entire file into memory, separated with newlines.
            if (buffer.compareTo("###") == 0)
            {
               buffer = "";
            } 
            else 
            {
               while (check.compareTo("###") != 0)
               {
                  i++;
                  if (check.compareTo("") != 0)
                     buffer += "\n" + check;
                  check = j.readLine();
               
               } //end of while
            }
            j.close();
            if (buffer.compareTo("") != 0)
               buffer += "\n";
            buffer += reportInfo;
         
         	// way to write out to file to overwrite data that has been updated
            PrintWriter out = new PrintWriter(new FileWriter("Statistics.txt", false));
            String bufferarray[] = buffer.split("\n");
         
            for (k = 0; k < i; k++)
            {	
               bufferstring = bufferarray[k];
               buffersplit = bufferstring.split("#");
            
            	//Write back the row
               out.write(bufferstring + "\n");	
            
            	//And gather running statistics
               queueLengthTotal += Integer.parseInt(buffersplit[1]);
            
               if (Integer.parseInt(buffersplit[1]) == 0)
                  emptyQueueCount++;
            
               idleTechTotal += Integer.parseInt(buffersplit[2]);
               noNightTechTotal += Integer.parseInt(buffersplit[3]);
            
            } // end of for loop
            out.write("###\n");
         	
         	//And print our report information
            reportsplit = reportInfo.split("#");
         
            long totalWait = Long.parseLong(reportsplit[4]) +
               Long.parseLong(reportsplit[5]) +
               Long.parseLong(reportsplit[6]) +
               Long.parseLong(reportsplit[7]);
         	
            out.write("\n\n");
            out.write("******************** Statistics Report  ******************************\n");
            out.write("Average Wait Time            : " + GetDateDiffString(Long.toString((long) totalWait / i)) + " hours\n");
            out.write("Average Queue Length         : " + Double.toString(queueLengthTotal / i) + "\n");
            out.write("% of Time Queue Empty        : " + Double.toString(100 * emptyQueueCount / i) + "\n");
            out.write("Idle Tech Blocks             : " + Integer.toString(idleTechTotal) + "\n");
            out.write("Blocks without Night Tech    : " + Integer.toString(noNightTechTotal) + "\n");
         
            out.write("Average Wait Time Priority 1 : " + GetDateDiffString(reportsplit[4]) + "\n");
            out.write("Average Wait Time Priority 2 : " + GetDateDiffString(reportsplit[5]) + "\n");
            out.write("Average Wait Time Priority 3 : " + GetDateDiffString(reportsplit[6]) + "\n");
            out.write("Average Wait Time Priority 4 : " + GetDateDiffString(reportsplit[7]) + "\n");
         	
            out.close();
         
         }  //end of else
      	
      }  //end of try
      
      catch (IOException e) {System.out.println("Printing Statistics.txt IO Exception:");}
   }

   private static String GetDateDiffString(String timestamp)
   {
      int days, hours, minutes;
      days = (int) Long.parseLong(timestamp) / (1000 * 60 * 60 * 24);
      hours = (int) Long.parseLong(timestamp) % (1000 * 60 * 60 * 24) / (1000 * 60 * 60);
      minutes = (int) Long.parseLong(timestamp) % (1000 * 60 * 60) / (1000 * 60);
      String s = " " + days + " days, " + hours + " hours, and " + minutes + " minutes ";
      return s;
   }
}
