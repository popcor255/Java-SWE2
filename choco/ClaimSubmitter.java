import java.util.Date;
import java.text.ParseException;
import java.io.FileNotFoundException;

/** The ClaimSubmitter control class provides the behaviour for the 
 *  Submit Claim use case, after the Verify Member use case has completed.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */
public class ClaimSubmitter
{
	/** Creates a new Claim Submitter object
	 *  @param theProvider the provider submitting the claim
	 *  @param theMember the member to whom the service was provided
	 */
	public ClaimSubmitter(Provider theProvider, Member theMember)
	{
		try
		{
			ui = new UserInterface();
			
			services = new Services();
			claims = new Claims();
			services.open();
			claims.open();
						
			//get the service date
			Date serviceDate = ui.promptForDate
						("Service Date (" + UserInterface.DATE_FORMAT + "): ");
			
			//get the correct service
			Service theService = null;
			boolean correctCode = false;
			do
			{
				//get the service code
				String serviceCode = ui.promptForString("Service Code: ");
				theService = services.find(serviceCode);
				if (theService == null)
					ui.errorMessage("Invalid code.  Please re-enter.");
				else
				{
					//confirm the service
					String answer = ui.promptForString("Service: " 
								 + theService.getName()
								 + "  \nIs this correct? (Y)es or (N)o: ");
					if (answer != null && answer.length() >= 1 &&			
						Character.toUpperCase(answer.charAt(0)) == 'Y')
							correctCode = true;
				}
			} while (!correctCode);
			
			
			//Create new claim.  The constructor initializes
			//the submission date and time with the system time.
			Claim aClaim = new Claim(theService.getCode(),
						 theProvider.getNumber(), theMember.getNumber(),
						 serviceDate);
			claims.add(aClaim);
			//Display success confirmation and service fee
			ui.message("Your claim has been submitted successfully.");
			ui.message("Service fee due to you: " 
				+ ui.formatAsCurrency(theService.getFee()));
			
			services.close();
			claims.close();
		}
		catch (ParseException ex)
		{
			//File format is incorrect
			ui.errorMessage(ex.getMessage());
		}
		catch (IllegalArgumentException ex)
		{
			//Thrown by the constructor for the claim object.
			//This should only happen if the comments entered are too long.
			ui.errorMessage(ex.getMessage());
		}
		catch (FileNotFoundException ex)
		{
			//occurs if the file cannot be created
			ui.errorMessage(ex.getMessage());
		}		
		
	}//default constructor
	
	//*******************instance variables
	private Services services;
	private Claims claims;
	
	private UserInterface ui;
}//class ClaimSubmitter
