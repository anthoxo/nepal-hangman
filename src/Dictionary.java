import java.util.Hashtable;

public class Dictionary extends Hashtable<String,String> {

    protected Hashtable<Integer,String> words;
    protected Hashtable<Integer,String> themes;
    protected Hashtable<Integer,String> definitions;
    protected String fileName;
    protected String sheetName;
    protected Integer size;

    public Dictionary(){
        this.words = new Hashtable();
        this.themes = new Hashtable();
        this.definitions = new Hashtable();
        this.fileName = "";
        this.sheetName = "";
        this.size = 0;
    }
    public Dictionary(String file, String sheet){
        this.words = new Hashtable();
        this.themes = new Hashtable();
        this.definitions = new Hashtable();
        this.fileName = file;
        this.sheetName = sheet;
        this.size = 0;
    }

    public void fill(){
        if (this.getFile() != "" && this.getSheet() != ""){
            Excel excel = new Excel(this.getFile(), this.getSheet());
            Object corps[][] = excel.getBody();
            int height = excel.getHeight();
            for (int i = 0 ; i<height ; i++){
                Integer index = ((int)corps[i][0]);
                this.words.put(index, (String)corps[i][1]);
                this.themes.put(index, (String)corps[i][2]);
                this.definitions.put(index, (String)corps[i][3]);
                this.size += 1;
            }
        }
    }
    public void put(String word, String theme, String definition){
        Excel excel = new Excel(this.getFile(), this.getSheet());
        excel.increaseSize();
        Object corps[][] = excel.getBody();
        int index = excel.getHeight() - 1;
        corps[index][0] = index+1;
        corps[index][1] = word;
        corps[index][2] = theme;
        corps[index][3] = definition;
        this.words.put(index+1, word);
        this.themes.put(index+1, theme);
        this.definitions.put(index+1, definition);
        this.size += 1;
        excel.save();
    }
    public void delete(){

    }

    private String getSheet() {
        return this.sheetName;
    }

    private String getFile() {
        return this.fileName;
    }

    public int getSize(){
        return this.size;
    }

    public static void main(String[] args){
        Dictionary exemple = new Dictionary("dictionary.xls", "sheet");
        exemple.fill();
        System.out.print(exemple.words);
        System.out.print(exemple.size);
        exemple.put("KATMANDU","Capital city","The capital of Nepal");
        System.out.print(exemple.words);
        System.out.print(exemple.getSize());

    }
}
