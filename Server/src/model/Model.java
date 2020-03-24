package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject

{
    void addUser(String name);

    void getMessage(Message message);

    UserList getUsers();

    void removeUser(String username);
}
