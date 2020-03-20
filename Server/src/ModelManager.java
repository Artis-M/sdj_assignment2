import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
    private Message message;
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
    public Message getMessage()
    {
        return message;
    }
    public synchronized void addLog(String log)
    {
        String logValue = message.getLogSize() + ": " + log;
        message.addLog(logValue);
        property.firePropertyChange("add", null, logValue);
    }

    @Override
    public UserList getUsers()
    {
       return users;
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
