import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**  Entity class that models a service that can be provided to a ChocAn member 
 *   by a ChocAn provider
 *   @author Jean Naude
 *   @version 1.0 March 2009
 */
public class Service 
{
	//**********************constructors
	
	/** Creates a new default service with default values
	 */
	public Service() 
	{		
	}//default constructor
	
	
	/** Creates a new service with the given values
	 *  @param aCode the service code
	 *  @param aName the service name
	 *  @param aFee the service fee
	 *  @throws IllegalArgumentException if any of the values are invalid
	 */
	public Service(String aCode, String aName, double aFee)	
	{
		setCode(aCode);
		setName(aName);
		
		setFee(aFee);
	}//parameterized Service constructor
	
	/** Creates a service object from a string representation of the service.
	 *  @param data the string representation
	 *  @throws IllegalArgumentException if the code, name or fee are invalid.
	 *  @throws ParseException if the fee is an not a valid double
	 */
	public Service (String data) throws ParseException
	{
		fromString(data);
	}//Service constructor using string data
	

	//**********************accessor methods
	
	/** Returns the service's code
	 *  @return the service code
	 */
	public String getCode()
	{
		return code;
	}//getCode
	
	/** Returns the service's name
	 *  @return the service name
	 */
	public String getName()
	{
		return name;
	}//getName
	
	/** Returns the service's fee
	 *  @return the service fee
	 */
	public double getFee()
	{
		return fee;
	}//getCode
	
	//***********************mutator methods
	
	/** Changes the code of the service
	 *  @param aCode the new code
	 *  @throws IllegalArgumentException if aCode is null, an empty string,
	 *             longer than CODE_LENGTH characters or 
	 *             contains characters other than digits 
	 */
	public void setCode(String aCode)
	{
		if (aCode == null || aCode.length() == 0)
			throw new IllegalArgumentException("A service code is required");
		else if (aCode.length() > CODE_LENGTH)
			throw new IllegalArgumentException
				("The service code may not be more than "
					+ CODE_LENGTH + " digits");
		else
		{
			//check that each character is a digit
			for (int i = 0; i < aCode.length(); i++)
				if (! Character.isDigit(aCode.charAt(i)))
					throw new IllegalArgumentException
						("The service code may contain digits only");
		}
		code = aCode;
	}//setCode
	
	/** Changes the name of the service
	 *  @param aName the new name
	 *  @throws IllegalArgumentException if aName is null, an empty string 
	 *             or more than NAME_LENGTH characters
	 */
	public void setName (String aName)
	{
		if (aName == null || aName.length() == 0)
			throw new IllegalArgumentException("A service name is required");
		else if (aName.length() > NAME_LENGTH)
			throw new IllegalArgumentException
				("The service name may not be more than " 
					+ NAME_LENGTH + " characters");
		name = aName;
	}//setName
	
	/** Changes the fee of the service
	 *  @param aFee the new fee
	 *  @throws IllegalArgumentException if aFee is negative or
	 *             greater than MAX_FEE
	 */
	public void setFee(double aFee)
	{
		if (aFee < 0 || aFee >= MAX_FEE)
			throw new IllegalArgumentException
				("The fee must be between $0 and " + formatter.format(MAX_FEE));
		fee = aFee;
	}//setFee

   /** Returns a string representation of the service consisting of the values,
    *  converted to strings, of all the instance variables separated by
    *  the character SEPARATOR.
    *  @return the string representation of the service.
    */
	public String toString()
	{
		return code + SEPARATOR + name + SEPARATOR + formatter.format(fee);
	}//toString
	
   /** Changes all the instance variables to the values given by the string 
    *  representation of the service.
    *  @param data the string representation of the person
    *  @throws ParseException if the service fee is not a valid double
    *  @throws IllegalArgumentException if any of the values are
    *             invalid.
    *  @throws IllegalStateException if there are not enough values 
    *             in the string
    */
	public void fromString(String data) throws ParseException
	{
		String [] fields = data.split("" + SEPARATOR);
      setCode(fields[0]);
      setName(fields[1]);
      setFee(formatter.parse(fields[2]).doubleValue());
	}//fromString
	
   /** Returns a string representation of the service in a format that is
    *  suitable for text display.
    *  @return a formatted string representation of the service
    */
   public String toFormattedString()
   {
    	String serviceString = "Code:  " + code
           			       + "\nName:  " + name
    					   + "\nFee:   " + formatter.format(fee);
    	return serviceString;
   }//toFormattedString
	
	//******************instance variables
	private String code;
	private String name;
	private double fee;
	 
	//******************class variables
	/** Maximum number of digits for a service code
	 */
	public static final int CODE_LENGTH = 6;
	/** Maximum number of characters for a service name
	 */
	public static final int NAME_LENGTH = 20;
	/** Maximum fee for a service
	 */
	public static final double MAX_FEE = 1000;
	
	private static final char SEPARATOR = '#';
	private static NumberFormat formatter 
			= NumberFormat.getCurrencyInstance(Locale.US);
	
}//class Service
