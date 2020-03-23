package Model;

import java.util.ArrayList;

public class Message
{
  private String text;
  private String username;
  private ArrayList<String> list;

  public Message(String text, String username)
  {
    this.text = text;
    this.username = username;
    this.list = new ArrayList<>();
  }

  public Message(String text)
  {
    this.text = text;
    this.username = "Unidentified";
  }

  public String getText()
  {
    return text;
  }

  public String getUsername()
  {
    return username;
  }

  public void addLog(String txt)
  {
    list.add(txt);
  }

  public int getLogSize()
  {
    return list.size();
  }

  public String getInfo()
  {
    return "Message: " + text + ", sent by: " + username + ".";
  }
}
