/** Entity class that models a ChocAn member.  It is a subclass of Person.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */

public class Member extends Person
{
	//****************constructors
  	
  	/** Creates a default member, allocating a unique number, setting 
  	 *  the status to Active and setting all other instance variables,
  	 *  except the name, to empty strings.
	 */	
	public Member()
	{
		super();
		status = 'A';  //default status is Active
	}//Member default constructor
	
	/** Creates a member from a string representation of the member.
	 *  @param data the string representation
    *  @throws NumberFormatException if the member number is not a valid 
    *          integer
    *  @throws IllegalArgumentException if any of the values are invalid.
    *  @throws ArrayIndexOutOfBoundsException if there are not enough values 
    *          in the string
	 */
	public Member(String data)
	{
		super(data);		
	}//Member constructor using string data
	
	//****************accessor method
	
	/** Returns the status of the member as a single character
	 *  @return the member's status
	 */
	public char getStatus()
	{
		return status;
	}//getStatus
	
	//****************mutator method
	
	/** Changes the status of the member
	 *  @param newStatus the new status of the member
	 *  @throws IllegalArgumentException if newStatus is not one of the 
	 *             allowed status characters
	 */
	public void setStatus(char newStatus)
	{
		if (MEMBER_STATUS_VALUES.indexOf(newStatus) < 0)
			throw new IllegalArgumentException(MEMBER_STATUS_HELP);
		status = newStatus;
	}//setStatus

   //****************utility methods
	
   /** Returns a string representation of the member consisting of the values,
	 *  converted to strings, of all the instance variables separated by
	 *  the character SEPARATOR.
	 *  @return the string representation of the member.
	 */
	public String toString()
	{
		return super.toString() + SEPARATOR + status;
	}//toString
	
    /** Changes all the instance variables to the values given by the string 
     *  representation of the member.
     *  @param data the string representation of the member
     *  @throws NumberFormatException if the member number is 
     *             not a valid integer
     *  @throws IllegalArgumentException if any of the values are invalid.
     *  @throws IllegalStateException if there are not enough values 
     *             in the string
     */
	public void fromString(String data)
	{
		super.fromString(data);
		setStatus(data.charAt(data.length()-1));
	}//fromString
	
	/** Returns a string representation of the member in a format that is
     *  suitable for text display.
     *  @return a formatted string representation of the member
     */
	public String toFormattedString()
	{
		return super.toFormattedString() + "\nStatus:         " + status;
	}//toFormattedString

	
	//*****************instance variable
	private char status;
	
	//****************class variables
	private static final String MEMBER_STATUS_VALUES = "AS";
	
	/** Message giving the characters that are valid for the member status
	 */
	public static final String MEMBER_STATUS_HELP = "Member status must be "
				+ "one of the following characters: A(ctive) or S(uspended)";

}//class Member