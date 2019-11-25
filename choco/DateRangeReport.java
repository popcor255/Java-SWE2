import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.FileNotFoundException;

/** This class models a report with a date range.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */
public abstract class DateRangeReport extends Report
{
	
	/** Creates a new report with a date range.  The date range is set to 
	 *  the week that includes the end date given.  A Friday is expected for the
	 *  end date.  If a Saturday is given as the end date, the end date is 
	 *  adjusted to the Friday preceding the Saturday.
	 *  If a Sunday through to Thursday is given as the end date, the end date
	 *  is adjusted to the following Friday.
	 *  @param anEndDate the date to be included in the date range
	 */
	public DateRangeReport(Date anEndDate)
	{
		super ();
		
		//Ensure that the end date is a Friday.
		setDates(anEndDate);
		
		//initialize the date and date time formatters
		dateTimeFormatter = new SimpleDateFormat();
		dateTimeFormatter.applyPattern(DATE_TIME_FORMAT);
		dateFormatter = new SimpleDateFormat();
		dateFormatter.applyPattern(DATE_FORMAT);
		
	}//DateRangeReport constructor
	
	/** Returns the end date (Friday) of the week of this report
	 */
	public Date getEndDate()
	{
		return endDate;
	}//getEndDate
	
	/** Returns the upper bound (exclusive) of the date range for this report
	 */
	public Date getEndDateRange()
	{
		return endDateRange;
	}//getEndDateRange
	
	/** Returns the lower bound (exclusive) of the date range for this report
	 */
	public Date getStartDateRange()
	{
		return startDateRange;
	}//getStartDateRange
	
	// Sets the dates for the report.  The date range is midnight Friday 
	// of the previous week to 1 second before midnight of the week
	// within which the given date falls, inclusive.
	private void setDates(Date givenDate)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(givenDate);
		
		//adjust time to 00:00:00 -- dates entered by manager already have 
		//time as 00:00:00, but when testing the accounting procedure
		//the time is the current system time
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		// Set end date to the Friday of the week in which it falls
		//i.e a Saturday is set to the preceding Friday
		//Sunday to Thursday is set to the following Friday
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int daysForward = Calendar.FRIDAY - dayOfWeek;
		calendar.add(Calendar.DATE, daysForward);
		endDate = calendar.getTime();
		
		//Use Saturday 00:00:00 am as end of range (exclusive)
	   	calendar.add(Calendar.DATE, 1);
	   	endDateRange = calendar.getTime(); 
	   	  		
   	//Use the previous Friday 12:59:59 pm as 
   	//the beginning of range (exclusive)
   	calendar.add(Calendar.DATE, -7);
   	calendar.add(Calendar.SECOND, -1);
   	startDateRange = calendar.getTime();
	}//setDates
	
	/** Simulates sending a report by email.  The report is saved to a
	 *  text file named by the "email address" given plus the report date
	 *  plus the extension .txt.
	 *  @param emailAddress the email address to which the report must be sent
	 *  @throws FileNotFoundException if the file cannot be created
	 */
	public void sendByEmail(String emailAddress) throws FileNotFoundException
	{
		String endDateString = dateFormatter.format(endDate);
		String fileName = emailAddress + " " + endDateString;
		super.sendByEmail(fileName);
	}//sendByEmail
	
	//**********************class variables 	
	/** Format used for dates
	 */
	public static final String DATE_FORMAT = "MM-dd-yyyy";
	/** Format used for date and time
	 */
	public static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";
	/** Used to format dates
	 */
	protected static SimpleDateFormat dateFormatter;
	/** Used to format dates and times
	 */ 
	protected static SimpleDateFormat dateTimeFormatter;
	
	//**********************instance variables
	private Date endDate;
	private Date endDateRange;
	private Date startDateRange;

}//class DateRangeReport
