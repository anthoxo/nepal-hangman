import java.util.ArrayList;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word mainW;
    protected ArrayList<Character> Letters; //letters of the mainW
    protected ArrayList<Integer> LettersFind; //same size as Letters, with 0 and 1
    protected ArrayList<Character> LettersPlayed;  //all letters played

    public Controller(){
        this.mainW = new Word();
        this.Letters = new ArrayList<Character>();
        this.LettersFind = new ArrayList<Integer>();
        this.LettersPlayed = new ArrayList<Character>();
    }

    public Controller(Word w){
        this.mainW = w;
        this.Letters = convertStringToArrayList(w.getWord());
        this.LettersFind = initArrayList(w.getSizeWord());
        this.LettersPlayed = new ArrayList<Character>();
    }

    public Controller(Controller c){
        this.mainW = c.mainW;
        this.Letters = c.Letters;
        this.LettersFind = c.LettersFind;
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
     *
     * @param size the size of the ArrayList<Integer>
     * @return an ArrayList with 0.
     */
    public ArrayList<Integer> initArrayList(int size){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i=0 ; i<size ; i++)
            intList.add(0);
        return intList;
    }

    /**
     * @return boolean to verify the victory.
     */
    public boolean checkVictory(){
        if (this.Letters.size() == this.LettersFind.size()){
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
     * If it is the case, it fills the ArrayList LettersFind.
     */
    public boolean checkLetter(char c){
        char[] tab = (this.mainW.getWord()).toCharArray();
        int size = tab.length;
        boolean check = false;

        for (int i=0 ; i<size ; i++){
            if (tab[i] == c){
                this.LettersFind.set(i,1);
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
            else{
                this.LettersPlayed.add(c);
                this.waitLetter();
            }
        }
        else{
            mainW.decreaseNbStrokes();
            if (!this.checkCount()){
                System.out.println("You lose !");
                //sortir ? clear ?
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
