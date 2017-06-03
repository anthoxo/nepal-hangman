import java.util.ArrayList;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word mainW;
    protected char[] letters; //letters of the mainW
    protected ArrayList<Integer> lettersFind; //same size as letters, with 0 and 1
    protected ArrayList<Character> lettersPlayed;  //all letters played

    public Controller(){
        this.mainW = new Word();
        this.letters = new char[0];
        this.lettersFind = new ArrayList<Integer>();
        this.lettersPlayed = new ArrayList<Character>();
    }

    public Controller(Word w){
        this.mainW = w;
        this.letters = w.getWord().toCharArray();
        this.lettersFind = initArrayList(w.getSizeWord());
        this.lettersPlayed = new ArrayList<Character>();
    }

    public Controller(Controller c){
        this.mainW = c.mainW;
        this.letters = c.letters;
        this.lettersFind = c.lettersFind;
        this.lettersPlayed = c.lettersPlayed;
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
        if (this.letters.length == this.lettersFind.size()){
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
                this.lettersFind.set(i,1);
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
                this.lettersPlayed.add(c);
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
                this.lettersPlayed.add(c);
                this.waitLetter();
            }
        }
    }

    /**
     * TODO later
     */
    public void waitLetter(){

    }

    /**
     * TODO later
     */
    public void run(){

    }
}
