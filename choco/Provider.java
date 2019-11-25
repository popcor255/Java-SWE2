/**
 * Entity class that models a ChocAn provider. It is a subclass of Person.
 * 
 * @author Jean Naude
 * @version 1.0 March 2009
 */
public class Provider extends Person {
	// ****************constructors

	/**
	 * Creates a default provider, allocating a unique number and setting all
	 * instance variables, except the name, to empty strings.
	 */
	public Provider() {
		super();
		// no default value for type
	}// Provider default constructor

	/**
	 * Creates a provider from a string representation of the provider.
	 * 
	 * @param data the string representation
	 * @throws NumberFormatException          if the provider number is not a valid
	 *                                        integer
	 * @throws IllegalArgumentException       if any of the values are invalid.
	 * @throws ArrayIndexOutOfBoundsException if there are not enough values in the
	 *                                        string
	 */
	public Provider(String data) {
		super(data);
	}// Provider constructor using string data

	// ****************accessor method

	/**
	 * Returns the type of the provider as a single character
	 * 
	 * @return the provider's type
	 */
	public char getType() {
		return type;
	}// getType

	// ****************mutator method

	/**
	 * Changes the type of the provider
	 * 
	 * @param newType the new type of the provider
	 * @throws IllegalArgumentException if newType is not one of the allowed type
	 *                                  characters
	 */
	public void setType(char newType) {
		if (PROVIDER_TYPES.indexOf(newType) < 0)
			throw new IllegalArgumentException(PROVIDER_TYPE_HELP);
		type = newType;
	}// setType

	// ****************utility methods

	/**
	 * Returns a string representation of the provider consisting of the values,
	 * converted to strings, of all the instance variables separated by the
	 * character SEPARATOR.
	 * 
	 * @return the string representation of the provider.
	 */
	public String toString() {
		return super.toString() + SEPARATOR + type;
	}// toString

	/**
	 * Changes all the instance variables to the values given by the string
	 * representation of the provider.
	 * 
	 * @param data the string representation of the provider
	 * @throws NumberFormatException     if the provider number is not a valid
	 *                                   integer
	 * @throws IllegalArgumentException  if any of the values are invalid.
	 * @throws IndexOutOfBoundsException if there are not enough values in the
	 *                                   string
	 */
	public void fromString(String data) {
		super.fromString(data);
		setType(data.charAt(data.length() - 1));
	}// fromString

	/**
	 * Returns a string representation of the provider in a format that is suitable
	 * for text display.
	 * 
	 * @return a formatted string representation of the provider
	 */
	public String toFormattedString() {
		return super.toFormattedString() + "\nType:           " + type;
	}// toFormattedString

	// ****************instance variable
	private char type; // The only allowable types are Dietitician (D),
						// Internist (I) and Exercise Specialist (E)

	// ****************class variables
	// I changed this
	private static final String PROVIDER_TYPES = "321";
	/**
	 * Message giving the characters that are valid for the provider type
	 */
	public static final String PROVIDER_TYPE_HELP = "Provider type must be "
			+ "one of the following characters: D(ietitian), " + "I(nternist) or E(xercise Specialist)";

}// class Provider