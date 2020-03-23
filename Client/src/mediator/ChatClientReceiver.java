package mediator;

import com.google.gson.Gson;
import model.Message;
import model.UserList;
import network.MessagePackage;
import network.NetworkPackage;
import network.NetworkType;
import network.UserListPackage;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiver implements Runnable{
    private BufferedReader in;
private ChatClient client;
private Gson gson;
    public ChatClientReceiver(ChatClient client, BufferedReader in){
        gson = new Gson();
this.in = in;
this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Receiver running");
while(true){
    try {
        String reply = in.readLine();
        NetworkPackage networkPackage = gson.fromJson(reply, NetworkPackage.class);
        System.out.println("Received NetworkPackage");
        switch (networkPackage.getType()){
            case MESSAGE:
                MessagePackage messagePackage = gson.fromJson(reply, MessagePackage.class);
                System.out.println("Received message");
                client.receivedMessage(messagePackage.getMessage());
                break;
            case USERLIST:
                UserListPackage userListPackage = gson.fromJson(reply, UserListPackage.class);
                System.out.println("Received userlist");
                client.receivedUserList(userListPackage.getUserList());
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

}
    }
}
