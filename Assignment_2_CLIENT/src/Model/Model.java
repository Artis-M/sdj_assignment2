package Model;

import Mediator.ClientModel;
import Utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void setClientModel(ClientModel clientModel);
  ArrayList<String> getList();

  void sendMessage(String message);
  void getMessageFromServer(Message message);

  void setUsername(String username);
  void getListFromServer(ArrayList<String> list);
}
