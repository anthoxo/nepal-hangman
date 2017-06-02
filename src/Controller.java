import java.util.ArrayList;

/**
 * Created by helene on 03/06/17.
 */
public class Controller {
    protected Word word;
    protected ArrayList<String> Letters;
    protected ArrayList<String> LettersUsed;

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
        if (this.word.getnbStrokes() == 0){
            return false;
        }
        return true;
    }

    /**
     * @return boolean to verify if the letter is in the word.
     */
    public boolean checkLetter(char c){
        int size = this.word.getSizeWord();
        String tab[] = this.word.toCharArray();

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
}
