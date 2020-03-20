package model;

import Utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  void sendMessage();
  String getMessageFromServer();
  void setUsername(String username);
  UserList getUserList(UserList userList);
}
