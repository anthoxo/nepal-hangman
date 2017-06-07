package Admin;

import Models.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;


public class AdminAddWord extends JPanel {

    private AdminView frame;
    private int key;

    private JPanel panelUp = new JPanel();
    private JPanel panelWord = new JPanel();
    private JPanel panelTheme = new JPanel();
    private JPanel panelDefinition = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelDown = new JPanel();

    private JTextField fieldWord = new JTextField();
    private JComboBox listThemes = new JComboBox();
    private JTextField fieldDefinition = new JTextField();

    private JLabel labelUp = new JLabel("Add a word in the dictionary");
    private JLabel labelWord = new JLabel("Word : ");
    private JLabel labelTheme = new JLabel("Theme : ");
    private JLabel labelDefinition = new JLabel("Definition : ");
    private JLabel labelDown = new JLabel();
    private JButton buttonOk = new JButton("Add !");


    public AdminAddWord(AdminView frame){

        this.frame = frame;
        Dictionary dico = frame.getDico();

        fieldWord.setPreferredSize(new Dimension(150, 30));
        listThemes.setPreferredSize(new Dimension(150, 30));
        fieldDefinition.setPreferredSize(new Dimension(200, 30));

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
        panelTheme.add(listThemes);
        panelDefinition.add(labelDefinition);
        panelDefinition.add(fieldDefinition);
        panelButton.add(buttonOk);
        panelDown.add(labelDown);

        Enumeration tab = dico.getThemes().elements();
        while (tab.hasMoreElements()){
            listThemes.addItem(tab.nextElement().toString());
        }
        this.key = -1;
        tab = dico.getThemes().keys();
        while (tab.hasMoreElements() && key==-1){
            int tmp = (int) tab.nextElement();
            if (listThemes.getSelectedItem().equals(dico.getThemes().get(tmp))){
                key = tmp;
            }
        }


        listThemes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                key=-1;
                Dictionary dico = frame.getDico();
                Enumeration tab = dico.getThemes().keys();
                while (tab.hasMoreElements() && key==-1){
                    int tmp = (int) tab.nextElement();
                    if (listThemes.getSelectedItem().equals(dico.getThemes().get(tmp))){
                        key = tmp;
                    }
                }
            }
        });

        buttonOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String word = fieldWord.getText().toUpperCase();
                        boolean result = frame.getDico().put(word,(String)listThemes.getSelectedItem(),fieldDefinition.getText());
                        if (result){
                            labelDown.setText(word+" is added in the dictionary !");
                        }
                        else{
                            labelDown.setText("There is a problem for adding "+word+" in the dictionary...");
                        }
                    }
                }
        );



    }
}
