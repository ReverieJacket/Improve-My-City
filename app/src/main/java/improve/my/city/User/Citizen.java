package improve.my.city.User;
import java.util.ArrayList;

import improve.my.city.Enums.*;

public class Citizen extends User {
    private String cpf;
    private Districts residence;
    private String address;
    private ArrayList<String> created;
    private ArrayList<String> confirmed;
    
    
    public Citizen(String name, int password, String cpf, String address) {
        this.name = name;
        this.password = password;
        this.cpf = cpf;
        this.address = address;
        this.created = new ArrayList<String>();
        this.confirmed = new ArrayList<String>();
    }


    public Districts getResidence(){
        return this.residence;
    }
    
    public void setResidence(Districts residence) {
        this.residence = residence;
    }
    public String getName(){
        return this.name;
        
    }

    public int getCreatedSize() {
        return this.created.size();
    }

    public int getConfirmedSize() {
        return this.confirmed.size();
    }


    public String getCreated(int position) {
        return created.get(position);
        
    }
    public String getConfirmed(int position) {
        return confirmed.get(position);
    }

    public void setCriados(String id) {
        this.created.add(id);
    }
    public void setConfirmados(String id) {
        this.confirmed.add(id);
    }
    public String getCpf() {
        return cpf;
    }
    @Override
    public String toString(){
        return "\nNome: " +this.name + "\n"+"CPF: " + this.cpf + "\n"+ "Bairro: "+ residence + "\n" + "Endereço: "+ address +"\n";
    }
    

    


   

    
}
