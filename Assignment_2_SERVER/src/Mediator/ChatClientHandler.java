package Mediator;

import Model.Model;
import Utility.*;
import com.google.gson.Gson;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import Model.Message;

public class ChatClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private boolean running;
  private Gson gson;

  public ChatClientHandler(Socket socket, Model model) throws IOException
  {
    this.running = true;
    gson = new Gson();
    this.model = model;
    this.socket = socket;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    model.addListener(this);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    MessagePackage messagePackage = new MessagePackage(NetworkType.GET_MESSAGE, (Message) evt.getNewValue());
    String reply = gson.toJson(messagePackage);
    out.println(reply);
  }

  @Override public void run()
  {
    running = true;
    while (running)
    {
      try
      {
        String request = in.readLine();
        NetworkPackage networkPackage = gson.fromJson(request, NetworkPackage.class);

        if(networkPackage.getType().equals(NetworkType.REQUEST_MESSAGE))
        {
          MessagePackage messagePackage = gson.fromJson(request, MessagePackage.class);
          model.addMessage(messagePackage.getMessage());
        }
        else if(networkPackage.getType().equals(NetworkType.REQUEST_LIST))
        {
          ListPackage listPackage = new ListPackage(NetworkType.GET_LIST, model.getList());
          String reply = gson.toJson(listPackage);
          out.println(reply);
        }
        else if(networkPackage.getType().equals(NetworkType.REQUEST_USERNAME))
        {
          UsernamePackage usernamePackage = gson.fromJson(request, UsernamePackage.class);
          model.addUsername(usernamePackage.getUsername());
          System.out.println(model.getList());
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }
}
