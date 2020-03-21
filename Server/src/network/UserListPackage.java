package network;


import model.UserList;

public class UserListPackage extends NetworkPackage
{
    private UserList userList;

    public UserListPackage(UserList userList)
    {
        super(NetworkType.USERLIST);
        this.userList = userList;
    }

    public UserList getUserList()
    {
        return userList;
    }

    public String toString()
    {
        return getType() + ":";
    }

}
