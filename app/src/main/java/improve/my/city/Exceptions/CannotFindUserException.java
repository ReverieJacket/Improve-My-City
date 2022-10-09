package improve.my.city.Exceptions;

public class CannotFindUserException extends Exception {

    public CannotFindUserException() {
        super("Usuário não encontrado!");
    }
    
}
