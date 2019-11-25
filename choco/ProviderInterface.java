import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/** This class simulates the provider terminal interface, i.e. the Provider
 *  Subsystem.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */

public class ProviderInterface
{
	
	/**
	 * Creates a new ProviderInterface.  
	 */
	public ProviderInterface()
	{
		ui = new UserInterface();
		
		//Start use case Manage Session
		logon();
		
		String menuText = "\t1. Verify Member/Submit Claim\n" 
							 + "\t2. Request Provider Directory\n"
							 + "\t3. Quit\n"; 
						  
		int choice;
		do 
		{      
			//display menu and read choice
			ui.message("\n\t\t\tProvider Subsystem\n");
			choice = ui.menu(menuText); 
			switch(choice)
			{
				//Use case Verify Member followed optionally by Submit claim
				case 1: verifyMember(); break;
				//Use case Receive Provider Directory
				case 2: receiveDirectory(); break;
				case 3: break;
				default: ui.errorMessage("Invalid choice.  Please re-enter.");
			}
		}while (choice != 3);
		
	}// default Constructor
	
	//Use Case Maintain Session start here and ends when the user chooses Quit.
	//Prompts the provider for his provider number and verifies the number.
	//The provider cannot continue until he has entered a valid provider number.
	private void logon()
	{
		try
		{
			Providers providers = new Providers();
			providers.open();
	
			boolean validNumber = false;
			do
			{
				//prompt user for provider number
				long number = ui.promptForLong("Provider Number: ");
				//search for provider in collection
				theProvider = providers.find(number);
				if (theProvider == null)
				  ui.errorMessage("Invalid provider number.  Please re-enter.");
				else validNumber = true;
			}while (!validNumber);
			
			providers.close();
		}
		catch (FileNotFoundException ex)
		{
			//occurs if the file cannot be created
			ui.errorMessage(ex.getMessage());
		}

	}//logon
	
	//Verify Member and Submit Claim use cases
	//Allows the provider to continue to submit a claim after verifying the 
	//member only if the member's status is Active.
	private void verifyMember()
	{
		try
		{
			Members members = new Members();
			members.open();
			boolean canContinue = false;
			
			//Prompt the provider for a member number.
			long number = ui.promptForLong("Member Number: ");
			theMember = members.find(number);
			
			//Display the status of the member.
			if (theMember == null)
				ui.message("Invalid member number.");
			else if (theMember.getStatus() == 'A')
			{
				ui.message("Active Member\n");
				canContinue = true;
			}
			else if (theMember.getStatus() == 'S')
				ui.message("Suspended\n");
			else ui.errorMessage("Invalid status: " + theMember.getStatus());
			
			if (canContinue)  //Can continue only if member is active
			{
				//Submit Claim Use Case extension to the Verify Member Use Case
				String answer = ui.promptForString
							("Continue to submit claim? (Y)es or (N)o: ");
				if (answer != null && answer.length() >= 1)        
					if (Character.toUpperCase(answer.charAt(0)) == 'Y')
					{
						//Create a new ClaimSubmitter control object
						new ClaimSubmitter(theProvider, theMember);           
					}
			}
			
			members.close();
		}
		catch (FileNotFoundException ex)
		{
			//occurs if the file cannot be created
			ui.errorMessage(ex.getMessage());
		}
	}//verifyMember
	
	// Generates a provider directory and sends it to provider by email
	private void receiveDirectory()
	{
		try
		{
			//generate the report
			ServiceReportGenerator generator = new ServiceReportGenerator();
			ProviderDirectory theReport = generator.getReport();
			
			//Send directory by email to the provider       
			theReport.sendByEmail(theProvider.getName() + " Provider Directory");
			
			//Display success confirmation on provider terminal
			ui.message("Your directory has been sent to you by email");
		}
		catch (FileNotFoundException ex)
		{
			//occurs if the file cannot be created
			ui.errorMessage(ex.getMessage());
		}

	}//receiveDirectory
		
	//********************instance variables
	private Provider theProvider;
	private Member theMember;
	private UserInterface ui;

	//***********************************************************************
	/**
	 * Starts the provider subsystem independently of the other subsystems
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		try
		{
			new ProviderInterface();
		}
		catch (NoSuchElementException ex)
		{
			UserInterface ui = new UserInterface();
			ui.message("\nEnd of test run.\n");
		}
	}//main
	
}//class ProviderInterface
