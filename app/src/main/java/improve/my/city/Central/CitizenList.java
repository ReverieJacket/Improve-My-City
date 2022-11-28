package improve.my.city.Central;

import java.util.ArrayList;

import improve.my.city.User.Citizen;
import improve.my.city.User.User;
import improve.my.city.User.UserList;

public class CitizenList  implements UserList{
    private ArrayList <Citizen> citizens;
    private static CitizenList citizenListInstance;


    // Implementação de singleton
    private CitizenList() {
    }
    public static CitizenList getInstance(){
        if(citizenListInstance == null){
            citizenListInstance = new CitizenList();
            citizenListInstance.citizens = new ArrayList<Citizen>();
        }
        return citizenListInstance; 
    }
    
    @Override
    public void addUser(User citizen) {
        citizens.add((Citizen) citizen);
        
    }

    public Citizen getUser(String id){
        int i;
        for(i = 0; i < citizens.size(); i++){
            if(citizens.get(i).getCpf().equals(id)){
                return citizens.get(i);
            }
        }
        return null;
        }
    

    public boolean searchUser(String id){
        int i;
        for(i = 0; i < citizens.size(); i++){
            if(citizens.get(i).getCpf().equals(id)){
                return true;
            }
        }
        return false;
    }
    

}

  
