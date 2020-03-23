package ViewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private StringProperty message;
  private StringProperty username;
  private ObservableList<String> listView;
  private Model model;

  public ChatViewModel(Model model)
  {
    this.model = model;
    this.message = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.listView = FXCollections.observableArrayList();
    this.model.addListener(this);
  }

  public StringProperty getMessage()
  {
    return message;
  }

  public StringProperty getUsername()
  {
    return username;
  }


  public void sendMessage(String message){
    System.out.println("ViewModel got message");
model.sendMessage(message);
  }

  public void connect(){
    model.connect();
  }
  public ObservableList<String> getItems()
  {
    return listView;
  }
  public void setUsername(String usernameString){
model.setUsername(usernameString);
  }
  public void requestList(){
    model.requestUserList();
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->
            {
              if(evt.getPropertyName().equals("NewMessageFromServer"))
              {
                System.out.println("Even triggered and got the message");
                listView.add((((Message) evt.getNewValue()).getFullMessage()));
              }
            }
    );
  }
}
