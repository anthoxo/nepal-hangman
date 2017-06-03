package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBasic extends JFrame implements ActionListener{

    JPanel corps = new JPanel();
    JLabel label = new JLabel();
    Keyboard keyboard = new Keyboard();

    public ViewBasic(){
        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);

        for (int i = 0 ; i<25 ; i++){
            keyboard.getButtonAlphabet().get(i).addActionListener(this);
        }
        corps.setBackground(Color.blue);

        this.add(corps, BorderLayout.CENTER);
        this.add(keyboard, BorderLayout.SOUTH);
        corps.add(label);
    }

    public void actionPerformed(ActionEvent arg0){
        JButton button = (JButton) arg0.getSource();
        this.label.setText(button.getText());
    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}
