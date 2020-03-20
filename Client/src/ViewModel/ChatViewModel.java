package ViewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListView;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private StringProperty message;
  private StringProperty username;
  private ListView<StringProperty> listView;
  private Model model;

  public ChatViewModel(Model model)
  {
    this.model = model;
    this.message = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.listView = new ListView<>();
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

  public ListView<StringProperty> getListView()
  {
    return listView;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->
        {
          listView.getItems().add(message);
        }
    );
  }
}
