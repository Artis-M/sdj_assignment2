package ViewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserListViewModel implements PropertyChangeListener
{
  private Model model;
  private ListView<StringProperty> listView;

  public UserListViewModel(Model model)
  {
    this.model = model;
    // this.listView = something
    this.model.addListener(this);
  }

  public ListView<StringProperty> getListView()
  {
    return listView;
  }

  public void addToList(Message message)
  {
    listView.getItems().add(new SimpleStringProperty(message.getMessage()));
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->
        {
          addToList((Message) evt.getNewValue());
        }
    );
  }
}
