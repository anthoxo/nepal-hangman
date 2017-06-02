import java.util.Hashtable;

/**
 * Created by helene on 02/06/17.
 */
public class Word extends Dictionary {
    protected String word;
    protected String theme;
    protected String definition;


    public Word(){
        this.word = "";
        this.theme = "";
        this.definition = "";
    }

    public Word(String word, String theme, String definition){
        this.word = word;
        this.theme = theme;
        this.definition = definition;
    }

    public Word(Word w){
        this.word = w.word;
        this.theme = w.theme;
        this.definition = w.definition;
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

    public int getSizeWord(){
        return this.word.length();
    }

}