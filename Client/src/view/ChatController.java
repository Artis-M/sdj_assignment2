package view;

import ViewModel.ChatViewModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ChatController
{
  @FXML TextField inputUsername;
  @FXML ListView<StringProperty> messagesList;
  @FXML TextField chatField;
  private Region root;

 private ViewHandler viewHandler;
 private ChatViewModel chatViewModel;

 public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root)
 {
  this.viewHandler = viewHandler;
  this.root = root;
  this.chatViewModel = chatViewModel;
  this.messagesList.setItems(chatViewModel.getItems());
  this.inputUsername.textProperty().bindBidirectional(chatViewModel.getUsername());
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
    chatViewModel.connect();
  }
    @FXML
    public void onEnter(ActionEvent ae){
     chatViewModel.sendMessage(chatField.getText());
     System.out.println("Enter Pressed");
    }

  private @FXML void onCurrentUsers()
  {
    viewHandler.openView("userList");
  }



}
