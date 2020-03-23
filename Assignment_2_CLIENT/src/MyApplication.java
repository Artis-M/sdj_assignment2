import Mediator.ChatClient;
import Model.Model;
import Model.ModelManager;
import View.ViewHandler;
import ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.Message;

public class MyApplication extends Application
{

  @Override public void start(Stage stage) throws Exception
  {
    try
    {
      Model model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler viewHandler = new ViewHandler(viewModelFactory);
      viewHandler.start(stage);

//      ChatClient chatClient = new ChatClient(model);
//      chatClient.connect();
//      System.out.println("________________________________");
//      chatClient.sendUsername("zzz");
//      System.out.println("________________________________");
//      chatClient.showUserList();
//      System.out.println("________________________________");

//      Message m1 = new Message("zzz", "asfasfasfafs");
//      chatClient.sendMessage(m1);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
