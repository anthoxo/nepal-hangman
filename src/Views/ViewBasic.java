package Views;
import Controllers.*;
import Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBasic extends JFrame implements ActionListener{

    JPanel corps = new JPanel();
    JLabel label = new JLabel();

    Keyboard keyboard = new Keyboard();
    Controller controller;

    public ViewBasic(){
        //test
        this.controller = new Controller(new Word("JAM", "Food", "Meat for me",10));

        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0 ; i<25 ; i++){
            keyboard.getButtonAlphabet().get(i).addActionListener(this);
        }
        corps.setBackground(Color.white);
        label.setBackground(Color.yellow);
        this.add(corps, BorderLayout.CENTER);
        this.add(keyboard, BorderLayout.SOUTH);
        corps.add(label, BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent arg0){
        JButton button = (JButton) arg0.getSource();
        //this.controller.run(button.getText().charAt(0));
        this.label.setText(button.getText());
    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}
