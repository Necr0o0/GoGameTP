package org.tpproject.maven.Controller.View;

import javax.swing.*;
import java.awt.*;

public class GameplayView extends BasicGameView
{
    private JButton passButton = new JButton("Pass");

    public  int mapSize = 19;
    private JButton[][] moveButton = new JButton[mapSize][mapSize];

    private JPanel gameplayPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    
    private JLabel title = new JLabel("Go Game");
    private JLabel currentPlayer = new JLabel("Current Player: Black ");

    private JLabel blackScore = new JLabel("White Captured: 0 ");
    private JLabel whiteScore = new JLabel("Black Captured: 0 ");

    private JLabel log = new JLabel( "Game Log");

    public GameplayView()
    {

       PrepareFrame();


       this.setLayout(new BorderLayout());


       this.add(titlePanel,BorderLayout.PAGE_START);

       this.add(gameplayPanel,BorderLayout.CENTER);
       gameplayPanel.setLayout(new GridLayout(mapSize,mapSize));
       for(int i = 0; i<mapSize;i++){
           for (int j= 0; j<mapSize;j++)
           {
                moveButton[i][j] = new JButton("");
                moveButton[i][j].setPreferredSize(new Dimension(30,30));
               gameplayPanel.add(moveButton[i][j]);
           }
       }


       this.add(settingsPanel,BorderLayout.LINE_END);
        settingsPanel.setLayout(new BoxLayout(settingsPanel,BoxLayout.Y_AXIS));

        settingsPanel.add(currentPlayer);
        settingsPanel.add(blackScore);
        settingsPanel.add(whiteScore);
        settingsPanel.add(passButton);

       this.add(log, BorderLayout.PAGE_END);



       titlePanel.add(title);


        pack();
    }


    public static void main(String[] args )

    {
        GameplayView settingView = new GameplayView();
    }

}
