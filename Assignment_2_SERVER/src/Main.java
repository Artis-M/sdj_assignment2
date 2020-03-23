import Mediator.ChatClientConnector;
import Model.Model;
import View.SimpleConsoleView;
import Model.ModelManager;

public class  Main
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
//    SimpleConsoleView simpleConsoleView = new SimpleConsoleView(model);
    ChatClientConnector chatClientConnector = new ChatClientConnector(model);
    Thread t1 = new Thread(chatClientConnector);
    t1.start();
  }
}
