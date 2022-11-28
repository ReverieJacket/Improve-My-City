package improve.my.city.Central;

import improve.my.city.Exceptions.CannotFindUserException;

public interface ConnectionType {
    public boolean login (Object list, String id, int password) throws CannotFindUserException;
}
