package View.Sprites;

import javax.swing.*;

public class Sprites {



    public static ImageIcon GetPoint()
    {
        ImageIcon point = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\View\\Sprites\\EmptyArea.png");

        return point;
    }
    public static ImageIcon GetBlack()
    {
        ImageIcon point = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\View\\Sprites\\Black.png");
        return point;
    }
    public static ImageIcon GetWhite()
    {
        ImageIcon point = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\View\\Sprites\\White.png");

        return point;
    }
}
