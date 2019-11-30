package View.Sprites;

import javax.swing.*;

public class Sprites {



    public static ImageIcon GetPoint()
    {
        ImageIcon point = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\View\\Sprites\\EmptyArea.png");
        System.out.println(System.getProperty("user.dir"));
        return point;
    }
    public static ImageIcon GetBlack()
    {
        ImageIcon point = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\View\\Sprites\\Black.png");
        System.out.println(System.getProperty("user.dir"));
        return point;
    }
}
