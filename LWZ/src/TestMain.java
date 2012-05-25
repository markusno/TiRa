
import algo.Compressor;
import algo.DeCompressor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author markus
 */
public class TestMain {

    public static void main(String[] args) {
        Compressor c = new Compressor();
        DeCompressor d = new DeCompressor();
        File fIn = new File("test.txt");
        File fComp = new File("testcomp.txt");
        File fOut = new File("testout.txt");
        FileInputStream a;
        FileOutputStream b;
        try {
            a = new FileInputStream(fIn);
            b = new FileOutputStream(fComp);
            c.compres(a, b);
            a = new FileInputStream(fComp);
            b = new FileOutputStream(fOut);
            String p = d.deCompres(a, b);
            //System.out.println(a);
            System.out.println(p);
            
        } catch (Exception ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
