package ViewModel;

import Model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import Model.Message;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private StringProperty message;
  private StringProperty username;
  private ObservableList<StringProperty> items;
  private Model model;

  public ChatViewModel(Model model)
  {
    this.model = model;
    this.message = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.items = FXCollections.observableArrayList();
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

  public ObservableList<StringProperty> getItems()
  {
    return items;
  }

  public void sendMessageToServer()
  {
    model.sendMessage(message.get());
  }

  public void connectToServer()
  {
    model.setUsername(username.get());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->
        {
            if(evt.getPropertyName().equals("Message"))
            {
              items.add(new SimpleStringProperty(((Message) evt.getNewValue()).getFullMessage()));
            }
        }
    );
  }
}
