package Mediator;

import Model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import Model.Message;
import Utility.MessagePackage;
import Utility.NetworkPackage;
import Utility.NetworkType;
import Utility.UsernamePackage;
import com.google.gson.Gson;

public class ChatClient implements ClientModel
{
  public final static String HOST = "localhost";
  public final static int PORT = 5555;
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private Gson gson;
  private String username;

  public ChatClient(String host, int port, Model model)
  {
    this.username = null;
    this.gson = new Gson();
    this.host = host;
    this.port = port;
    this.model = model;
  }

  public ChatClient(Model model)
  {
    this.username = null;
    this.gson = new Gson();
    this.host = HOST;
    this.port = PORT;
    this.model = model;
    this.model.setClientModel(this);
  }

  @Override public void connect() throws IOException
  {
    this.socket = new Socket(host, port);
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    ChatClientReceiver chatClientReceiver = new ChatClientReceiver(this, in);
    Thread t1 = new Thread(chatClientReceiver);
    t1.start();
  }

  @Override public void disconnect() throws IOException
  {
    socket.close();
    in.close();
    out.close();
  }

  @Override public void sendMessage(String message)
  {
    Message message1 = new Message(username, message);
    MessagePackage messagePackage = new MessagePackage(NetworkType.REQUEST_MESSAGE, message1);
    String request = gson.toJson(messagePackage);
    out.println(request);
  }

  @Override public void showUserList()
  {
    NetworkPackage networkPackage = new NetworkPackage(NetworkType.REQUEST_LIST);
    String request = gson.toJson(networkPackage);
    out.println(request);
  }

  @Override public void sendUsername(String username)
  {
    this.username = username;
    UsernamePackage usernamePackage = new UsernamePackage(NetworkType.REQUEST_USERNAME, username);
    String request = gson.toJson(usernamePackage);
    out.println(request);
  }

  @Override
  public ArrayList<String> getList() {
    return model.getList();
  }

//  @Override public ArrayList<String> getList()
  ////  {
  ////    NetworkPackage networkPackage = new NetworkPackage(NetworkType.REQUEST_LIST);
  ////    String request = gson.toJson(networkPackage);
  ////    out.println(request); wait and notify
//  method from receiver to notify
  ////  }

  @Override public void sendMessageToModel(Message message)
  {
    model.getMessageFromServer(message);
  }

  @Override public void sendListToModel(ArrayList<String> list)
  {
    model.getListFromServer(list);
  }

}
