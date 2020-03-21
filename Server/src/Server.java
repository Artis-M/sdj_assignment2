import mediator.ChatClientConnector;
import model.Model;
import model.ModelManager;

public class Server
{
    public static void main(String[] args)
    {
        Model model = new ModelManager();
        ChatClientConnector chatClientConnector = new ChatClientConnector(model);
        Thread t = new Thread(chatClientConnector);
        t.start();
    }
}
