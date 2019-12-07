package main.java.View;

import Controller.StartController;
import View.Common.StartActionListener;

import javax.swing.*;
import java.awt.*;

public class StartView extends View.BasicGameView
{
    private JButton botSettings = new JButton("Play with computer");
    private JButton hostSettings = new JButton("Host Game");
    private JButton clientSettings = new JButton("Join Game");
    private JPanel settingsPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JLabel title = new JLabel("Goo Game");

    public StartView()
    {

       PrepareFrame();
       this.setLayout(new GridLayout(2,1));
       this.add(titlePanel);
       titlePanel.add(title);

       this.add(settingsPanel);
       settingsPanel.add(botSettings);
       settingsPanel.add(hostSettings);
       settingsPanel.add(clientSettings);

       botSettings.addActionListener(new StartActionListener("bot",this));
       hostSettings.addActionListener(new StartActionListener("host",this));
       clientSettings.addActionListener(new StartActionListener("join",this));


    }
}
