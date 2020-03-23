package model;

import model.Message;
import model.UserList;
import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject

{
    void addUser(String name);

    void getMessage(Message message);

    UserList getUsers();

    void sendMessage();

    void removeUser(String username);
}
