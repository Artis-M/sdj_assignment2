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
                        out.println(packageString);
                        break;
                    case USERLISTREQUEST:
                        UserList userList = model.getUsers();
                        NetworkPackage networkPackage1 = new UserListPackage(userList);
                        String userListString = gson.toJson(networkPackage1);
                        out.println(networkPackage1);
                }
            } catch (Exception e)
            {
                model.removeUser(username);
                close();
            }
        }
        close();
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
