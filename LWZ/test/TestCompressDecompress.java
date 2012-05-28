/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import algo.Compressor;
import algo.DeCompressor;
import java.io.*;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author markus
 */
public class TestCompressDecompress {
    
    Compressor compressor;
    DeCompressor decompressor;
    File original;
    File compressed;
    File decompressed;
    String message;
    
    public TestCompressDecompress() {
    }
    
    @Before
    public void setUp() {
        compressor = new Compressor();
        decompressor = new DeCompressor();
        original = new File("test.txt");
        compressed = new File("testcomp.txt");
        decompressed = new File("testout.txt");       
    }
    
    private void initOriginal() throws FileNotFoundException, IOException{
        FileOutputStream out = new FileOutputStream(original);
        Random r = new Random();
        for (int i = 0; i < 2000; i++){
            out.write(r.nextInt(256));
        }
        out.close();
    }
    
    private boolean comparison() throws FileNotFoundException, IOException{
        FileInputStream org = new FileInputStream(original);
        FileInputStream decomp = new FileInputStream(decompressed);
        
        int i = 0;
        while (org.available() > 0){
            char o = (char)org.read();
            char d = (char)decomp.read();
            if (o != d){
                message += i +". tavu väärin pitäisi olla: [" + o + "] on: [" + d + "]";
                org.close();
                decomp.close();
                return false;
            }
            i++;
        }
        if (org.available() != decomp.available()){
            message += "eri koko";
            return false;
        }
        return true;
    }
    
    @Test
    public void testCompressDecompress()throws IOException{
        //initOriginal();
        FileInputStream in = new FileInputStream(original);
        FileOutputStream out = new FileOutputStream(compressed);
        compressor.compres(in, out);
        in = new FileInputStream(compressed);
        out = new FileOutputStream(decompressed);
        decompressor.deCompres(in, out);
        boolean match = comparison();
        assertEquals(message, true, match);
    }
}
