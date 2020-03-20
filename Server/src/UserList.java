import java.util.ArrayList;

public class UserList
{
    private ArrayList<String> userList;

    public UserList()
    {
        this.userList = new ArrayList<>();
    }

    public void addUser(String name)
    {
        userList.add(name);
    }

    public void removeUser(String name)
    {
        for (int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).equals(name))
            {
                userList.remove(i);
            }
        }
    }

    public String getUserList()
    {
        for (int i = 0; i < userList.size(); i++)
        {
            return userList.get(i) + "\n";
        }
        return null;
    }
    public ArrayList getList(){
        return userList;
    }

    public int getSize()
    {
        return userList.size();
    }
}
