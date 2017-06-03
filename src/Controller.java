import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word mainW;
    protected ArrayList<Character> Letters;
    protected ArrayList<Character> LettersUsed; //letters in the mainW
    protected ArrayList<Character> LettersPlayed;

    public Controller(){
        this.mainW = new Word();
        this.Letters = new ArrayList<Character>();
        this.LettersUsed = new ArrayList<Character>();
        this.LettersPlayed = new ArrayList<Character>();
    }

    public Controller(Word w){
        this.mainW = w;
        this.Letters = convertStringToArrayList(w.word);
        this.LettersUsed = new ArrayList<Character>();
        this.LettersPlayed = new ArrayList<Character>();
    }

    public Controller(Controller c){
        this.mainW = c.mainW;
        this.Letters = c.Letters;
        this.LettersUsed = c.LettersUsed;
        this.LettersPlayed = c.LettersPlayed;
    }

    /**
     * @return conversion of a String to an ArrayList.
     */
    public ArrayList<Character> convertStringToArrayList(String str){
        ArrayList<Character> charList = new ArrayList<Character>();
        for (int i=0 ; i<str.length() ; i++){
            charList.add(str.charAt(i));
        }
        return charList;
    }

    /**
     * @return boolean to verify the victory.
     */
    public boolean checkVictory(){
        if (this.Letters.size() == this.LettersUsed.size()){
            return true;
        }
        return false;
    }

    /**
     * @return boolean to verify the number of strokes.
     */
    public boolean checkCount(){
        if (this.mainW.getnbStrokes() == 0){
            return false;
        }
        return true;
    }

    /**
     * @return boolean to verify if the letter is in the word.
     */
    public boolean checkLetter(char c){
        char[] tab = (this.mainW.getWord()).toCharArray();
        int size = tab.length;
        boolean check = false;

        for (int i=0 ; i<size ; i++){
            if (tab[i] == c){
                this.LettersUsed.add(c);
                check = true;
            }
        }
        return check;
    }

    /**
     * @return boolean to verify the size of the lists Letters and LettersUsed.
     */
    private boolean sizeLettersUsed(){
        if (this.Letters.size() < this.LettersUsed.size()){
            return false;
        }
        return true;
    }

    public void fillLetterUsed(){

    }

    /**
     * Actions to do when a letter is chosen.
     */
    public void actionLetter(char c){
        if (this.checkLetter(c)){
            if (this.checkVictory()){
                //TODO
            }
            else
                this.LettersPlayed.add(c);
        }
        else{
            mainW.decreaseNbStrokes();
            if (!this.checkCount()){
                System.out.println("You lose !");
                //sortir ?
            }
            else {
                this.LettersPlayed.add(c);
                this.waitLetter();
            }
        }
    }

    /**
     * TODO later
     */
    public void waitLetter(){

    }
}
