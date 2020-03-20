package ViewModel;

import model.Model;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private UserListViewModel userListViewModel;

public ViewModelFactory(Model model)
{
this.chatViewModel = new ChatViewModel(model);
this.userListViewModel = new UserListViewModel(model);
}

public ChatViewModel getChatViewModel()
{
  return chatViewModel;
}

public UserListViewModel getUserListViewModel()
{
  return userListViewModel;
}
}
