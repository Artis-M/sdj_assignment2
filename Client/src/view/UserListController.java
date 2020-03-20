package view;

import ViewModel.UserListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class UserListController
{
  @FXML ListView<String> userList;
  private Region root;

  private ViewHandler viewHandler;
  private UserListViewModel userListViewModel;

  public void init(ViewHandler viewHandler, UserListViewModel userListViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.userListViewModel = userListViewModel;

    this.userList.getCellFactory();   //this I don't know if it is the solution.
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {

  }

  private @FXML void onBack()
  {
    viewHandler.openView("chat");
  }
}
