package Controllers;

import Views.ViewMenu;

/**
 * Created by helene on 06/06/17.
 */
public class Menu {
    protected int theme;  //-1 if it is the mix version
    protected int nbStrokesAllowed;
    protected int mode; //0 if it is the normal mode, 1 the advanced one
    protected ViewMenu view;

    public Menu(){
        this.theme = -1;
        this.nbStrokesAllowed = 0;
        this.mode = 0;
    }

    public Menu(int theme, int nbStrokesAllowed, int mode){
        this.theme = theme;
        this.nbStrokesAllowed = nbStrokesAllowed;
        this.mode = mode;
    }

    public Menu(Menu m){
        this.theme = m.theme;
        this.nbStrokesAllowed = m.nbStrokesAllowed;
        this.mode = m.mode;
    }

    public int getMode() {
        return mode;
    }

    public int getNbStrokesAllowed() {
        return nbStrokesAllowed;
    }

    public int getTheme() {
        return theme;
    }

    public void addView(ViewMenu view){
        this.view = view;
    }
}
