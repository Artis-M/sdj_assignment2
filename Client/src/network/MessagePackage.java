package network;

import model.Message;

public class MessagePackage extends NetworkPackage
{
    private Message message;

    public MessagePackage(NetworkType type, Message message)
    {
        super(NetworkType.MESSAGE);
        this.message = message;
    }

    public Message getMessage()
    {
        return message;
    }

    public String toString()
    {
        return getType() + ": " + message.getFullMessage();
    }

}
