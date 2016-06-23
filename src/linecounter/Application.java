package linecounter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author KuroiTenshi
 */
public class Application {
    private static final List<Pair> MY_LIST = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> filter = new ArrayList<>();
        
        File f = null;
        if( args.length > 0 ) {
            f = new File(args[0]);            
        }
        
        filter.addAll(Arrays.asList(args).subList(1, args.length));
        
        if (f != null && f.isDirectory()) {
            findFiles(f.getPath());
        } else {
            System.out.println("Input Error!");
            System.exit(1);
        }
         
        int count = 0 ;
        int lineNumb = 1 ;
        for ( Pair vLookUp:MY_LIST ) {
            boolean flag = true;
            System.out.println( lineNumb + ". " + vLookUp.getFileName() + " has : " + vLookUp.getLineCount() + " lines...");
            lineNumb++;
            
            if( !filter.isEmpty() ) {
                for(String vString:filter) {
                    if ( vLookUp.getFileName().contains(vString) ) {
                        flag = false;
                    }                    
                }
            }
            
            if ( flag ) {
                count += vLookUp.getLineCount();                
            }
        }
        
        System.out.println("Total lines : " + count);
    }
    
    public static void findFiles(String path) throws IOException {
        File[] f = new File(path).listFiles();
        for(File vLookUp:f) {
            if (vLookUp.isFile()) {
                countLines(vLookUp.getPath(), vLookUp.getName());
            } else {
                findFiles(vLookUp.toString());
            }
        }
    }
    
    public static void countLines(String filePath, String fileName) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
                        
            if (count == 0 && !empty) {
                writeData(fileName, 1, filePath);
            } else {
                writeData(fileName, count, filePath);
            }
            
        } finally {
            is.close();
        }
    }
    
    public static void writeData(String fileName, int count, String filePath) {
        Pair p = new Pair(fileName, count, filePath);
        MY_LIST.add(p);        
    }    
}
