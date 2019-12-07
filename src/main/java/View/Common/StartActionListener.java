package View.Common;

import Controller.StartController;
import View.BasicGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartActionListener implements ActionListener
{
    String buttonType;
    private StartController startController = new StartController();
    private BasicGameView view;

    public StartActionListener(String button, BasicGameView view)
    {
        buttonType = button;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        view.Close();
        switch (buttonType)
        {
            case "host":
                startController.CreateHostSettingView();
                break;
            case "bot":
                startController.CreateBotSettingView();
                break;
            case "join":
                startController.CreateClientSettingView();
                break;
        }
    }
}
