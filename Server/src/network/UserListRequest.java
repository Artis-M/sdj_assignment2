package network;


public class UserListRequest extends NetworkPackage
{

    public UserListRequest()
    {
        super(NetworkType.USERLISTREQUEST);
    }
    public String toString()
    {
        return getType() + ":";
    }
}
