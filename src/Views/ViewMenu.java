package Views;

import Controllers.Menu;
import Models.Dictionary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by helene on 06/06/17.
 */
public class ViewMenu extends JFrame {
    Menu menu = new Menu();
    Dictionary d = new Dictionary("dictionary.txt","themes.txt");

    PanelGraphics pan = new PanelGraphics();
    JPanel panWelcome = new JPanel();
    JPanel panTheme = new JPanel();
    JPanel panNbStrokes = new JPanel();
    JPanel panMode = new JPanel();
    JPanel panWord = new JPanel();
    JPanel panSubmit = new JPanel();
    JPanel list = new JPanel();

    JLabel lblWelcome = new JLabel("Welcome to the Hangman Game!");
    JLabel lblTheme = new JLabel("Theme");
    JLabel lblNbStrokes = new JLabel("Number of Strokes       ");
    JLabel lblMode = new JLabel("Display letters played ?   ");
    JLabel lblWord = new JLabel("Number of words to guess ?   ");

    JComboBox<String> theme = new JComboBox<String>();
    ButtonGroup groupS = new ButtonGroup();
    ButtonGroup groupM = new ButtonGroup();
    ButtonGroup groupW = new ButtonGroup();
    JRadioButton nbStrokes1 = new JRadioButton("4");
    JRadioButton nbStrokes2 = new JRadioButton("8",null,true);
    JRadioButton modeY = new JRadioButton("Yes",null,true);
    JRadioButton modeN = new JRadioButton("No");
    JRadioButton nbWord10 = new JRadioButton("10");
    JRadioButton nbWord20 = new JRadioButton("20");
    JRadioButton nbWordSurvival = new JRadioButton("Survival",null,true);

    JButton btnSubmit = new JButton(new BtnSubmitAction());
    
    public ViewMenu(){
        this.menu = new Menu("Mix",8,"Yes",-1);
        this.menu.addView(this);
        d.fill();

        this.setTitle("Hangman game");
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //list contains all options list and the Submit button
        list.setLayout(new BoxLayout(list,BoxLayout.PAGE_AXIS));
        list.setOpaque(false);

        panWelcome.setOpaque(false);
        panTheme.setOpaque(false);
        panNbStrokes.setOpaque(false);
        panMode.setOpaque(false);
        panWord.setOpaque(false);
        panSubmit.setOpaque(false);

        panWelcome.add(lblWelcome);
        //Size of option list
//        theme.setPreferredSize(new Dimension(100, 20));

        //Option list for Theme
        theme.addItem("Mix");
        for (int i=0 ; i<d.getThemes().size() ; i++){
            theme.addItem(d.getThemes().get(d.getThemesIndex().get(i)));
        }
        theme.addItemListener(new ItemStateTheme());
        panTheme.add(lblTheme);
        panTheme.add(theme);

        //Options for nbStrokesAllowed
        nbStrokes1.setOpaque(false);
        nbStrokes2.setOpaque(false);
        groupS.add(nbStrokes1);
        groupS.add(nbStrokes2);
        nbStrokes1.addActionListener(new StateListenerStrokes());
        nbStrokes2.addActionListener(new StateListenerStrokes());
        panNbStrokes.add(lblNbStrokes);
        panNbStrokes.add(nbStrokes1);
        panNbStrokes.add(nbStrokes2);

        //Options for Mode
        modeY.setOpaque(false);
        modeN.setOpaque(false);
        groupM.add(modeY);
        groupM.add(modeN);
        modeY.addActionListener(new StateListenerMode());
        modeN.addActionListener(new StateListenerMode());
        panMode.add(lblMode);
        panMode.add(modeY);
        panMode.add(modeN);

        //Options for number of words
        nbWord10.setOpaque(false);
        nbWord20.setOpaque(false);
        nbWordSurvival.setOpaque(false);
        groupW.add(nbWord10);
        groupW.add(nbWord20);
        groupW.add(nbWordSurvival);
        nbWord10.addActionListener(new StateListenerWord());
        nbWord20.addActionListener(new StateListenerWord());
        nbWordSurvival.addActionListener(new StateListenerWord());
        panWord.add(lblWord);
        panWord.add(nbWord10);
        panWord.add(nbWord20);
        panWord.add(nbWordSurvival);

        //Button Submit
        panSubmit.add(btnSubmit);

        list.add(panWelcome);
        list.add(panTheme);
        list.add(panNbStrokes);
        list.add(panMode);
        list.add(panWord);
        list.add(panSubmit);
        pan.add(list);

        this.setContentPane(pan);

    }

    public void launch(){
        JFrame v = new ViewMenu();
        v.setVisible(true);
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
            dispose();
            ViewBasic v = new ViewBasic(menu);
            v.launch(menu);
        }
    }

    public class PanelGraphics extends JPanel {
        public PanelGraphics(){
            super();
        }

        public void paintComponent(Graphics g) {
            try {
                //Background
                FileInputStream input = new FileInputStream(new File("src/Icons/background.jpg"));
                Image img = ImageIO.read(input);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                
                //Welcoming text
//                Font font = new Font("Lato",Font.BOLD,13);
//                g.setFont(font);
//                g.setColor(Color.black);
//                g.drawString("Welcome to the Hangman game made for Lubra's school !",this.getWidth()/2-200,50);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public class ItemStateTheme implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            System.out.println("Theme changed in "+e.getItem());
            menu.setTheme((String)e.getItem());
        }
    }

    public class StateListenerStrokes implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JRadioButton tmp = (JRadioButton)e.getSource();
            if (tmp.getText() == "4"){
                System.out.println("NbStrokes : "+tmp.getText());
                menu.setStrokes(4);
            }
            else if (tmp.getText() == "8"){
                System.out.println("NbStrokes : "+tmp.getText());
                menu.setStrokes(8);
            }
        }
    }

    public class StateListenerMode implements ActionListener {
        public void actionPerformed(ActionEvent e){
            menu.setMode(((JRadioButton)e.getSource()).getText());
        }
    }

    public class StateListenerWord implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JRadioButton tmp = (JRadioButton)e.getSource();
            if (tmp.getText() == "10")
                menu.setNbWords(10);
            else if (tmp.getText() == "20")
                menu.setNbWords(20);
            else if (tmp.getText() == "Survival")
                menu.setNbWords(-1);
        }
    }
}
