package Utility;

import java.util.ArrayList;

public class ListPackage extends NetworkPackage
{
  private ArrayList<String> list;

  public ListPackage(NetworkType type, ArrayList<String> list)
  {
    super(type);
    this.list = list;
  }

  public ArrayList<String> getList()
  {
    return list;
  }
}
