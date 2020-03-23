package Mediator;

import Utility.ListPackage;
import Utility.MessagePackage;
import Utility.NetworkPackage;
import Utility.NetworkType;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiver implements Runnable
{
  private BufferedReader in;
  private ChatClient chatClient;
  private Gson gson;

  public ChatClientReceiver(ChatClient client, BufferedReader in)
  {
    this.chatClient = client;
    this.in = in;
    gson = new Gson();
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        String message = in.readLine();
        NetworkPackage networkPackage = gson.fromJson(message, NetworkPackage.class);
        if(networkPackage.getType().equals(NetworkType.GET_MESSAGE))
        {
          MessagePackage messagePackage = gson.fromJson(message, MessagePackage.class);
          chatClient.sendMessageToModel(messagePackage.getMessage());
        }
        else if(networkPackage.getType().equals(NetworkType.GET_LIST))
        {
          ListPackage listPackage = gson.fromJson(message, ListPackage.class);
          chatClient.sendListToModel(listPackage.getList());
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
