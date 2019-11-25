
/** Boundary class modeling a provider directory -- a list of services in
 *  alphabetical order according to the service name.
 *  @author Jean Naude
 *  @version 1.0 March 2009
 */
public class ProviderDirectory extends Report
{
	
	/**
	 * Creates a new ProviderDirectory
	 */
	public ProviderDirectory()
	{
		String title = "Provider Directory";
		
		formatter.format("%31s %n%n", title);
		formatter.format("%-25s %-6s   %8s %n","Service Name", "Code", "Fee");
		formatter.format("%-25s %-6s   %8s %n","------------", "----", "---");
		
	}//default constructor
	
	/** Adds a line of detail about a service
	 *  @param name the name of the service
	 *  @param code the code of the service
	 *  @param fee the fee payable for the service
	 */
	public void addDetail(String name, String code, double fee)
	{
		formatter.format("%-25s %-6s   %8s %n", name, code, 
							currencyFormatter.format(fee));
	}//addDetail
	
		
}//class ProviderDirectory
