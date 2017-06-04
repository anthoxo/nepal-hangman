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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar();
        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        JButton btnMenu = new JButton(new BtnMenuAction("Return menu"));
        toolBar.add(btnMenu);

        JSeparator separator = new JSeparator();
        toolBar.add(separator);

        JLabel lblThemeLabel = new JLabel("Theme");
        toolBar.add(lblThemeLabel);

        JSeparator separator_1 = new JSeparator();
        toolBar.add(separator_1);

        JButton btnQuit = new JButton(new BtnQuitAction());
        toolBar.add(btnQuit);

        for (int i = 0 ; i<25 ; i++){
            keyboard.getButtonAlphabet().get(i).addActionListener(this);
        }
        corps.setBackground(Color.yellow);

        this.add(corps, BorderLayout.CENTER);
        this.add(keyboard, BorderLayout.SOUTH);
        corps.add(label);
    }

    public void actionPerformed(ActionEvent arg0){
        JButton button = (JButton) arg0.getSource();
        this.label.setText(button.getText());
    }

    public class BtnMenuAction extends AbstractAction {
        public BtnMenuAction(String text){
            super(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //retour au menu
        }
    }

    public class BtnQuitAction extends AbstractAction {
        public BtnQuitAction(){
            putValue(SMALL_ICON, new ImageIcon(ViewBasic.class.getResource("/Icons/quit16.png")));
            putValue(LARGE_ICON_KEY, new ImageIcon(ViewBasic.class.getResource("/Icons/quit32.png")));
            putValue(NAME, "Quit");
            putValue(SHORT_DESCRIPTION, "Quit the game");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //quitter
        }
    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}
