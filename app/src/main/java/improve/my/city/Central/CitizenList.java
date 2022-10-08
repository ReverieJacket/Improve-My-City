package improve.my.city.Central;

import java.util.ArrayList;

import improve.my.city.User.Citizen;
import improve.my.city.User.UserList;

public class CitizenList  implements UserList{
    private ArrayList <Citizen> citizens;

    public CitizenList() {
        this.citizens = new ArrayList<Citizen>();
    }
    public void addUser(Citizen user){
        this.citizens.add(user);
    }

    public Citizen getUser(String id){
        int i;
        for(i = 0; i < this.citizens.size(); i++){
            if(this.citizens.get(i).getCpf().equals(id)){
                return this.citizens.get(i);
            }
        }
        return null;
        }
    

    public boolean searchUser(String id){
        int i;
        for(i = 0; i < this.citizens.size(); i++){
            if(this.citizens.get(i).getCpf().equals(id)){
                return true;
            }
        }
        return false;
    }

}

  
