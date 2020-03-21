import com.google.gson.Gson;

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

    public ChatClientHandler(Socket socket, Model model)
            throws IOException
    {
        this.model = model;
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        model.addListener(this);
    }

    @Override
    public void run()
    {
        Gson gson = new Gson();
        running = true;
        while (running)
        {
            try
            {
                String request = in.readLine();
                model.addLog("Client> " + request);
                String user = in.readLine();
                if (!model.getUsers().getList().contains(user))
                {
                    model.addUser(user);
                }
                model.addLog("Client> " + user);
                if (user != null)
                {
                    Message message = new Message(request, user);
                    String back = gson.toJson(message);
                    out.println(back);
                }
                Message message = new Message(request);
                String back = gson.toJson(message);
                out.println(back);
                if (request.contentEquals("quit"))
                {
                    for (int i = 0; i < model.getUsers().getList().size(); i++)
                    {
                        if (model.getUsers().getList().get(i).equals(user))
                        {
                            model.getUsers().getList().remove(i);
                        }
                    }
                    close();
                }
            } catch (Exception e)
            {
                model.addLog("Client error");
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
