package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;
import model.UserList;
import network.MessagePackage;
import network.NetworkPackage;
import network.NetworkType;
import network.UserListRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements ServerModel {
    private final int PORT = 1337;
    private final String HOST = "localhost";
    private String host;
    private int port;
    private Model model;
    private Socket socket;
    private boolean isConnected;
    private BufferedReader in;
    private PrintWriter out;

    private Gson gson;

    private ChatClientReceiver chatClientReceiver;

    public ChatClient(Model model) {
        this.host = HOST;
        this.port = PORT;
        this.model = model;
        this.isConnected = false;
    }

    public ChatClient(String host, int port, Model model) {
        this.port = port;
        this.host = host;
        this.model = model;
        this.isConnected = false;
    }

    @Override
    public void connect() {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            chatClientReceiver = new ChatClientReceiver(this, in);
            Thread t0 = new Thread(chatClientReceiver);
            t0.start();
            System.out.println("Connected to server: " + socket.getInetAddress());
            out.println(model.getUsername());
            isConnected = true;
        } catch (IOException e) {
            System.out.println("Failed to connect to the server, make sure that the server is running");
        }
    }

    @Override
    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
            isConnected = false;
        } catch (IOException e) {
            System.out.println("Closing the connection to the server has failed");
        }
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public synchronized void sendMessage(Message message) {
        gson = new Gson();
        NetworkPackage networkData = new MessagePackage(NetworkType.MESSAGE, message);
        String dataPacket = gson.toJson(networkData);
        out.println(dataPacket);
    }

    @Override
    public synchronized void requestUserList() {
        gson = new Gson();
        NetworkPackage request = new UserListRequest();
        String requestString = gson.toJson(request);
        out.println(requestString);
    }

    public synchronized void receivedMessage(Message message) {
        model.getMessageFromServer(message);
    }

    public synchronized void receivedUserList(UserList userList) {
        model.getUserList(userList);
    }
}
