package mediator;

import com.google.gson.Gson;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private final int PORT = 1337;
    private final String HOST = "localhost";
    private String host;
    private int port;

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    private Gson gson;

    public ChatClient() {
        this.host = HOST;
        this.port = PORT;
    }

    public ChatClient(String host, int port) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void connect() {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Failed to connect to the server, make sure that the server is running");
        }
    }
    @Override
    public void disconnect(){
        try{
            in.close();
            out.close();
            socket.close();
        }
catch (IOException e) {
System.out.println("Closing the connection to the server has failed");
        }
    }
    @Override
    public void sendMessage(Message message){
        gson = new Gson();
        String dataPacket = gson.toJson(message);
        out.println(dataPacket);
    }
}
