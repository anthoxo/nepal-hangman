package Views;

import Controllers.Controller;
import Models.Dictionary;
import Controllers.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ViewBasic extends JFrame implements ActionListener{
    Controller controller;
    int nbInitStrokes = 0;
    Menu menu;
    int nbStrokesAllowed; //to start a new game with the same number of Strokes chosen in the menu

    Keyboard keyboard = new Keyboard();

    PanelImage corps = new PanelImage("src/Icons/background.jpg");
    JPanel panelLetters = new JPanel();
    JPanel panelUpLetters = new JPanel(); //to center panelDownLetters
    JPanel panelDownLetters = new JPanel();
    JPanel panelHangman = new JPanel();
    JPanel panelUp = new JPanel();

    JTextArea labelLetters = new JTextArea();
    JTextArea labelUp = new JTextArea();
    JLabel lblThemeLabel = new JLabel();
    JLabel labelHangman = new JLabel();

    JToolBar toolBar = new JToolBar();
    JSeparator separator = new JSeparator();
    JSeparator separator_1 = new JSeparator();
    JButton btnMenu = new JButton(new BtnMenuAction());
    JButton btnQuit = new JButton(new BtnQuitAction());

    public ViewBasic(Menu menu){
        Dictionary d = new Dictionary("dictionary.txt","themes.txt");
        d.fill();
        this.menu = new Menu(menu);
        nbStrokesAllowed = menu.getNbStrokes();

        this.nbInitStrokes = menu.getNbStrokes();
        if (menu.getTheme() == "Mix")
            this.controller = new Controller(d,this.nbInitStrokes);
        else
            this.controller = new Controller(d,d.getIndexTheme(menu.getTheme()),this.nbInitStrokes);

        this.controller.addView(this);

        lblThemeLabel.setText(this.controller.getMainW().getTheme());

        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(toolBar);
        toolBar.add(btnMenu);
        toolBar.add(separator);
        toolBar.add(lblThemeLabel);
        toolBar.add(separator_1);
        toolBar.add(btnQuit);

        for (int i = 0 ; i<26 ; i++){
            keyboard.getButtonAlphabet().get(i).addActionListener(this);
        }

        corps.setLayout(new BorderLayout());

        this.add(corps);
        this.add(keyboard);

        panelUp.setOpaque(false);
        panelLetters.setOpaque(false);
        panelHangman.setOpaque(false);
        panelUpLetters.setOpaque(false);
        panelDownLetters.setOpaque(false);

        labelUp.setEditable(false);
        labelUp.setOpaque(false);
        labelLetters.setEditable(false);
        labelLetters.setOpaque(false);
        labelUp.setFont(new Font("Lato",1,15));
        labelLetters.setFont(new Font("Lato",1,15));

        corps.add(panelUp,BorderLayout.NORTH);
        corps.add(panelLetters, BorderLayout.EAST);
        corps.add(panelHangman,BorderLayout.WEST);

        panelLetters.add(panelUpLetters);
        panelLetters.add(panelDownLetters);
        panelDownLetters.add(labelLetters);
        panelUp.add(labelUp);
        panelHangman.add(labelHangman);

        labelHangman.setIcon(new ImageIcon("src/Icons/hangman/0.png"));

        this.labelLetters.setAlignmentY(JTextArea.RIGHT_ALIGNMENT);

        /**
         * size of each component
         */
        corps.setPreferredSize(new Dimension(500,350));
        panelUp.setPreferredSize(new Dimension( 500, 75 ));
        panelLetters.setPreferredSize(new Dimension(200,500));
        panelUpLetters.setPreferredSize(new Dimension(200,75));
        panelHangman.setPreferredSize(new Dimension(300,500));

        this.refresh("Good luck !");
    }

    public void actionPerformed(ActionEvent arg0){
        JButton button = (JButton) arg0.getSource();
        this.controller.run(button.getText().charAt(0),menu);
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
            Object[] options = { "Yes", "No" };

            int res = JOptionPane.showOptionDialog(null, "Return to the menu ?","Menu",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(ViewBasic.class.getResource("/Icons/question32.png")), options, options[0]);
            if (res == 0){
                dispose();
                ViewMenu menuV = new ViewMenu();
                menuV.launch();
            }
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
            Object[] options = { "Yes", "No" };

            int res = JOptionPane.showOptionDialog(null, "Do you really want to quit the game ?",
                    "Quit",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(ViewBasic.class.getResource("/Icons/question32.png")),options,options[0]);
            if (res == 0)
                dispose();
        }
    }

    /**
     * Graphic part
     */
    public class PanelImage extends JPanel {

        String path;

        public PanelImage(String path){
            super();
            this.path = path;
        }

        public void paintComponent(Graphics g) {
            try {
                FileInputStream input = new FileInputStream(new File(this.path));
                Image img = ImageIO.read(input);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refresh(String state){
        String result = "";
        int img = this.nbInitStrokes - this.controller.getMainW().getNbStrokes();
        if (this.nbInitStrokes == 4){
            img *= 2;
        }
        String path = "src/Icons/hangman/"+String.valueOf(img)+".png";
        Iterator it = this.controller.getLettersFound().iterator();
        while (it.hasNext()){
            result += (Character)it.next();
            if (it.hasNext()){
                result += " ";
            }
        }
        this.labelLetters.setText(result);
        this.labelUp.setText(state);
        this.labelHangman.setIcon(new ImageIcon(path));
    }

    public void printVictory(boolean victory){
        JOptionPane optPane = new JOptionPane();
        Object[] options = { "New game", "Menu","Quit" };
        int res = -1;

        if (victory){
            String message = "You find ";
            message += this.controller.getMainW().getWord()+" !\n";
            message += "Description : "+this.controller.getMainW().getDefinition();
            if (menu.getNbWords()==0){
                String score;
                int total;
                total = menu.getVictory() + menu.getFailure();
                score = "\nYour score : " + menu.getVictory() + "/" + total;
                res=optPane.showOptionDialog(null, message + score,"Congratulation !",JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
            }
            else
                optPane.showMessageDialog(null,message,"Congratulation !",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String message = "You don't find ";
            message += this.controller.getMainW().getWord()+" ...\n";
            message += "Description : "+this.controller.getMainW().getDefinition()+"\n";
            if (menu.getNbWords()<1) { //include the case of number of Words fixed and the Survival mode
                String score;
                if (menu.getNbWords()==0){
                    int total;
                    total = menu.getVictory() + menu.getFailure();
                    score = "\nYour score : " + menu.getVictory() + "/" + total;
                }
                else
                    score = "\nYour score : " + menu.getVictory();

                res=optPane.showOptionDialog(null, message + score,"You lose ...",JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
            }
            else
                optPane.showMessageDialog(null,message,"You lose...",JOptionPane.ERROR_MESSAGE);
        }

        dispose();
        //There are still words or "New Game" option
        if (res==-1 || res==0){
            if (res==0)
                menu.setNbWords(nbStrokesAllowed);
//            System.out.println("nbStrokesAllowed" + menu.getNbWords());
            ViewBasic v = new ViewBasic(menu);
            v.launch(menu);
        }
        //"Menu" option
        else if (res==1){
            ViewMenu v = new ViewMenu();
            v.launch();
        }

    }

     public void launch(Menu menu){
        JFrame t = new ViewBasic(menu);
        t.setVisible(true);
    }
}