package Admin;

import Models.Dictionary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminView extends JFrame {

    Dictionary dico = new Dictionary("dictionary.txt","themes.txt");
    JPanel corps = new JPanel();

    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuWord = new JMenu("Word");
    JMenu menuTheme = new JMenu("Theme");

    JMenuItem menuQuit = new JMenuItem("Quit");
    JMenuItem menuAddWord = new JMenuItem("Add word");
    JMenuItem menuDeleteWord = new JMenuItem("Delete word");
    JMenuItem menuDeleteTheme = new JMenuItem("Delete theme");


    public AdminView(){

        this.setTitle("Hangman : Administration");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(corps);

        this.menuFile.add(menuQuit);
        this.menuWord.add(menuAddWord);
        this.menuWord.add(menuDeleteWord);
        this.menuTheme.add(menuDeleteTheme);

        this.menuBar.add(menuFile);
        this.menuBar.add(menuWord);
        this.menuBar.add(menuTheme);
        this.setJMenuBar(menuBar);


        this.menuQuit.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            }
        );
        this.menuAddWord.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    AdminView.this.changePane(new AdminAddWord(AdminView.this));
                }
            }
        );


    }
    public void changePane(JPanel panel){
        this.setContentPane(panel);
        this.revalidate();
    }

    public Dictionary getDico(){
        return this.dico;
    }

    public static void main(String[] args){
        JFrame t = new AdminView();
        t.setVisible(true);
    }
}
