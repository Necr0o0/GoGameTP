package View.Settings;

public class HostSettingView extends SettingsView
{

    HostSettingView settingView;

    public HostSettingView()
    {
        AddMapsize();
        AddPlayerColor();
        AddPort();
        AddServerStartButton();

    }
    public static void main(String[] args )

    {
        HostSettingView settingView = new HostSettingView();
    }



}
