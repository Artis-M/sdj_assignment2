package ViewModel;

import Model.Message;
import Model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class UserListViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<StringProperty> items;

  public UserListViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this);
    this.items = FXCollections.observableArrayList();
    //model manager method getting previous usernames similar to lines 46 47
  }

  public ObservableList<StringProperty> getItems()
  {
    return items;
  }

//  public void addToList(Message message)
//  {
//    items.add(new SimpleStringProperty(message.getUsername()));
//  }

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
