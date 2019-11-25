import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;
import java.io.FileNotFoundException;

/** Control class to co-ordinate the use case Produce Provider Report.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */
 
public class ProviderReportGenerator 
{
	
/** Creates a new provider report generator which creates a new provider report.
 *  @param provider the provider about whom the report is generated
 *  @param endDate a date within the week for which the report is to be
 *         generated
 *  @throws FileNotFoundException if the file cannot be created.
 */
	public ProviderReportGenerator(Provider provider, Date endDate) 
	        throws FileNotFoundException
	{
		Claims claims = null;
		Members members = null;
		Services services = null;
		
		//Create a new provider report
		report = new ProviderReport(provider, endDate);

		try
		{						
			//create and open the collections of claims, members and services
			claims = new Claims();
			claims.open();
			members = new Members();
			members.open();
			services = new Services();
			services.open();
			   		
			int noConsultations = 0;  //use to count number of consultations
			double totalFee = 0;      //use to accumulate fee
		
			//get all claims submitted by provider
			ArrayList<Claim> providerClaims = 
				claims.findByProvider(provider.getNumber());
				
			//for each claim
			for (Claim nextClaim : providerClaims)
			{
				//test whether within date range
				if (nextClaim.getSubmissionDate().after(report.getStartDateRange()) 
				  && nextClaim.getSubmissionDate().before(report.getEndDateRange()))
				{
					//get the member to whom the service was provided
					String memberName;
					Member member = members.find(nextClaim.getMemberNumber());
					if (member == null) 
						memberName = "Invalid Number";
					else memberName = member.getName();
					
					//get the fee for the service
					double serviceFee;
					Service service = services.find(nextClaim.getServiceCode());
					if ( service == null)
						serviceFee = 0;   //indicates invalid code
					else serviceFee = service.getFee();
					
					//add claim details to report
					report.addDetail(nextClaim.getSubmissionDate(),
							nextClaim.getServiceDate(), nextClaim.getMemberNumber(),
							memberName, nextClaim.getServiceCode(), serviceFee);
					
					//increment number of consultations
					noConsultations++;
					
					//accumulate fee
					totalFee += serviceFee;
				}//if date in specified week
			}//for
			
			//add summary details to report
			report.addSummary(noConsultations, totalFee);
		}//try
		catch (ParseException ex)
		{
			report.addErrorMessage(ex.getMessage());
		}
		finally
		{
			if (claims != null) claims.close();
			if (members != null) members.close();
			if (services != null) services.close();
		}		
				
	}//constructor

	/** Returns the report
	 *  @return the report
	 */
	public ProviderReport getReport()
	{
		return report;
	}//getReport
	
	//********************instance variable
	private ProviderReport report;
			
}//class ProviderReportGenerator
