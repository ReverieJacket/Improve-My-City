package improve.my.city.Central;

import java.util.Scanner;

import improve.my.city.Exceptions.CannotCreateAccountException;

public interface AccountCreationStrategy {
    public void create(Object e, Scanner input) throws CannotCreateAccountException;
}
