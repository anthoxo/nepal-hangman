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

    protected String fileWords;
    protected String fileThemes;
    protected Integer size;

    /**
     * Default constructor (useless)
     */
    public Dictionary(){
        this.words = new Hashtable();
        this.themesIndex = new Hashtable();
        this.definitions = new Hashtable();
        this.themes = new Hashtable();
        this.fileWords = "";
        this.fileThemes = "";
        this.size = 0;
    }

    /**
     * Main constructor
     * @param fileWords name of dictionary file
     * @param fileThemes name of themes file
     */
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
     * This method takes all of an text file and puts in the created object.
     *
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
     * Method for putting up a word in the object and in the text file.
     * @param word
     * @param theme
     * @param definition
     * @return true if the word is added, else false
     */
    public boolean put(String word, String theme, String definition){
        if (this.themes.containsValue(theme) == false || this.getWords().containsValue(word) == true){
            return false;
        }
        else{
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
            return true;
        }
    }

    /**
     * Method for adding a theme in data base.
     * @param theme
     * @return true if the theme has been added, else false
     */
    public boolean put(String theme){
        if (this.themes.containsValue(theme) == true){
            return false;
        }
        else{
            FileText fileThemes = new FileText(this.getFileThemes());
            fileThemes.increaseSize();
            Object corps[][] = fileThemes.getBody();
            int index = fileThemes.getHeight() - 1;
            corps[index][0] = index;
            corps[index][1] = theme;
            this.themes.put(index, theme);
            fileThemes.save();
            return true;
        }
    }

    /**
     * Delete a word from the object and the text file.
     * @param word the word for deleting
     * @return true if the word has been deleted, else false
     */
    public boolean delete(Word word){
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
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Method for deleting a theme from the data base.
     * @param theme the theme for deleting
     * @return true if the theme has been deleted, else false
     */
    public boolean delete(String theme){
        if (this.getThemes().containsValue(theme)){
            int key = -1;
            Enumeration themes = this.getThemes().keys();
            while (themes.hasMoreElements() && key == -1){
                int tmp = (int) themes.nextElement();
                if (this.getThemes().get(tmp).equals(theme)){
                    key = tmp;
                }
            }
            boolean result = this.getThemesIndex().containsValue(key);
            if (result){
                return false;
            }
            else{
                FileText fileTheme = new FileText(this.getFileThemes());
                fileTheme.decreaseSize(key);
                this.getWords().clear();
                this.getThemes().clear();
                this.getDefinitions().clear();
                this.getThemesIndex().clear();
                this.fill();
                return true;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Create an array of int that the theme is corresponding with a number
     * @param index index of a theme
     * @return an Array(int) of the index that the theme is corresponding with index
     */
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

    /**
     * Method contains.
     * @param word
     * @return true if word is in the object, else false
     */
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
    /**
     * @return the index in the fileTheme of the String theme.
     */
    public int getIndexTheme(String theme){
        for (int i=0 ; i<this.themes.size() ; i++){
            if (this.getThemes().get(i).compareTo(theme) == 0)
                return i;
        }
        return -1;
    }
}
