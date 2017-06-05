package Models;

import java.io.*;

public class FileText {
    private String header[];
    private Object body[][];
    private String fileName = null;
    private int height;
    private int width;

    public FileText(String fileName){
        try {
            this.setFileName(fileName);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String lineTab[] = line.split("\t");
            this.header = lineTab;
            this.width = this.header.length;

            while ((line = br.readLine()) != null){
                lineTab = line.split("\t");
                Object tmpBody[][] = this.body;
                this.body = new Object[this.height+1][this.width];
                for (int i = 0 ; i<this.height ; i++){
                    for (int j = 0 ; j<this.width ; j++){
                        this.body[i][j] = tmpBody[i][j];
                    }
                }
                this.height += 1;
                for (int i = 0; i<this.width; i++){
                    this.body[this.height-1][i] = lineTab[i];
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName));
            for (int i = 0 ; i<this.width ; i++){
                bw.write(this.header[i]);
                if (i != this.width-1){
                    bw.write("\t");
                }
            }
            bw.newLine();
            for (int i = 0 ; i<this.height ; i++){
                for (int j = 0 ; j<this.width ; j++){
                    if (this.body[i][j] instanceof Integer){
                        bw.write(Integer.toString((Integer)this.body[i][j]));
                    }
                    else{
                        bw.write((String)this.body[i][j]);
                    }
                    if (j != this.width-1){
                        bw.write("\t");
                    }
                }
                if (i != this.height-1){
                    bw.newLine();
                }
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void increaseSize(){
        Object tmp[][] = this.getBody();
        this.body = new Object[this.getHeight()+1][this.getWidth()];
        for (int i = 0; i<this.height ; i++){
            for (int j = 0; j<this.width ; j++){
                this.body[i][j] = tmp[i][j];
            }
        }
        for (int j = 0; j<this.width ; j++){
            this.body[this.height][j] = null;
        }
        this.height += 1;
    }
    public void decreaseSize(int index){
        if (index >= 0 && index <=this.height-1){
            Object tmp[][] = this.getBody();
            this.body = new Object[this.getHeight()-1][this.getWidth()];
            for (int i = 0; i<index ; i++){
                this.body[i][0] = i;
                for (int j = 1; j<this.width ; j++){
                    this.body[i][j] = tmp[i][j];
                }
            }
            this.height -= 1;
            for (int i = index; i<this.height ; i++){
                this.body[i][0] = i;
                for (int j = 1; j<this.width ; j++){
                    this.body[i][j] = tmp[i+1][j];
                }
            }
            this.save();

        }
    }

    public int getHeight(){ return this.height; }
    public int getWidth(){ return this.width; }
    public Object[][] getBody(){ return this.body; }
    public String[] getHeader(){ return this.header; }
    public String getFileName(){ return this.fileName;}

    public void setFileName(String fileName){
        this.fileName = fileName;
    }
}
