package view;

import ViewModel.ChatViewModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ChatController
{
  @FXML TextField inputUsername;
  @FXML ListView<String> messagesList;
  @FXML TextField chatField;
  @FXML Label errorLabel;
  private Region root;

 private ViewHandler viewHandler;
 private ChatViewModel chatViewModel;

 public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root)
 {
  this.viewHandler = viewHandler;
  this.root = root;
  this.chatViewModel = chatViewModel;
  this.messagesList.setItems(chatViewModel.getItems());
  errorLabel.setText("");
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
      chatViewModel.setUsername(inputUsername.getText());
      if(inputUsername.getText().equals("")){
          inputUsername.setPromptText("Select Username");
          System.out.println("No username was set");
      }
      else if(!(chatViewModel.isConnected())){
          chatViewModel.connect();
          inputUsername.setPromptText("");
          errorLabel.setText("Connected");
          errorLabel.setStyle("-fx-text-fill: green");
      }

  }
    @FXML
    public void onEnter(ActionEvent ae){
     chatViewModel.sendMessage(chatField.getText());
     chatField.clear();
     System.out.println("Enter Pressed");
    }

  private @FXML void onCurrentUsers()
  {
      if(chatViewModel.isConnected()){
          chatViewModel.requestList();
          viewHandler.openView("userList");
      }
      else{
errorLabel.setText("You must be connected");
      }

  }



}
