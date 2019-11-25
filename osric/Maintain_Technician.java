/**
 * Summary description for Maintain_Technician.
 */
public class Maintain_Technician
{
   public Maintain_Technician()
   {
   }


	// Function: Add Technician Control Class: add new technician and output technician number
	// Input: None
	// Output: None
   void addTechnician()
   {
      Technician tech = new Technician();
      tech.getAddInformation();
   
      System.out.println("Technician " + Integer.toString(tech.getTechnicianNumber()) + " added.");
   }


	// Function: Update Technician Contorl class: find technician, get update information,
	//	and confirm technician successfully updated
	// Input: None
	// Output: None
   void updateTechnician()
   {
      Technician tech = new Technician();
   			
      if (!tech.findTechnician())
         return;
   
      tech.getUpdateInformation();
      System.out.println("Technician updated.\n");
   }


	// Function: Delete Technician Control class: find technician & delete technician
	// Input: None
	// Output: None
   void deleteTechnician()
   {
      Technician tech = new Technician();
   			
      if (!tech.findTechnician())
         return;
   
      tech.deleteTechnician();
   }
}
