package Mediator;

import Model.Message;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientModel
{
   void connect() throws IOException;
   void disconnect() throws IOException;

   void sendMessage(String message);
   void showUserList();
   void sendUsername(String username);
   ArrayList<String> getList();

   void sendMessageToModel(Message message);
   void sendListToModel(ArrayList<String> list);
}
