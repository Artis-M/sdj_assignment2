package mediator;

import com.google.gson.Gson;
import model.Message;
import model.UserList;
import network.NetworkPackage;
import network.NetworkType;

import java.io.BufferedReader;
import java.io.IOException;

public class CharClientReceiver implements Runnable{
    private BufferedReader in;
private ChatClient client;
    public CharClientReceiver(ChatClient client, BufferedReader in){
this.in = in;
this.client = client;
    }

    @Override
    public void run() {
while(true){
    try {
        Gson gson = new Gson();
        String reply = in.readLine();
        NetworkPackage networkPackage = gson.fromJson(reply, NetworkPackage.class);
        switch (networkPackage.getType()){
            case MESSAGE:
                Message message = gson.fromJson(reply, Message.class);
                client.receivedMessage(message);
            case USERLIST:
                UserList userList = gson.fromJson(reply, UserList.class);
                client.receivedUserList(userList);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

}
    }
}
