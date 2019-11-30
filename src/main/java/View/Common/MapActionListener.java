package View.Common;

import View.Sprites.Sprites;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapActionListener implements ActionListener {

    private int xPos;
    private int Ypos;
    private String color;


    public  MapActionListener(int x, int y, String value)
    {
        this.xPos = x;
        this.Ypos = y;
        this.color = value;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Have to send info about position and current player color
        ((JButton)e.getSource()).setIcon(Sprites.GetBlack());
    }
}
