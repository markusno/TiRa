
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
        File fOut = new File("testout.txt");
        FileInputStream a;
        FileOutputStream b;
        ArrayList<Integer> t;
        try {
            a = new FileInputStream(fIn);
            b = new FileOutputStream("testout.txt");
            t = c.compres(a);
            System.out.println(t);
            String p = d.deCompres(t, b);
            System.out.println(a);
            System.out.println(p);
            System.out.println(t.size() + " " + p.length());
        } catch (Exception ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
