package Controllers;

import Views.ViewMenu;

/**
 * Created by helene on 06/06/17.
 */
public class Menu {
    protected String theme;
    protected int strokes;
    protected String mode; //Yes if we can display letters used, No if we can't
    protected int nbWords; //-1 in mode survival
    protected ViewMenu view;

    public Menu(){
        this.theme = "Mix";
        this.strokes = 0;
        this.mode = "Yes";
        this.nbWords = 0;
    }

    public Menu(String theme, int strokes, String mode, int nbWords){
        this.theme = theme;
        this.strokes = strokes;
        this.mode = mode;
        this.nbWords = nbWords;
    }

    public Menu(Menu m){
        this.theme = m.theme;
        this.strokes = m.strokes;
        this.mode = m.mode;
        this.nbWords = m.nbWords;
    }

    public String getMode() {
        return mode;
    }

    public int getNbStrokes() {
        return strokes;
    }

    public String getTheme() {
        return theme;
    }

    public int getNbWords() {
        return nbWords;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setStrokes(int Strokes){
        this.strokes = Strokes;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setNbWords(int nbWords) {
        this.nbWords = nbWords;
    }

    public void addView(ViewMenu view){
        this.view = view;
    }

    public void decreaseWord(){
        this.nbWords --;
    }
}
