package improve.my.city.Central;

import improve.my.city.Exceptions.CannotFindUserException;

public class LoginCitizenStrategy implements ConnectionType{

    @Override
    public boolean login(Object citizenList, String cpf, int password) throws CannotFindUserException { 
        CitizenList citizens = (CitizenList) citizenList;
        if(citizens.searchUser(cpf)){
            if(citizens.getUser(cpf).equals(null)){
                throw new CannotFindUserException();
            }else{
                if(citizens.getUser(cpf).getPassword() == password){
                    return true;
                }else{
                    return false;
                } 
            }
        }else{
            return false;
        }

    }

}
