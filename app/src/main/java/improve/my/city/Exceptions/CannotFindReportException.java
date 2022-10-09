package improve.my.city.Exceptions;

public class CannotFindReportException extends Exception {

    public CannotFindReportException() {
        super("Nenhum relato encontrado!\n");
    }
   
}
