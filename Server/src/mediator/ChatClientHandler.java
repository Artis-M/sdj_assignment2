package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;
import model.UserList;
import network.MessagePackage;
import network.NetworkPackage;
import network.UserListPackage;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener
{

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Model model;
    private boolean running;
    private String username;

    public ChatClientHandler(Socket socket, Model model)
            throws IOException
    {
        this.model = model;
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        model.addListener(this);
        this.username = "UnnamedUser";
    }

    @Override
    public void run()
    {
        Gson gson = new Gson();
        running = true;
        try {
            username = in.readLine();
            System.out.println(username);
            model.addUser(username);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (running)
        {
            try
            {
                String request = in.readLine();
                NetworkPackage networkPackage = gson.fromJson(request, NetworkPackage.class);
                switch (networkPackage.getType()){
                    case MESSAGE:
                        String packageString = gson.toJson(networkPackage);
                        MessagePackage messagePackage = gson.fromJson(request, MessagePackage.class);
                        System.out.println("received message from client: ");
                        System.out.println(messagePackage.getMessage().getFullMessage());
                        out.println(request);

                        break;
                    case USERLISTREQUEST:
                        UserList userList = model.getUsers();
                        NetworkPackage networkPackage1 = new UserListPackage(userList);
                        String userListString = gson.toJson(networkPackage1);
                        System.out.println("received userlist request from client");
                        out.println(userListString);
                        break;
                }
            } catch (Exception e)
            {
                System.out.println("Error");

                close();
            }
        }
        close();
        model.removeUser(username);
        System.out.println("UserLeft");
    }

    public void close()
    {
        running = false;
        try
        {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e)
        {
            //
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
    }


}
