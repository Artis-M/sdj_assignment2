package mediator;

import model.Message;

public interface ServerModel {
public void connect();
public void disconnect();
public void sendMessage(Message message);
public void requestUserList();

}
