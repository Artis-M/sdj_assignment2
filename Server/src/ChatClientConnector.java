import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatClientConnector implements Runnable
{


    private final int PORT = 6789;
    private Model model;
    private boolean running;
    private ServerSocket welcomeSocket;

    public ChatClientConnector(Model model)
    {
        this.model = model;
    }

    private void start() throws IOException
    {
       System.out.println("Starting Server...");
        welcomeSocket = new ServerSocket(PORT);

        running = true;
        while (running)
        {
            System.out.println("Waiting for a client...");
            Socket socket = welcomeSocket.accept();
            ChatClientHandler client = new ChatClientHandler(socket, model);
            Thread clientThread = new Thread(client);
            clientThread.setDaemon(true);
            clientThread.start();
        }
    }

    @Override
    public void run()
    {
        try
        {
            start();
        } catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void stop()
    {
        running = false;
        try
        {
            welcomeSocket.close();
        } catch (Exception e)
        {
            //
        }

    }

}
