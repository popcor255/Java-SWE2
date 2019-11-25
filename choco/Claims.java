import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;

/** Claims represents a collection of Claim objects.  Claim objects can be added
 *  to the collection, but never deleted or updated. The collection can be asked 
 *  for all the claims submitted by a given provider and for all the claims for
 *  services rendered to a given member.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */ 

public class Claims
{
	/**
	 * Creates a new empty Claims object
	 */
	public Claims() 
	{
		claimList = new ArrayList<Claim>();
	}//default constructor
		
	/** Reads all the claims in the FILE_NAME text file into the collection.
	 *  @throws NumberFormatException if the provider or member number is 
	 *             not a valid integer
	 *  @throws IllegalArgumentException if any of the values are
	 *             invalid.
	 *  @throws IndexOutOfBoundsException if there are 
	 *             not enough values in the string
	 *  @throws ParseException if the submission date and time or the 
	 *             service date is not in the correct format
	 */
	public void open() throws ParseException
	{
		Scanner inFile = null;
		try
		{
			FileReader reader = new FileReader(FILE_NAME);
			inFile = new Scanner(reader);
			while(inFile.hasNextLine())
			{
				String line = inFile.nextLine();
				Claim aClaim = new Claim(line);
				add(aClaim);
			}
		}
		catch (FileNotFoundException ex)    
		{
			//No claims saved.  Not an error.  Do nothing.
		}
		finally
		{
			if (inFile != null) inFile.close();
		}     
	}//open
	
	/** Writes all the claims in the collection to the FILE_NAME text file.
	 *  @throws FileNotFoundException if the file cannot be created.
	 */   
	public void close() throws FileNotFoundException 
	{
		PrintWriter outFile = new PrintWriter(FILE_NAME);
		for (Claim aClaim : claimList)
			outFile.println(aClaim.toString());
		outFile.close();
	}//close
		
	/** Adds the given claim to the collection.
	 *  @param aClaim the claim to be added
	 */
	public void add(Claim aClaim)
	{
		claimList.add(aClaim);
	}//add
	
	/** Finds all the claims submitted by a given provider.
	 *  @param providerNumber the provider's number
	 *  @return all the claims submitted by the given provider
	 */
	public ArrayList<Claim> findByProvider(long providerNumber)
	{
		ArrayList<Claim> providerClaims = new ArrayList<Claim>();
		for(Claim aClaim : claimList )
			if (aClaim.getProviderNumber() == providerNumber)
				providerClaims.add(aClaim);
		
		return providerClaims;
	}//findByProvider
	
	/** Finds all the claims for services rendered to a given member.
	 *  @param memberNumber the member's number
	 *  @return all the claims for services rendered to the given member
	 */
	public ArrayList<Claim> findByMember(long memberNumber)
	{
		ArrayList<Claim> memberClaims = new ArrayList<Claim>();
		for(Claim aClaim : claimList )
			if (aClaim.getMemberNumber() == memberNumber)
				memberClaims.add(aClaim);
				
		//Sort by service date (Use a bubble sort for clarity)
		for (int i = memberClaims.size() - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (memberClaims.get(j).getServiceDate()
							.after(memberClaims.get(i).getServiceDate()))
				{
					Claim temp = memberClaims.get(i);
					memberClaims.set(i, memberClaims.get(j));
					memberClaims.set(j, temp);
				}     
				
		return memberClaims;
	}//findByMember
	 
	//class variable 
	private static final String FILE_NAME = "Claims.txt";
	//instance variable  
	private ArrayList<Claim> claimList;
	
}//class Claims
