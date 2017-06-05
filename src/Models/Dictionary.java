package Models;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Model that used as a basis for the entire project
 * It contains 3 Hashtables (dictionary with a key and a value) : words, themes, definitions
 *
 *
 */
public class Dictionary {

    protected Hashtable<Integer,String> words;
    protected Hashtable<Integer,Integer> themesIndex;
    protected Hashtable<Integer,String> definitions;
    protected Hashtable<Integer,String> themes;
    /**
     * fileName and sheetName are for Excel class
     */
    protected String fileWords;
    protected String fileThemes;
    protected Integer size;

    public Dictionary(){
        this.words = new Hashtable();
        this.themesIndex = new Hashtable();
        this.definitions = new Hashtable();
        this.themes = new Hashtable();
        this.fileWords = "";
        this.fileThemes = "";
        this.size = 0;
    }
    public Dictionary(String fileWords, String fileThemes){
        this.words = new Hashtable();
        this.themesIndex = new Hashtable();
        this.definitions = new Hashtable();
        this.themes = new Hashtable();
        this.fileWords = fileWords;
        this.fileThemes = fileThemes;
        this.size = 0;
    }

    /**
     * This method takes all of an Excel file and puts in the created object.
     * If the file doesn't exit, then the constructor of the Excel object makes an exception.
     */
    public void fill(){
        if (!this.getFileThemes().equals("") && !this.getFileWords().equals("")){
            /**
             * To fill the themes list
             */
            this.size = 0;
            FileText fileThemes = new FileText(this.getFileThemes());
            Object corps[][] = fileThemes.getBody();
            int height = fileThemes.getHeight();
            for (int i = 0 ; i<height ; i++){
                Integer index = Integer.parseInt((String)corps[i][0]);
                this.themes.put(index, (String)corps[i][1]);
            }

            /**
             * To fill the dictionary
             */

            FileText file = new FileText(this.getFileWords());
            corps = file.getBody();
            height = file.getHeight();
            for (int i = 0 ; i<height ; i++){
                Integer index = Integer.parseInt((String)corps[i][0]);
                this.words.put(index, (String)corps[i][1]);
                this.themesIndex.put(index, Integer.parseInt((String)corps[i][2]));
                this.definitions.put(index, (String)corps[i][3]);
                this.size += 1;
            }
        }
    }
    /**
     * This method adds in the Excel file and in the structure an element with his word, his theme
     * and his definition.
     */

    public void put(String word, String theme, String definition){
        if (this.themes.containsValue(theme) == false){
            FileText fileTheme = new FileText(this.getFileThemes());
            fileTheme.increaseSize();
            Object corpsThemes[][] = fileTheme.getBody();
            int indexTheme = fileTheme.getHeight() - 1;
            corpsThemes[indexTheme][0] = indexTheme;
            corpsThemes[indexTheme][1] = theme;
            this.themes.put(indexTheme, theme);
            fileTheme.save();
        }

        Enumeration<Integer> themeKeys = this.themes.keys();
        int keys = -1;
        while (themeKeys.hasMoreElements() && keys == -1){
            Integer tmp = themeKeys.nextElement();

            if (this.themes.get(tmp).equals(theme)){
                keys = tmp;
            }
        }

        FileText file = new FileText(this.getFileWords());
        file.increaseSize();
        Object corps[][] = file.getBody();
        int index = file.getHeight() - 1;
        corps[index][0] = index;
        corps[index][1] = word;
        corps[index][2] = keys;
        corps[index][3] = definition;
        this.words.put(index, word);
        this.themesIndex.put(index, keys);
        this.definitions.put(index, definition);
        this.size += 1;
        file.save();
    }

    public void delete(Word word){
        if (this.contains(word)){
            FileText file = new FileText(this.getFileWords());

            Enumeration<Integer> wordKeys = this.getWords().keys();
            int keys = -1;
            while (wordKeys.hasMoreElements() && keys == -1){
                Integer tmp = wordKeys.nextElement();
                if (this.getWords().get(tmp).equals(word.getWord())){
                    keys = tmp;
                }
            }

            file.decreaseSize(keys);

            this.getWords().clear();
            this.getThemes().clear();
            this.getDefinitions().clear();
            this.getThemesIndex().clear();
            this.fill();
        }
    }

    public ArrayList<Integer> listThemes(int index){
        Dictionary tmp = this;
        ArrayList<Integer> result = new ArrayList();
        Enumeration<Integer> keysThemes = tmp.getThemesIndex().keys();
        while (keysThemes.hasMoreElements()){
            Integer key = keysThemes.nextElement();
            if (tmp.getThemesIndex().get(key).equals(index)){
                result.add(key);
            }
        }
        return result;
    }

    public boolean contains(Word word){
        boolean result = true;
        result = result && this.getWords().contains(word.getWord());
        result = result && this.getThemes().contains(word.getTheme());
        return result;
    }

    public Hashtable<Integer, String> getWords() {
        return this.words;
    }

    public Hashtable<Integer, Integer> getThemesIndex() {
        return this.themesIndex;
    }

    public Hashtable<Integer, String> getDefinitions() {
        return this.definitions;
    }

    public Hashtable<Integer, String> getThemes() {
        return themes;
    }

    /**
     * @return the name of the file for themes.
     */
    public String getFileThemes() {
        return this.fileThemes;
    }
    /**
     * @return the name of the file for words.
     */
    public String getFileWords() {
        return this.fileWords;
    }


    /**
     * @return the size of the dictionary.
     */

    public int getSize(){
        return this.size;
    }

}
