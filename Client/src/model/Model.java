package model;

import Utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void sendMessage(String message);
  void getMessageFromServer(Message message);
  void setUsername(String username);
  ArrayList<String> getUserList(UserList userList);
  String getUsername();
  void connect();
}
