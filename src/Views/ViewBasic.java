package Views;
import Controllers.*;
import Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ViewBasic extends JFrame implements ActionListener{
    Keyboard keyboard = new Keyboard();
    //test
    Controller controller = new Controller(new Word("PASTA", "Food", "Pasta for me",10));

    JPanel corps = new JPanel();
    //JPanel up = new JPanel();
    JLabel label = new JLabel();
    //JLabel labelUp = new JLabel();
    JLabel lblThemeLabel = new JLabel(this.controller.getMainW().getTheme());
    PanelBackground pan = new PanelBackground();

    JToolBar toolBar = new JToolBar();
    JSeparator separator = new JSeparator();
    JSeparator separator_1 = new JSeparator();
    JButton btnMenu = new JButton(new BtnMenuAction());
    JButton btnQuit = new JButton(new BtnQuitAction());

    public ViewBasic(){
        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(btnMenu);
        toolBar.add(separator);
        toolBar.add(lblThemeLabel);
        toolBar.add(separator_1);
        toolBar.add(btnQuit);

        for (int i = 0 ; i<26 ; i++){
            keyboard.getButtonAlphabet().get(i).addActionListener(this);
        }
        corps.setBackground(Color.yellow);
        //up.setBackground(Color.cyan);

        //labelUp.setText("THEME : ");

        corps.setLayout(new BorderLayout());
        corps.add(pan, BorderLayout.CENTER);
        //this.setContentPane(new ImagePanel());

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

    /**
     * Action to do when the player clicks on the button "Menu".
     */
    public class BtnMenuAction extends AbstractAction {
        public BtnMenuAction(){
            putValue(SMALL_ICON, new ImageIcon(ViewBasic.class.getResource("/Icons/home16.png")));
            putValue(LARGE_ICON_KEY, new ImageIcon(ViewBasic.class.getResource("/Icons/home32.png")));
            putValue(NAME, "Menu");
            putValue(SHORT_DESCRIPTION, "Return to the menu");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int res = JOptionPane.showConfirmDialog
                    (null, "Return to the menu ?","Menu", JOptionPane.YES_NO_OPTION);
            if (res == 0)
                System.out.println("Menu"); //delete after
                //retour au menu
        }
    }

    /**
     * Actions to do when the player clicks on the button "Quit".
     */
    public class BtnQuitAction extends AbstractAction {
        public BtnQuitAction(){
            putValue(SMALL_ICON, new ImageIcon(ViewBasic.class.getResource("/Icons/quit16.png")));
            putValue(LARGE_ICON_KEY, new ImageIcon(ViewBasic.class.getResource("/Icons/quit32.png")));
            putValue(NAME, "Quit");
            putValue(SHORT_DESCRIPTION, "Quit the game");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int res = JOptionPane.showConfirmDialog(
                    null, "Do you really want to quit the game ?",
                    "Quit the game", JOptionPane.YES_NO_OPTION);
            if (res == 0)
                dispose();
        }
    }

    /**
     * Graphic part
     */
    public class PanelBackground extends JPanel {
        private String path;

        public PanelBackground(){
            super();
        }

        public void paintComponent(Graphics g) {
            try {
                FileInputStream input = new FileInputStream(new File("src/Icons/background.jpg"));
                FileInputStream input2 = new FileInputStream(new File("src/Icons/hangman.jpg"));

                Image img = ImageIO.read(input);
                Image img2 = ImageIO.read(input2);

                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                g.drawImage(img2, 15, this.getHeight()/2-125, this);

            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}
