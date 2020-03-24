package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
    private PropertyChangeSupport property;
    private UserList users;

    public ModelManager()
    {
        this.users = new UserList();
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public void addUser(String name)
    {
        users.addUser(name);
    }

    @Override
    public void getMessage(Message message)
    {
        property.firePropertyChange("message", null, message);
    }
    @Override
    public UserList getUsers()
    {
       return users;
    }

    @Override
    public void removeUser(String username) {
users.removeUser(username);
    }

    @Override
    public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }
}
