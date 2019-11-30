package org.tpproject.maven.Controller.View.Settings;

import org.tpproject.maven.Controller.View.Common.LabelComboBox;
import org.tpproject.maven.Controller.View.BasicGameView;

import javax.swing.*;
import java.awt.*;

public abstract class SettingsView extends BasicGameView
{
    private JPanel  _settingsPanel = new JPanel();

    private JPanel  _startPanel = new JPanel();

    private String[] _mapSizes = { "19 x 19", "13 x 13", "9 x 9" };
    private String[] _playerColors = { "Black (Starts game)", "White" };
    private String[]  _ports = { "58901", "58901" };

    private LabelComboBox _mapsize = new LabelComboBox("Map Size:",_mapSizes);
    private LabelComboBox _playerColor = new LabelComboBox("Player color:",_playerColors);
    private LabelComboBox _port = new LabelComboBox("Port:", _ports);


    private JButton _startGameButton = new JButton("Start");


    public SettingsView()
    {
       PrepareFrame();
       PrepareSettingsPanel();
       PrepareStartPanel();
       AddStartButton();
    }



    void PrepareSettingsPanel()
    {
        this.add(_settingsPanel);
        _settingsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    void PrepareStartPanel()
    {
        this.add(_startPanel);
    }

    void AddMapsize()
    {
        _settingsPanel.add(_mapsize);
    }
    void AddPlayerColor()
    {
        _settingsPanel.add(_playerColor);
    }



    void AddStartButton()
    {
        _startPanel.add(_startGameButton);
    }

    void AddPort()
    {
        _settingsPanel.add(_port);
    }
}
