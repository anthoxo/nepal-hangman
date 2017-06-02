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
            Excel excelThemes = new Excel(this.getFileThemes(), "Feuille1");
            Object corps[][] = excelThemes.getBody();
            int height = excelThemes.getHeight();
            for (int i = 0 ; i<height ; i++){
                Integer index = ((int)corps[i][0]);
                this.themes.put(index, (String)corps[i][1]);
            }

            /**
             * To fill the dictionary
             */

            Excel excel = new Excel(this.getFileWords(), "Feuille1");
            corps = excel.getBody();
            height = excel.getHeight();
            for (int i = 0 ; i<height ; i++){
                Integer index = ((int)corps[i][0]);
                this.words.put(index, (String)corps[i][1]);
                this.themesIndex.put(index, (Integer)corps[i][2]);
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

            Excel excelTheme = new Excel(this.getFileThemes(), "Feuille1");
            excelTheme.increaseSize();
            Object corpsThemes[][] = excelTheme.getBody();
            int indexTheme = excelTheme.getHeight() - 1;
            corpsThemes[indexTheme][0] = indexTheme + 1;
            corpsThemes[indexTheme][1] = theme;
            this.themes.put(indexTheme+1, theme);
            excelTheme.save();
        }

        Enumeration<Integer> themeKeys = this.themes.keys();
        int keys = -1;
        while (themeKeys.hasMoreElements() && keys == -1){
            Integer tmp = themeKeys.nextElement();

            if (this.themes.get(tmp).equals(theme)){
                keys = tmp;
            }
        }

        Excel excel = new Excel(this.getFileWords(), "Feuille1");
        excel.increaseSize();
        Object corps[][] = excel.getBody();
        int index = excel.getHeight() - 1;
        corps[index][0] = index+1;
        corps[index][1] = word;
        corps[index][2] = keys;
        corps[index][3] = definition;
        this.words.put(index+1, word);
        this.themesIndex.put(index+1, keys);
        this.definitions.put(index+1, definition);
        this.size += 1;
        excel.save();
    }

    /**
     * TODO later
     */
    public void delete(){

    }

    public ArrayList<Integer> listThemes(int index){
        Dictionary tmp = this;
        ArrayList<Integer> result = new ArrayList();
        Enumeration<Integer> keysThemes = tmp.getThemes().keys();
        while (keysThemes.hasMoreElements()){
            Integer key = keysThemes.nextElement();
            if (tmp.getThemes().get(key).equals(index)){
                result.add(key);
            }
        }
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
    private String getFileThemes() {
        return this.fileThemes;
    }
    /**
     * @return the name of the file for words.
     */
    private String getFileWords() {
        return this.fileWords;
    }


    /**
     * @return the size of the dictionary.
     */

    public int getSize(){
        return this.size;
    }

    public static void main(String[] args){
        Dictionary dico = new Dictionary("dictionary.xls", "themes.xls");

        dico.fill();
        System.out.println(dico.getSize());
        System.out.println(dico.themes);
        System.out.println(dico.themesIndex);
        System.out.println(dico.words);
        System.out.println(dico.definitions);

    }
}
