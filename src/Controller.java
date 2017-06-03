import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word mainW;
    protected ArrayList<Character> Letters;
    protected ArrayList<Character> LettersUsed;

    public Controller(){
        this.mainW = new Word();
        this.Letters = new ArrayList<Character>();
        this.LettersUsed = new ArrayList<Character>();
    }

    public Controller(Word w){
        this.mainW = w;
        this.Letters = convertStringToArrayList(w.word);
        this.LettersUsed = new ArrayList<Character>();
    }

    public Controller(Controller c){
        this.mainW = c.mainW;
        this.Letters = c.Letters;
        this.LettersUsed = c.LettersUsed;
    }

    /**
     * @return a conversion of a String to a ArrayList
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
        char[] tab = (this.mainW.word).toCharArray();
        int size = tab.length;

        for (int i=0 ; i<size ; i++){
            if (tab[i] == c)
                return true;
        }
        return false;
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

    /**
     * Actions to do when a letter is chosen.
     */
    public void actionLetter(char c){
        if (checkLetter(c)){

        }
        else{
            mainW.decreaseNbStrokes();
        }
    }
}
