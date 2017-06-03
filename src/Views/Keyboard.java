package Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Keyboard extends JPanel {
    char[] alphabet = "azertyuiopqsdfghjklmwxcvbn".toCharArray();
    ArrayList<JButton> buttonAlphabet = new ArrayList();

    public Keyboard(){
        this.setSize(100,30);
        this.setLayout(new GridLayout(3,10));
        for (int i = 0 ; i<26 ; i++){
            JButton button = new JButton(String.valueOf(this.alphabet[i]));
            buttonAlphabet.add(i,button);
            this.add(button);
        }
        for (int i = 0 ; i<4 ; i++){
            this.add(new JButton(""));
        }
    }

}
