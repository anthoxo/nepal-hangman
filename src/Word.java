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
    public String random(Dictionary d){
        int size = d.getSize();
        int index;
        index = (int)(Math.round(Math.random() * (size + 1)));
        return d.getWords().get(index);
    }

    /**
     * @return the word chosen by random for a theme given.
     */
    public Word randomTheme(int IndexTheme){

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