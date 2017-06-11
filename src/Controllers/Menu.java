package Controllers;

import Views.ViewMenu;

/**
 * Created by helene on 06/06/17.
 */
public class Menu {
    protected String theme;
    protected int strokes;
    protected boolean mode; //Yes if we can display letters used, No if we can't
    protected int nbWords; //-1 in mode survival
    protected int victory; //number of victories
    protected int failure; //number of failure, allows to display the score
    int nbWordsChosen; //to start a new game, will be fixed
    protected ViewMenu view;

    public Menu(){
        this.theme = "Mix";
        this.strokes = 8;
        this.mode = true;
        this.nbWords = -1;
        this.victory = 0;
        this.failure = 0;
        this.nbWordsChosen = -1;
    }

    public Menu(String theme, int strokes, boolean mode,
                int nbWords, int nbVictory, int nbFailure,
                int nbWordsChosen){
        this.theme = theme;
        this.strokes = strokes;
        this.mode = mode;
        this.nbWords = nbWords;
        this.victory = nbVictory;
        this.failure = nbFailure;
        this.nbWordsChosen = nbWordsChosen;
    }

    public Menu(Menu m){
        this.theme = m.theme;
        this.strokes = m.strokes;
        this.mode = m.mode;
        this.nbWords = m.nbWords;
        this.victory = m.victory;
        this.failure = m.failure;
        this.nbWordsChosen = m.nbWordsChosen;
        this.view = m.view;
    }

    public boolean getMode() {
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

    public int getVictory(){
        return victory;
    }

    public int getFailure() {
        return failure;
    }

    public int getNbWordsChosen() {
        return nbWordsChosen;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setStrokes(int Strokes){
        this.strokes = Strokes;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public void setNbWords(int nbWords) {
        this.nbWords = nbWords;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public void setNbWordsChosen(int nbWordsChosen) {
        this.nbWordsChosen = nbWordsChosen;
    }

    public void addView(ViewMenu view){
        this.view = view;
    }

    public void decreaseWord(){
        this.nbWords --;
    }

    public void increaseVictory(){
        this.victory ++;
    }

    public void increaseFailure(){
        this.failure ++;
    }
}
