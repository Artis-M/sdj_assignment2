package view;

import ViewModel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ChatController
{
  @FXML TextField inputUsername;
  @FXML ListView<String> messagesList;
  private Region root;

 private ViewHandler viewHandler;
 private ChatViewModel chatViewModel;

 public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root)
 {
  this.viewHandler = viewHandler;
  this.root = root;
  this.chatViewModel = chatViewModel;

  this.inputUsername.textProperty().bindBidirectional(chatViewModel.getUsername());
  this.messagesList.getCellFactory();   //this I don't know if it is the solution.
 }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    inputUsername.setText("");
  }

  private @FXML void onConnect()
  {
    // see later
  }

  private @FXML void onCurrentUsers()
  {
    viewHandler.openView("userList");
  }



}
