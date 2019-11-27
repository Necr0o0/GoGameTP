package View;

import javax.swing.*;
import java.awt.*;

public class StartView extends BasicGameView
{
    private JButton botSettings = new JButton("Play with computer");
    private JButton hostSettings = new JButton("Host Game");
    private JButton clientSettings = new JButton("Join Game");
    private JPanel settingsPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JLabel title = new JLabel("Goo Game");

    public StartView()
    {
       //super("Start");

       PrepareFrame();
       this.setLayout(new GridLayout(2,1));
       this.add(titlePanel);
       titlePanel.add(title);

       this.add(settingsPanel);
       settingsPanel.add(botSettings);
       settingsPanel.add(hostSettings);
       settingsPanel.add(clientSettings);
    }


    public static void main(String[] args )

    {
        StartView settingView = new StartView();
    }

}
