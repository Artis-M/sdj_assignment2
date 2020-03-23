package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String message;
  private String username;

  public Message(String username, String message)
  {
    this.username = username;
    this.message = message;
  }

  public String getUsername()
  {
    return username;
  }

  public String getMessage()
  {
    return message;
  }

  public String getFullMessage()
  {
    LocalDateTime timeDate = LocalDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");
    return "[" + dateFormatter.format(timeDate) + "]" + " " + username + ": " + message;
  }
}
