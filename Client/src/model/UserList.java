package model;

import java.util.ArrayList;

public class UserList
{
  private ArrayList<String> userList;

  public UserList()
  {
    userList = new ArrayList<>();
  }

  public ArrayList<String> getUserList()
  {
    return userList;
  }

  public void addUser(String username)
  {
    userList.add(username);
  }

  public void removeUser(String username)
  {
    int i = 0;
    while (i < userList.size())
    {
      if (userList.get(i).equals(username))
      {
        userList.remove(i);
      }
    }
  }
}
