package View;

import Controller.Client;
import Model.Enums.PlayerColor;
import View.Common.MapActionListener;
import View.Sprites.Sprites;

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
    private JLabel currentPlayer = new JLabel("Current Player: Undefined ");

    private JLabel blackScore = new JLabel("White Captured: 0 ");
    private JLabel whiteScore = new JLabel("Black Captured: 0 ");

    private JLabel log = new JLabel( "Game Log");

    public GameplayView( Client client ) {
      PrepareFrame();

      this.setLayout(new BorderLayout());
      this.add(titlePanel,BorderLayout.PAGE_START);

      this.currentPlayer.setText( "Current player: " + client.color ); // Set the color label

      this.add(gameplayPanel,BorderLayout.CENTER);
      gameplayPanel.setLayout(new GridLayout(mapSize,mapSize));
      for(int i = 0; i<mapSize;i++) {
        for (int j = 0; j < mapSize; j++) {
          moveButton[i][j] = new JButton("");
          moveButton[i][j].setPreferredSize(new Dimension(30, 30));
          moveButton[i][j].setIcon(Sprites.GetPoint());
          moveButton[i][j].addActionListener(new MapActionListener(i, j, "Empty", client));
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

  public void PlaceStone( int x, int y, PlayerColor color ) {
      if( color == PlayerColor.Black ) {
        moveButton[x][y].setIcon(Sprites.GetBlack());
      } else {
        moveButton[x][y].setIcon(Sprites.GetWhite());
      }
  }

  public void Log( String message ) {
      log.setText( message );
  }

}
