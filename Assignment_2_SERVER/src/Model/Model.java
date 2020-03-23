package Model;

import Utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  ArrayList<String> getList();
  void addUsername(String username);
  void addMessage(Message message);
}
