/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linecounter;

/**
 *
 * @author KuroiTenshi
 */
public class Pair {
    private String fileName;
    private int lineCount;
    private String filePath;

    public Pair(String fileName, int lineCount, String filePath) {
        this.fileName = fileName;
        this.lineCount = lineCount;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
