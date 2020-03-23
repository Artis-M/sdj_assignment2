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
import java.util.ArrayList;

public class UserListViewModel implements PropertyChangeListener
{
  private Model model;
  private ListView<StringProperty> listView;
  private ObservableList<StringProperty> items;
  public UserListViewModel(Model model)
  {
    this.model = model;
    // this.listView = something
    this.model.addListener(this);
    this.items = FXCollections.observableArrayList();
  }

  public ListView<StringProperty> getListView()
  {
    return listView;
  }

  public void addToList(Message message)
  {
    //listView.getItems().add(new SimpleStringProperty(message.getMessage()));
  }
  public ObservableList<StringProperty> getItems()
  {
    return items;
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->
            {
              if(evt.getPropertyName().equals("List"))
              {
                ArrayList<String> list = (ArrayList<String>) evt.getNewValue();
                for(int i=0;i<list.size();i++)
                  items.add(new SimpleStringProperty(list.get(i)));
              }
            }
    );
  }
}