package model;

import Utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model, UnnamedPropertyChangeSubject
{
  private String username;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager(String username)
  {
    this.username = username;
  }

  @Override public void sendMessage()
  {
    //Well as you can see the mediator is still missing so I have no clue on how to get this to work
  }

  @Override public String getMessageFromServer()
  {
    Message message = new Message("Bob", "test message");
    return message.getFullMessage();
  }

  @Override public void setUsername(String username)
  {
    this.username = username;
  }

  @Override public UserList getUserList(UserList userList)
  {
    return new UserList();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
}
