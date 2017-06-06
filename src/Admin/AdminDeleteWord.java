package Admin;

import Models.Dictionary;
import Models.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class AdminDeleteWord extends JPanel {

    private AdminView frame;
    private Word word;

    private JPanel panelUp = new JPanel();
    private JPanel panelWord = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelDown = new JPanel();

    private JComboBox listWord = new JComboBox();

    private JLabel labelUp = new JLabel("Delete a word from the dictionary");
    private JLabel labelWord = new JLabel("Word : ");
    private JLabel labelCenter = new JLabel();
    private JLabel labelDown = new JLabel();
    private JButton buttonOk = new JButton("Delete !");


    public AdminDeleteWord(AdminView frame){

        this.frame = frame;
        Dictionary dico = frame.getDico();
        Enumeration tab = dico.getWords().elements();
        while (tab.hasMoreElements()){
            listWord.addItem(tab.nextElement().toString());
        }
        int keys = -1;
        tab = dico.getWords().keys();
        while (tab.hasMoreElements() && keys==-1){
            int tmp = (int) tab.nextElement();
            if (listWord.getSelectedItem().equals(dico.getWords().get(tmp))){
                keys = tmp;
            }
        }
        word = new Word(dico.getWords().get(keys),
                dico.getThemes().get(dico.getThemesIndex().get(keys)),
                dico.getDefinitions().get(keys),2);

        listWord.setPreferredSize(new Dimension(150, 30));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.add(panelUp);
        this.add(panelWord);
        this.add(panelCenter);
        this.add(panelButton);
        this.add(panelDown);

        panelUp.add(labelUp);
        panelWord.add(labelWord);
        panelWord.add(listWord);
        panelCenter.add(labelCenter);
        panelButton.add(buttonOk);
        panelDown.add(labelDown);

        labelCenter.setText(word.getWord()+" - "+word.getTheme());

        listWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int keys = -1;
                Dictionary dico = frame.getDico();
                Enumeration tab = dico.getWords().keys();
                while (tab.hasMoreElements() && keys==-1){
                    int tmp = (int) tab.nextElement();
                    if (listWord.getSelectedItem().equals(dico.getWords().get(tmp))){
                        keys = tmp;
                    }
                }
                word = new Word(dico.getWords().get(keys),
                        dico.getThemes().get(dico.getThemesIndex().get(keys)),
                        dico.getDefinitions().get(keys),2);

                labelCenter.setText(word.getWord()+" - "+word.getTheme());
            }
        });

        buttonOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        boolean result = frame.getDico().delete(word);
                        if (result){
                            labelDown.setText(word.getWord()+" has been removing from the dictionary.");
                        }
                        else{
                            labelDown.setText("There is a problem with removing "+word.getWord()+"...");
                        }
                    }
                }
        );

    }


}
