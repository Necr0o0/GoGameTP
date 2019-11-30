package org.tpproject.maven.Controller.View.Settings;

public class HostSettingView extends SettingsView
{

    HostSettingView settingView;

    HostSettingView()
    {
        AddMapsize();
        AddPlayerColor();
        AddPort();

    }
    public static void main(String[] args )

    {
        HostSettingView settingView = new HostSettingView();
    }



}
