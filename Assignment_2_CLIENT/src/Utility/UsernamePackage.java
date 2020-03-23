package Utility;

public class UsernamePackage extends NetworkPackage
{
  private String username;

  public UsernamePackage(NetworkType type, String username)
  {
    super(type);
    this.username = username;
  }

  public String getUsername()
  {
    return username;
  }
}
