package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private ArrayList<Message> messages;
  private ArrayList<String> list;
  private PropertyChangeSupport property;

  public ModelManager()
  {
    this.messages = new ArrayList<Message>();
    this.list = new ArrayList<>();
    this.property = new PropertyChangeSupport(this);
  }

  @Override public ArrayList<String> getList()
  {
    return list;
  }

  @Override public void addUsername(String username)
  {
    list.add(username);
  }

  @Override public void addMessage(Message message)
  {
    messages.add(message);
    property.firePropertyChange("Message", null, message);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

}