package View;

import javax.swing.*;
import java.awt.*;

public abstract class BasicGameView extends JFrame
{
    protected void PrepareFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        setSize(500,300);
        setVisible(true);
    }
}
