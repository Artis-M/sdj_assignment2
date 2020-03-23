package View;

import ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;

  private ViewModelFactory viewModelFactory;
  private ChatController chatController;
  private UserListController userListController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("chat");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "chat":
        root = loadChatView("Chat.fxml");
        break;
      case "userList":
        root = loadUserListView("UserList.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadChatView(String fxmlFile)
  {
    if (chatController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        chatController = loader.getController();
        chatController.init(this, viewModelFactory.getChatViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      chatController.reset();
    }
    return chatController.getRoot();
  }

  private Region loadUserListView(String fxmlFile)
  {
    if (userListController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        userListController = loader.getController();
        userListController.init(this, viewModelFactory.getUserListViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      userListController.reset();
    }
    return userListController.getRoot();
  }
}
