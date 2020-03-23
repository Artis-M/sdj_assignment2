package Mediator;

import Model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatClientConnector implements Runnable
{
  public final int PORT = 5555;
  public Model model;
  private ServerSocket welcomeSocket;
  private boolean running;

  public ChatClientConnector(Model model)
  {
    this.running = true;
    this.model = model;
  }

  private void start() throws IOException
  {
    welcomeSocket = new ServerSocket(PORT);
    while (running)
    {
      Socket socket = welcomeSocket.accept();
      ChatClientHandler chatClientHandler = new ChatClientHandler(socket, model);
      Thread t1 = new Thread(chatClientHandler);
      t1.start();
    }
  }

  @Override public void run()
  {
      try
      {
        start();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
  }

public void stop() throws IOException
{
  running = false;
  try
  {
    welcomeSocket.close();
  }
  catch (Exception e)
  {
    //
  }
}
}
