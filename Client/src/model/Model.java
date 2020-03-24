package model;

import Utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void sendMessage(String message);
  void getMessageFromServer(Message message);
  void setUsername(String username);
  void getUserList(UserList userList);
  void requestUserList();
  String getUsername();
  void connect();
  boolean isConnected();
}
