package View.Settings;

import Controller.Client;
import Controller.Server;
import Controller.User;
import Model.Enums.PlayerColor;
import Model.DefaultGameLogic;
import View.Common.LabelComboBox;
import View.BasicGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        _startGameButton.addActionListener(e -> {

            System.out.println("ODPALAM ");

            try {
                Client client = new Client( "127.0.0.1" );
                client.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    void AddServerStartButton()
    {
        _startPanel.add(_startGameButton);
        _startGameButton.addActionListener(e -> {


            Server server = new Server();
            server.start();

            try {
                Client client = new Client( "127.0.0.1" );
                client.start();


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });




    }

    void AddPort()
    {
        _settingsPanel.add(_port);
    }
}
