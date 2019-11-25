public class things {
    void getAddInformation() {
        Technician_File tFile = new Technician_File();

        System.out.print("Please enter technician name: ");
        this.techName = Get_Input.readString(100);

        System.out.print("Please enter technician's shift (1=day, 2=night1, 3=night2): ");
        shift = Get_Input.readSelection(3);

        technicianNumber = tFile.getNewNumber();

        tFile.addTechnician(technicianNumber + "#" + technicianName + "#" + (shift - 1));

    }
}