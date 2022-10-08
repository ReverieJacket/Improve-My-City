package improve.my.city.Central;

import java.util.ArrayList;

import improve.my.city.User.Employee;
import improve.my.city.User.UserList;

public class EmployeeList implements UserList{
    ArrayList <Employee> employees;

    public EmployeeList(){
        this.employees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee); 
    }

    public Employee getUser(String id){
        int i;
        for(i = 0; i < this.employees.size(); i++){
            if(this.employees.get(i).getEmployeeId().equals(id)){
                return this.employees.get(i);
            }
        }
        return null;
        }

    public boolean searchUser(String id){
        int i;
        for(i = 0; i < this.employees.size(); i++){
            if(this.employees.get(i).getEmployeeId().equals(id)){
                return true;
            }
        }
        return false;
    }

}
