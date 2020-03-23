package View;

import ViewModel.ChatViewModel;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ChatController
{
  @FXML TextField inputUsername;
  @FXML ListView<StringProperty> messagesList;
  @FXML TextField textField;
  private Region root;

 private ViewHandler viewHandler;
 private ChatViewModel chatViewModel;

 public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root)
 {
  this.viewHandler = viewHandler;
  this.root = root;
  this.chatViewModel = chatViewModel;

  this.inputUsername.textProperty().bindBidirectional(chatViewModel.getUsername());
  this.messagesList.setItems(chatViewModel.getItems());
  this.textField.textProperty().bind(chatViewModel.getMessage());
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
    chatViewModel.connectToServer();
  }

  private @FXML void onCurrentUsers()
  {
    viewHandler.openView("userList");
  }

  private @FXML void onEnter()
  {
     chatViewModel.sendMessageToServer();
  }


}
