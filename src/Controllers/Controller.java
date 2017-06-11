package Controllers;
import Models.Dictionary;
import Models.Word;
import Views.ViewBasic;

import java.util.ArrayList;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word mainW;
    protected char[] letters; //letters of the mainW
    protected ArrayList<Character> lettersFound; //same size as letters, with 0 and 1
    protected ArrayList<Character> lettersPlayed;  //all letters played
    protected ViewBasic view;

    public Controller(Dictionary d, int nbStrokes){
        ArrayList<Character> list = new ArrayList<Character>();

        this.mainW = new Word();
        this.mainW.launch(d, nbStrokes);
        this.letters = this.mainW.getWord().toCharArray();

        for (int i=0 ; i<this.mainW.getSizeWord() ; i++)
            list.add('_');
        this.lettersFound = list;
        this.lettersPlayed = new ArrayList<Character>();
        this.view = null;
    }

    public Controller(Dictionary d, int indexTheme, int nbStrokes){
        ArrayList<Character> list = new ArrayList<Character>();

        this.mainW = new Word();
        this.mainW.launch(d, indexTheme, nbStrokes);
        this.letters = this.mainW.getWord().toCharArray();

        for (int i=0 ; i<this.mainW.getSizeWord() ; i++)
            list.add('_');
        this.lettersFound = list;
        this.lettersPlayed = new ArrayList<Character>();
        this.view = null;
    }

    public Controller(Controller c){
        this.mainW = c.mainW;
        this.letters = c.letters;
        this.lettersFound = c.lettersFound;
        this.lettersPlayed = c.lettersPlayed;
        this.view = c.view;
    }

    public Word getMainW(){
        return this.mainW;
    }

    public ArrayList<Character> getLettersFound(){
        return this.lettersFound;
    }

    public ArrayList<Character> getLettersPlayed(){
        return this.lettersPlayed;
    }

    /**
     * Print in the console the ArrayList lettersFound.
     */
    public void printLettersFound(){
        for (int i=0; i<this.letters.length; i++){
            System.out.print(this.lettersFound.get(i));
        }
        System.out.print("\n");
    }

    /**
     * Print in the console the ArrayList lettersPlayed.
     */
    public void printLettersPlayed(){
        System.out.print("[");
        for (int i=0; i<this.lettersPlayed.size()-1; i++){
            System.out.print(this.lettersPlayed.get(i)+",");
        }
        System.out.print(this.lettersPlayed.get(this.lettersPlayed.size()-1));
        System.out.println("]");
    }

    /**
     * @return the number of letters found by the player.
     */
    public int getNbLettersFound(){
        int size = this.letters.length;
        int count = 0;
        for (int i=0; i<size; i++){
            if (this.lettersFound.get(i) != '_')
                count ++;
        }
        return count;
    }

    /**
     * @return boolean to verify the victory.
     */
    public boolean checkVictory(){
        if (this.getNbLettersFound() == this.letters.length){
            return true;
        }
        return false;
    }

    /**
     * @return boolean to verify the number of strokes.
     */
    public boolean checkCount(){
        if (this.mainW.getNbStrokes() == 0){
            return false;
        }
        return true;
    }

    /**
     * @return boolean to verify if the letter is in the word.
     * If it is the case, it fills the ArrayList lettersFind.
     */
    public boolean checkLetter(char c){
        char[] tab = (this.mainW.getWord()).toCharArray();
        int size = tab.length;
        boolean check = false;

        for (int i=0 ; i<size ; i++){
            if (Character.toUpperCase(tab[i]) == Character.toUpperCase(c)){
                this.lettersFound.set(i,Character.toUpperCase(c));
                check = true;
            }
        }
        return check;
    }


    /**
     * Actions to do when a letter is chosen.
     */
    public void actionLetter(char c, Menu menu){
        String result;
        //In mode "Display letters played", the game tells the player he can't play a letter already played
        if (menu.getMode() && this.getLettersPlayed().contains(c)){
            result = "Letter "+c+" has already been played !\n";
            result+="LettersPlayed : ";
            result+=this.lettersPlayed.toString();
        }
        //If the letter played is in the word
        else if (this.checkLetter(c)){
            this.lettersPlayed.add(Character.toUpperCase(c));
            //Verify the victory
            if (this.checkVictory()){
                menu.increaseVictory();
                if (menu.getNbWords() != -1)
                    menu.decreaseWord();
                result = "Congratulations, you win !";
                this.view.printVictory(true);
            }
            else{
                result = "Letter "+c+" Yes !\n";
                if (menu.getMode()){
                    result+="LettersPlayed : ";
                    result+=this.lettersPlayed.toString();
                }
            }
        }
        //If the letter played is not in the word
        else{
            this.lettersPlayed.add(Character.toUpperCase(c));
            mainW.decreaseNbStrokes();
            //When the player lose the game
            if (!this.checkCount()){
                menu.increaseFailure();
                if (menu.getNbWords() != -1)
                    menu.decreaseWord();
                result = "You lose...";
                this.view.printVictory(false);
            }
            else{
                result = "Letter "+c+" not in the word.\n";
                result += "Number of strokes : "+this.mainW.getNbStrokes()+"\n";
                if (menu.getMode()){
                    result+="LettersPlayed : ";
                    result+=this.lettersPlayed.toString();
                }
            }
        }
        this.view.refresh(result);
    }

    /**
     * Actions to do when a letter is played.
     */
    public void run(char c, Menu menu){
        if (!this.checkVictory() && this.getMainW().getNbStrokes() > 0){
            this.actionLetter(c,menu);
        }
    }

    /**
     * Add view to the controller.
     */
    public void addView(ViewBasic view){
        this.view = view;
    }

}
