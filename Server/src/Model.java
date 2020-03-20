import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject

{
    void addUser(String name);

    Message getMessage();

    UserList getUsers();

    void addLog(String log);
}
