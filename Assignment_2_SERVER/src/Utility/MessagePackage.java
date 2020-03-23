package Utility;

import Model.Message;

public class MessagePackage extends NetworkPackage
{
  private Message message;

  public MessagePackage(NetworkType type, Message message)
  {
    super(type);
    this.message = message;
  }

  public Message getMessage()
  {
    return message;
  }

}
