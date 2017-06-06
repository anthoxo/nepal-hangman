package Views;
import Controllers.Controller;
import Models.Dictionary;
import Models.Word;

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

    Controller controller;
    Menu menu;

    JPanel corps = new JPanel();
    JPanel panelLetters = new JPanel();
    JPanel panelUp = new JPanel();

    JLabel labelLetters = new JLabel();
    JLabel labelUp = new JLabel();
    JLabel lblThemeLabel = new JLabel();
    PanelImage pan = new PanelImage();

    JToolBar toolBar = new JToolBar();
    JSeparator separator = new JSeparator();
    JSeparator separator_1 = new JSeparator();
    JButton btnMenu = new JButton(new BtnMenuAction());
    JButton btnQuit = new JButton(new BtnQuitAction());

    public ViewBasic(){
        Dictionary d = new Dictionary("dictionary.txt","themes.txt");
        d.fill();

        //test, delete when it's ok
        //int nbStrokes = menu.getNbStrokesAllowed();
        int nbStrokes = 10;

//        Décommenter une fois Menu implémentée
//        if (menu.theme == -1)
        this.controller = new Controller(d,nbStrokes);
//        else
//            this.controller = new Controller(d,menu.theme,nbStrokes);
        this.controller.addView(this);

        lblThemeLabel.setText(this.controller.getMainW().getTheme());

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

        corps.setLayout(new BorderLayout());
        corps.add(pan, BorderLayout.CENTER);
        panelUp.setBackground(Color.blue);
        this.add(corps, BorderLayout.CENTER);
        this.add(keyboard, BorderLayout.SOUTH);

        corps.add(panelLetters, BorderLayout.EAST);
        corps.add(panelUp,BorderLayout.NORTH);

        panelLetters.add(labelLetters,BorderLayout.CENTER);
        panelUp.add(labelUp,BorderLayout.CENTER);

        //size
        panelUp.setPreferredSize(new Dimension( 500, 50 ));


        this.refresh("Good luck !");
    }

    public void actionPerformed(ActionEvent arg0){
        JButton button = (JButton) arg0.getSource();
        this.controller.run(button.getText().charAt(0));
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
    public class PanelImage extends JPanel {
        private String path;

        public PanelImage(){
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

    public void refresh(String state){
        this.labelLetters.setText(this.controller.getLettersFound().toString());
        this.labelUp.setText(state);
    }

    public static void main(String[] args){
        JFrame t = new ViewBasic();
        t.setVisible(true);
    }
}