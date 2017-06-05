package Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Keyboard extends JPanel {
    protected char[] alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    protected ArrayList<JButton> buttonAlphabet = new ArrayList(); //array of buttons

    /**
     * Constructor of keyboard
     */
    public Keyboard(){
        this.setSize(100,30);
        this.setLayout(new GridLayout(3,10,2,2));
        for (int i = 0 ; i<19 ; i++){
            JButton button = new JButton(String.valueOf(this.alphabet[i]));
            buttonAlphabet.add(i,button);
            this.add(button);
        }
        this.add(new JButton(""));
        for (int i = 0 ; i<7 ; i++){
            JButton button = new JButton(String.valueOf(this.alphabet[19+i]));
            buttonAlphabet.add(19+i,button);
            this.add(button);
        }
        for (int i = 0 ; i<3 ; i++){
            this.add(new JButton(""));
        }
    }

    /**
     *
     * @return the array of buttons
     */
    public ArrayList<JButton> getButtonAlphabet(){
        return this.buttonAlphabet;
    }

}
