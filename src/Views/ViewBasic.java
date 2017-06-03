package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ViewBasic extends JFrame {
    
    public ViewBasic(){
        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);

        JPanel keyboard = new Keyboard();
        JPanel corps = new JPanel();
        corps.setBackground(Color.red);
        this.add(corps, BorderLayout.CENTER);
        this.add(keyboard, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent arg0){

    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}
