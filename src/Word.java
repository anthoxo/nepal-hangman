import java.util.*;

/**
 * Created by helene on 02/06/17.
 */
public class Word {
    protected String word;
    protected String theme;
    protected String definition;
    protected Integer nbStrokes;


    public Word(){
        this.word = "";
        this.theme = "";
        this.definition = "";
        this.nbStrokes = 0;
    }

    public Word(String word, String theme, String definition,int n){
        this.word = word;
        this.theme = theme;
        this.definition = definition;
        this.nbStrokes = n;
    }

    public Word(Word w){
        this.word = w.word;
        this.theme = w.theme;
        this.definition = w.definition;
        this.nbStrokes = w.nbStrokes;
    }

    /**
     * @return the name of the word.
     */
    public String getWord(){
        return this.word;
    }

    /**
     * @return the name of the respective theme of the word.
     */
    public String getTheme(){
        return this.theme;
    }

    /**
     * @return the name of the respective definition of the word.
     */
    public String getDefinition(){
        return this.definition;
    }

    /**
     * @return the number of strokes.
     */
    public int getnbStrokes() {
        return this.nbStrokes;
    }

    /**
     * @return the number of letters of the word.
     */
    public int getSizeWord(){
        return this.word.length();
    }

    /**
     * @return the word chosen by random.
     */
    public void random(Dictionary d, int nbStrokes){
        int index;
        int size = d.getSize();

        index = (int)(Math.round(Math.random() * (size + 1)));
        this.word = d.getWords().get(index);
        this.theme = d.getThemes().get(index);
        this.definition = d.getDefinitions().get(index);
        this.nbStrokes = nbStrokes;

    }

    /**
     * @return the word chosen by random for a theme given.
     */
    public void randomTheme(Dictionary d, int indexTheme, int nbStrokes){
        int n;
        int index;
        ArrayList<Integer> list = d.listThemes(indexTheme);
        int size = list.size();

        n = (int)(Math.round(Math.random() * size));
        index = list.get(n);
        this.word = d.getWords().get(index);
        this.theme = d.getThemes().get(index);
        this.definition = d.getDefinitions().get(index);
        this.nbStrokes = nbStrokes;

    }

    /**
     * Decrease the number of strokes.
     */
    public void decreaseNbStrokes(Word w){
        this.word = w.word;
        this.theme = w.theme;
        this.definition = w.definition;
        this.nbStrokes = w.nbStrokes --;
    }

}