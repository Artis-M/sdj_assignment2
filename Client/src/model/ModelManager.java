package model;

import Utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model, UnnamedPropertyChangeSubject
{
  private String username;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager(String username)
  {
    this.username = username;
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override public void sendMessage()
  {
    //Well as you can see the mediator is still missing so I have no clue on how to get this to work
  }

  @Override public String getMessageFromServer(Message message)
  {
    //placeholder!!!!!!!!!!!!!!!!!!!!!
    return message.getFullMessage();
    //placeholder!!!!!!!!!!!!!!!!!!!!!
  }

  @Override public void setUsername(String username)
  {
    this.username = username;
  }

  @Override public ArrayList<String> getUserList(UserList userList)
  {
    //placeholder!!!!!!!!!!!!!!!!!!!!!
    return userList.getUserList();
    //placeholder!!!!!!!!!!!!!!!!!!!!!
  }

  @Override
  public String getUsername() {
    return username;
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
