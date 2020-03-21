package model;

import Utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void sendMessage();
  String getMessageFromServer(Message message);
  void setUsername(String username);
  ArrayList<String> getUserList(UserList userList);
}
