import java.util.ArrayList;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word mainW;
    protected char[] letters; //letters of the mainW
    protected ArrayList<Character> lettersFound; //same size as letters, with 0 and 1
    protected ArrayList<Character> lettersPlayed;  //all letters played

    public Controller(){//this constructor is useless
        this.mainW = new Word();
        this.letters = new char[0];
        this.lettersFound = new ArrayList<Character>();
        this.lettersPlayed = new ArrayList<Character>();
    }

    public Controller(Word w){
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i=0 ; i<w.getSizeWord() ; i++)
            list.add('_');

        this.mainW = w;
        this.letters = w.getWord().toCharArray();
        this.lettersFound = list;
        this.lettersPlayed = new ArrayList<Character>();
    }

    public Controller(Controller c){
        this.mainW = c.mainW;
        this.letters = c.letters;
        this.lettersFound = c.lettersFound;
        this.lettersPlayed = c.lettersPlayed;
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
            if (tab[i] == c){
                this.lettersFound.set(i,c);
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
            if (this.checkVictory()){
                System.out.println("You win !");
                //sortir ? clear ?
            }
            else
                this.lettersPlayed.add(c);
        }
        else{
            mainW.decreaseNbStrokes();
            if (!this.checkCount()){
                System.out.println("You lose !");
                //sortir ? clear ?
            }
            else
                this.lettersPlayed.add(c);
        }
    }

    /**
     * Actions to do when a letter is played.
     */
    public void run(char c){
        this.actionLetter(c);
    }

}
