package improve.my.city.User;

import improve.my.city.Enums.*;

public class Employee extends User {
    private Institutions institution;
    private String employeeId;

    
    public Employee( String employeeId, String name, int password) {
        this.name = name;
        this.password = password;
        this.employeeId = employeeId;
    }


    public void setInstitution(Institutions institution) {
        this.institution = institution;
    }

    public String getEmployeeId() {
        return employeeId;
    }


    public Institutions getInstitution() {
        return institution;
    }

    

    
}
