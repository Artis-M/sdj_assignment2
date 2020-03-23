package Model;

import Mediator.ChatClient;
import Mediator.ClientModel;
import Utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model, UnnamedPropertyChangeSubject
{
  private String username;
  private PropertyChangeSupport propertyChangeSupport;
  private ClientModel clientModel;

  public ModelManager() throws IOException
  {
    this.username = null;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.clientModel = new ChatClient(this);
    clientModel.connect();
  }

  public void setClientModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
  }

  @Override public ArrayList<String> getList()
  {
    return clientModel.getList();
  }

  @Override public void sendMessage(String message)
  {
    clientModel.sendMessage(message);
  }

  @Override public void getMessageFromServer(Message message)
  {
    System.out.println(message.getFullMessage());
    propertyChangeSupport.firePropertyChange("Message", null, message);
  }

  @Override public void setUsername(String username)
  {
    this.username = username;
    clientModel.sendUsername(username);
  }

  @Override public void getListFromServer(ArrayList<String> list)
  {
    System.out.println(list);
    propertyChangeSupport.firePropertyChange("List", null, list);
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
