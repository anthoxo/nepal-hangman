package Controllers;
import Views.*;
import Models.Word;

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


    public Controller(){//this constructor is useless
        this.mainW = new Word();
        this.letters = new char[0];
        this.lettersFound = new ArrayList<Character>();
        this.lettersPlayed = new ArrayList<Character>();
        this.view = null;
    }

    public Controller(Word w){
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i=0 ; i<w.getSizeWord() ; i++)
            list.add('_');

        this.mainW = w;
        this.letters = w.getWord().toCharArray();
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
    public void actionLetter(char c){
        if (this.checkLetter(c)){
            System.out.println("Letter "+c+" Yes");
            System.out.print("LettersFound: ");
            this.printLettersFound();
            if (this.checkVictory()){
                System.out.println("You win !");
                //sortir ? clear ?
            }
            else{
                this.lettersPlayed.add(Character.toUpperCase(c));
                System.out.println("LettersPlayed: ");
                this.printLettersPlayed();
            }
        }
        else{
            System.out.println("Letter "+c+" No");
            mainW.decreaseNbStrokes();
            System.out.println("NbStrokes : "+this.mainW.getNbStrokes());
            System.out.print("LettersFound: ");
            this.printLettersFound();
            if (!this.checkCount()){
                System.out.println("You lose...");
                //sortir ? clear ?
            }
            else{
                this.lettersPlayed.add(Character.toUpperCase(c));
                System.out.print("LettersPlayed: ");
                this.printLettersPlayed();
            }
        }
        System.out.print("\n");
    }

    /**
     * Actions to do when a letter is played.
     */
    public void run(char c){
        this.actionLetter(c);
    }

    /**
     * @param view
     * Add view to the controller.
     */
    public void addView(ViewBasic view){
        this.view = view;
    }

    /**
    public static void main(String[] args){
        Word test = new Word("Papu","Random","Random word",2);
        Controller c = new Controller(test);

        System.out.println("   At the beginning");
        System.out.println("Word: "+test.getWord());
        System.out.println("Theme: "+test.getTheme());
        System.out.println("Definition: "+test.getDefinition());
        System.out.println("NbStrokes: "+test.getNbStrokes());
        System.out.print("LettersFound: ");
        c.printLettersFound();
        System.out.println("\n   Launching tests");
        System.out.println("\nTest 1");
        c.run('A');
        System.out.println("\nTest 2");
        c.run('u');
        System.out.println("\nTest 3");
        c.run('y');
        System.out.println("\nTest 4");
        c.run('x');
    }
    */

}
