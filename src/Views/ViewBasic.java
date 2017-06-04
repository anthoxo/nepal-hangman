package Views;
import Controllers.*;
import Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ViewBasic extends JFrame implements ActionListener{

    JPanel corps = new JPanel();
    JPanel up = new JPanel();
    JLabel label = new JLabel();
    //JLabel labelUp = new JLabel();

    Keyboard keyboard = new Keyboard();
    Controller controller;

    public ViewBasic(){
        //test
        this.controller = new Controller(new Word("PASTA", "Food", "Pasta for me",10));

        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar();
        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        JButton btnMenu = new JButton(new BtnMenuAction());
        toolBar.add(btnMenu);

        JSeparator separator = new JSeparator();
        toolBar.add(separator);

        JLabel lblThemeLabel = new JLabel(this.controller.getMainW().getTheme());
        toolBar.add(lblThemeLabel);

        JSeparator separator_1 = new JSeparator();
        toolBar.add(separator_1);

        JButton btnQuit = new JButton(new BtnQuitAction());
        toolBar.add(btnQuit);

        for (int i = 0 ; i<26 ; i++){
            keyboard.getButtonAlphabet().get(i).addActionListener(this);
        }
        corps.setBackground(Color.yellow);
        //this.setContentPane(new ImagePanel());

        //up.setBackground(Color.cyan);

        //labelUp.setText("THEME : ");

        this.add(corps, BorderLayout.CENTER);
        this.add(keyboard, BorderLayout.SOUTH);
        //this.add(up, BorderLayout.NORTH);

        //up.add(labelUp, BorderLayout.CENTER);
        //corps.add(label, BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent arg0){
        JButton button = (JButton) arg0.getSource();
        this.controller.run(button.getText().charAt(0));
        this.label.setText(button.getText());
    }
    public class BtnMenuAction extends AbstractAction {
        public BtnMenuAction(){
            putValue(SMALL_ICON, new ImageIcon(ViewBasic.class.getResource("/Icons/home16.png")));
            putValue(LARGE_ICON_KEY, new ImageIcon(ViewBasic.class.getResource("/Icons/home32.png")));
            putValue(NAME, "Menu");
            putValue(SHORT_DESCRIPTION, "Return to the menu");
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

    public class ImagePanel extends JPanel {

        public void paintComponent(Graphics g) {
//            try {
//                Image img = ImageIO.read(new File("/Icons/background.jpg"));
//                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
//            }
//             catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}
