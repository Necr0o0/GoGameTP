package View;

        import javax.swing.*;
        import java.awt.*;

public class SettingsView extends JFrame
{
    public SettingsView()
    {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setVisible(true);
    }


    public static void main(String[] args )

    {
        SettingsView _settingView = new SettingsView();
    }

}
