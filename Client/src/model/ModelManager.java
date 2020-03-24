package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.ChatClient;
import mediator.ChatClientReceiver;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model, UnnamedPropertyChangeSubject
{
  private String username;
  private PropertyChangeSupport property;
private ChatClient client;


  public ModelManager(String username)
  {
    this.username = username;
    property = new PropertyChangeSupport(this);
    client = new ChatClient(this);
  }

  @Override public void sendMessage(String message)
  {
    //Well as you can see the mediator is still missing so I have no clue on how to get this to work
    Message message1 = new Message(username, message);
    System.out.println("Message sent to client");
    client.sendMessage(message1);
  }

  @Override public void getMessageFromServer(Message message)
  {
    //placeholder!!!!!!!!!!!!!!!!!!!!!
    property.firePropertyChange("NewMessageFromServer", null, message);
    //placeholder!!!!!!!!!!!!!!!!!!!!!
  }

  @Override public void setUsername(String username)
  {
    this.username = username;
  }

  @Override public void getUserList(UserList userList)
  {
    //placeholder!!!!!!!!!!!!!!!!!!!!!
    property.firePropertyChange("List", null, userList.getUserList());
    //placeholder!!!!!!!!!!!!!!!!!!!!!
  }

  @Override
  public void requestUserList() {
    client.requestUserList();
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void connect() {
    client.connect();
  }

  @Override
  public boolean isConnected() {
    return client.isConnected();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

}
