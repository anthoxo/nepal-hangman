package Admin;

import Models.Dictionary;
import Models.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class AdminDeleteTheme extends JPanel{

    private AdminView frame;
    private Word word;
    private int keys;

    private JPanel panelUp = new JPanel();
    private JPanel panelTheme = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelDown = new JPanel();

    private JComboBox listTheme = new JComboBox();

    private JLabel labelUp = new JLabel("Delete a theme from the dictionary");
    private JLabel labelTheme = new JLabel("Theme : ");
    private JLabel labelDown = new JLabel();
    private JButton buttonOk = new JButton("Delete !");


    public AdminDeleteTheme(AdminView frame){

        this.frame = frame;
        Dictionary dico = frame.getDico();
        Enumeration tab = dico.getThemes().elements();
        while (tab.hasMoreElements()){
            listTheme.addItem(tab.nextElement().toString());
        }
        this.keys = -1;
        tab = dico.getThemes().keys();
        while (tab.hasMoreElements() && keys==-1){
            int tmp = (int) tab.nextElement();
            if (listTheme.getSelectedItem().equals(dico.getThemes().get(tmp))){
                keys = tmp;
            }
        }

        listTheme.setPreferredSize(new Dimension(150, 30));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.add(panelUp);
        this.add(panelTheme);
        this.add(panelButton);
        this.add(panelDown);

        panelUp.add(labelUp);
        panelTheme.add(labelTheme);
        panelTheme.add(listTheme);
        panelButton.add(buttonOk);
        panelDown.add(labelDown);


        listTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keys=-1;
                Dictionary dico = frame.getDico();
                Enumeration tab = dico.getThemes().keys();
                while (tab.hasMoreElements() && keys==-1){
                    int tmp = (int) tab.nextElement();
                    if (listTheme.getSelectedItem().equals(dico.getThemes().get(tmp))){
                        keys = tmp;
                    }
                }
            }
        });

        buttonOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        labelDown.setText("This function is not done yet, but you have chosen : "+frame.getDico().getThemes().get(keys));
                    }
                }
        );

    }

}
