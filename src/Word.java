import java.util.Hashtable;

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


    public String getWord(){
        return this.word;
    }

    public String getTheme(){
        return this.theme;
    }

    public String getDefinition(){
        return this.definition;
    }

    public int getnbStrokes() {
        return this.nbStrokes;
    }

    public int getSizeWord(){
        return this.word.length();
    }

    public Word random(Dictionary d){
        int size = d.getSize();

    }

    public Word randomTheme(String theme){

    }

    public void decreaseNbStrokes(Word w){
        this.word = w.word;
        this.theme = w.theme;
        this.definition = w.definition;
        this.nbStrokes = w.nbStrokes --;
    }
}