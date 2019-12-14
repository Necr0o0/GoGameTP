package View.Settings;

public class BotSettingView extends SettingsView
{

    BotSettingView settingView;

    public BotSettingView()
    {
        AddMapsize();
        AddPlayerColor();
        AddStartButton();
    }
    public static void main(String[] args )

    {
        BotSettingView settingView = new BotSettingView();
    }



}
