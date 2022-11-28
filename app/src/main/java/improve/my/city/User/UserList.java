package improve.my.city.User;

public interface UserList {
    public void addUser(User user);
    public User getUser (String id);
    public boolean searchUser(String id);
}
