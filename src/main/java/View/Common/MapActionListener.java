package View.Common;

import Controller.Client;
import View.Sprites.Sprites;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapActionListener implements ActionListener {

    private int xPos;
    private int yPos;
    private String color;
    private Client client;

// Zmieniłem tutaj konstruktor, bo inaczej przycisk nie miałby dostępu do klienta,
// więc nie miałby jak powiedzieć serwerowi, że wykonano ruch. ~P
    public  MapActionListener(int x, int y, String value, Client client)
    {
        this.xPos = x;
        this.yPos = y;
        this.color = value;
        this.client = client;   // O to tutaj dodałem ~P
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Have to send info about position and current player color
        //((JButton)e.getSource()).setIcon(Sprites.GetBlack());
        client.SendMessage( "PUT_STONE " + xPos + " " + yPos );
    }
}
