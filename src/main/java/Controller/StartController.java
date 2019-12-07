package Controller;

import View.Settings.BotSettingView;
import View.Settings.ClientSettingView;
import View.Settings.HostSettingView;
import main.java.View.StartView;

public class StartController
{

    public void StartApp(){
        StartView startView = new StartView();
    }

    public void CreateBotSettingView()
    {
        BotSettingView bot = new BotSettingView();
    }
    public void CreateClientSettingView(){
        ClientSettingView client = new ClientSettingView();
    }
    public void CreateHostSettingView(){
        HostSettingView hostSettingView = new HostSettingView();
    }
}
