package improve.my.city.Exceptions;

public class OutOfTheOptionsException extends Exception {

    public OutOfTheOptionsException() {
        super("\nOpção Inválida! Por favor, insira um número dentre os listados\n");
    }
    
}
