package improve.my.city.Exceptions;

public class CannotCreateAccountException extends Exception {

    public CannotCreateAccountException() {
        super("Não foi possível criar sua conta! Já existe uma conta utilizando a identificação informada.\n");
    }
    
    
}
