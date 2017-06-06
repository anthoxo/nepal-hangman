package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAddTheme extends JPanel {
    private AdminView frame;

    private JPanel panelUp = new JPanel();
    private JPanel panelTheme = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelDown = new JPanel();

    private JTextField fieldTheme = new JTextField();

    private JLabel labelUp = new JLabel("Add a theme in the data base");
    private JLabel labelTheme = new JLabel("Theme : ");
    private JLabel labelDown = new JLabel();
    private JButton buttonOk = new JButton("Add !");


    public AdminAddTheme(AdminView frame){

        this.frame = frame;

        fieldTheme.setPreferredSize(new Dimension(150, 30));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.add(panelUp);
        this.add(panelTheme);
        this.add(panelButton);
        this.add(panelDown);

        panelUp.add(labelUp);
        panelTheme.add(labelTheme);
        panelTheme.add(fieldTheme);
        panelButton.add(buttonOk);
        panelDown.add(labelDown);

        buttonOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        boolean result = frame.getDico().put(fieldTheme.getText());
                        labelDown.setText("Hey ! Wait a minute.. The functionality is not done yet");
                    }
                }
        );



    }
}
