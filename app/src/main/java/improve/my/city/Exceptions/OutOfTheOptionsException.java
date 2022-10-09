package improve.my.city.Exceptions;

public class OutOfTheOptionsException extends Exception {

    public OutOfTheOptionsException() {
        super("Opção Inválida! Por favor, insira um número dentre os listados");
    }
    
}
