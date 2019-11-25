import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;
import java.io.FileNotFoundException;

/** Control class to co-ordinate the use case Produce Member Report.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */
public class MemberReportGenerator 
{
/** Creates a new member report generator which creates a new member report.
 *  @param member the member about whom the report is generated
 *  @param endDate a date within the week for which the report is to be
 *         generated
 *  @throws FileNotFoundException if the file cannot be created.
 */
	public MemberReportGenerator(Member member, Date endDate) 
									throws FileNotFoundException
	{
		Claims claims = null;
		Providers providers = null;
		Services services = null;
		
		//Create a new member report
		report = new MemberReport(member, endDate);

		try
		{						
			//create and open the claims, providers and services collections
			claims = new Claims();
			claims.open();
			providers = new Providers();
			providers.open();
			services = new Services();
			services.open();
			   					
			//Get all claims submitted for services to this member, ordered by
			//service date
			ArrayList<Claim> memberClaims = 
				claims.findByMember(member.getNumber());
				
			//for each claim
			for (Claim nextClaim : memberClaims)
			{
				//test whether within date range
				if (nextClaim.getSubmissionDate()
							.after(report.getStartDateRange()) 
					&& nextClaim.getSubmissionDate()
							.before(report.getEndDateRange()))
				{
					//get provider
					String providerName;
					Provider provider = providers
						.find(nextClaim.getProviderNumber());
					if (provider == null) 
						providerName = "Invalid Number";
					else providerName = provider.getName();
					
					//get service
					String serviceName;
					Service service = services.find(nextClaim.getServiceCode());
					if ( service == null)
						serviceName = "Invalid Code";
					else serviceName = service.getName();
					
					//add service details to report
					report.addDetail(nextClaim.getServiceDate(), serviceName,
							providerName);
					
				}//if date in specified week
			}//for
			
		}//try
		catch (ParseException ex)
		{
			report.addErrorMessage(ex.getMessage());
		}
		finally
		{
			if (claims != null) claims.close();
			if (providers != null) providers.close();
			if (services != null) services.close();
		}		
				
	}//constructor
	
	/** Returns the report
	 *  @return the report
	 */
	public MemberReport getReport()
	{
		return report;
	}//getReport
	
	//********************instance variable
	private MemberReport report;
			
}//class MemberReportGenerator
