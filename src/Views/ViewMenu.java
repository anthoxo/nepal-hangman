package Views;

import Controllers.Menu;
import Models.Dictionary;

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
    Dictionary d = new Dictionary("dictionary.txt","themes.txt");

    JPanel corps = new JPanel();
    JPanel panTheme = new JPanel();
    JPanel panNbStrokes = new JPanel();
    JPanel panMode = new JPanel();
    JPanel panSubmit = new JPanel();
    JPanel list = new JPanel();

    JLabel lblTheme = new JLabel("Theme");
    JLabel lblNbStrokes = new JLabel("Number of Strokes");
    JLabel lblMode = new JLabel("Mode");

    JComboBox<String> theme = new JComboBox<String>();
    JComboBox<String> mode = new JComboBox<String>();

    JRadioButton nbStrokes1 = new JRadioButton("6");
    JRadioButton nbStrokes2 = new JRadioButton("9");

    JButton btnSubmit = new JButton(new BtnSubmitAction());

    panelGraphics pan = new panelGraphics();

    public ViewMenu(){
        this.menu = new Menu();
        this.menu.addView(this);
        d.fill();

        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //list contains all options list and the Submit button
        list.setLayout(new BoxLayout(list,BoxLayout.PAGE_AXIS));
        list.setOpaque(false);

        panTheme.setOpaque(false);
        panNbStrokes.setOpaque(false);
        panMode.setOpaque(false);
        panSubmit.setOpaque(false);

        //Size of option list
//        theme.setPreferredSize(new Dimension(100, 20));
//        nbStrokes.setPreferredSize(new Dimension(100, 20));
//        mode.setPreferredSize(new Dimension(100, 20));

        //Option list for Theme
        theme.addItem("Mix");
        for (int i=0 ; i<d.getThemes().size() ; i++){
            theme.addItem(d.getThemes().get(d.getThemesIndex().get(i)));
        }
        panTheme.add(lblTheme);
        panTheme.add(theme);

        //Option list for nbStrokesAllowed
        nbStrokes1.setOpaque(false);
        nbStrokes2.setOpaque(false);
        panNbStrokes.add(lblNbStrokes);
        panNbStrokes.add(nbStrokes1);
        panNbStrokes.add(nbStrokes2);

        //Option list for Mode
        mode.addItem("Normal");
        mode.addItem("Advanced");
        panMode.add(lblMode);
        panMode.add(mode);

        //Button Submit
        panSubmit.add(btnSubmit);

        list.add(panTheme);
        list.add(panNbStrokes);
        list.add(panMode);
        list.add(panSubmit);
        pan.add(list,BorderLayout.CENTER);

        this.setContentPane(pan);

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
