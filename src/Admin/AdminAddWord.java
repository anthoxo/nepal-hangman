package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminAddWord extends JPanel {

    private AdminView frame;

    private JPanel panelUp = new JPanel();
    private JPanel panelWord = new JPanel();
    private JPanel panelTheme = new JPanel();
    private JPanel panelDefinition = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelDown = new JPanel();

    private JTextField fieldWord = new JTextField();
    private JTextField fieldTheme = new JTextField();
    private JTextField fieldDefinition = new JTextField();

    private JLabel labelUp = new JLabel("Add a word in the dictionary");
    private JLabel labelWord = new JLabel("Word : ");
    private JLabel labelTheme = new JLabel("Theme : ");
    private JLabel labelDefinition = new JLabel("Definition : ");
    private JLabel labelDown = new JLabel();
    private JButton buttonOk = new JButton("Add !");


    public AdminAddWord(AdminView frame){

        this.frame = frame;

        fieldWord.setPreferredSize(new Dimension(150, 30));
        fieldTheme.setPreferredSize(new Dimension(150, 30));
        fieldDefinition.setPreferredSize(new Dimension(150, 60));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.add(panelUp);
        this.add(panelWord);
        this.add(panelTheme);
        this.add(panelDefinition);
        this.add(panelButton);
        this.add(panelDown);

        panelUp.add(labelUp);
        panelWord.add(labelWord);
        panelWord.add(fieldWord);
        panelTheme.add(labelTheme);
        panelTheme.add(fieldTheme);
        panelDefinition.add(labelDefinition);
        panelDefinition.add(fieldDefinition);
        panelButton.add(buttonOk);
        panelDown.add(labelDown);

        buttonOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.getDico().put(fieldWord.getText(),fieldTheme.getText(),fieldDefinition.getText());
                        labelDown.setText(fieldWord.getText()+" is added in the dictionary !");
                    }
                }
        );



    }
}
