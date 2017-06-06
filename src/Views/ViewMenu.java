package Views;

import Controllers.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by helene on 06/06/17.
 */
public class ViewMenu extends JFrame {
    Menu menu;

    JPanel corps = new JPanel();

    JLabel lblThemeLabel = new JLabel();
    PanelImage pan = new PanelImage();

    JToolBar toolBarNorth = new JToolBar();
    JToolBar toolBarSouth = new JToolBar();
    JSeparator separator = new JSeparator();
    JButton btnSubmit = new JButton(new BtnSubmitAction());

    public ViewMenu(){
        this.menu = new Menu();
        this.menu.addView(this);

        this.setTitle("Hangman game");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblThemeLabel.setText("Welcome to the Hangman game made for Lubra's school!");
        this.getContentPane().add(toolBarNorth, BorderLayout.NORTH);
        toolBarNorth.add(separator);
        toolBarNorth.add(lblThemeLabel);

        corps.setLayout(new BorderLayout());
        corps.add(pan, BorderLayout.CENTER);

        this.getContentPane().add(toolBarSouth, BorderLayout.SOUTH);
        toolBarSouth.add(separator);
        toolBarSouth.add(btnSubmit);
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

    public class PanelImage extends JPanel {
        private String path;

        public PanelImage(){
            super();
        }

        public void paintComponent(Graphics g) {
            try {
                FileInputStream input = new FileInputStream(new File("src/Icons/background.jpg"));

                Image img = ImageIO.read(input);

                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

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
