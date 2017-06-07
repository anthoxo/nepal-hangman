package Views;

import Controllers.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by helene on 06/06/17.
 */
public class ViewMenu extends JFrame {
    Menu menu;

    JPanel corps = new JPanel();
    JPanel panelBtn = new JPanel();

    panelGraphics pan = new panelGraphics();

    JButton btnSubmit = new JButton(new BtnSubmitAction());

    public ViewMenu(){
        this.menu = new Menu();
        this.menu.addView(this);

        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        corps.setLayout(new BorderLayout());
//        corps.add(pan, BorderLayout.CENTER);
//        corps.add(btnSubmit,BorderLayout.CENTER);
//        this.add(corps,BorderLayout.CENTER);

        corps.setLayout(new BorderLayout());
        //Add the background
        corps.add(pan);
        //Add buttons
        btnSubmit.setSize(50,50);
        panelBtn.setLayout(new BorderLayout());
        panelBtn.add(btnSubmit, BorderLayout.CENTER);
        corps.add(panelBtn, BorderLayout.SOUTH);
        this.setContentPane(corps);
    }

    public class BtnSubmitAction extends AbstractAction {
        public BtnSubmitAction(){
            putValue(SMALL_ICON, new ImageIcon(ViewBasic.class.getResource("/Icons/submit16.png")));
            putValue(LARGE_ICON_KEY, new ImageIcon(ViewBasic.class.getResource("/Icons/submit32.png")));
            putValue(NAME, "Submit");
            putValue(SHORT_DESCRIPTION, "Submit your choice");
        }

        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class panelGraphics extends JPanel {
        public panelGraphics(){
            super();
        }

        public void paintComponent(Graphics g) {
            try {
                //Background
                FileInputStream input = new FileInputStream(new File("src/Icons/background.jpg"));
                Image img = ImageIO.read(input);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                
                //Welcoming text
                Font font = new Font("Courier",Font.BOLD,12);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("Welcome to the Hangman game made for Lubra's school !",this.getWidth()/2-200,50);

                //Button

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        JFrame view = new ViewMenu();
        view.setVisible(true);
    }
}
