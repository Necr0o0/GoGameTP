package org.tpproject.maven.Controller.View.Common;

import javax.swing.*;
import java.awt.*;

public class LabelComboBox extends JPanel
{

    private JLabel title;
    private JComboBox comboBox;

    public LabelComboBox(String titleText, String[] comboBoxValues)
    {

        title = new JLabel(titleText);
        comboBox = new JComboBox(comboBoxValues);

        this.setLayout(new FlowLayout());
        this.add(title);
        this.add(comboBox);
    }

}
