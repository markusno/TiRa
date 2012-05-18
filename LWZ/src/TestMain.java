
import algo.Compressor;
import algo.DeCompressor;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author markus
 */
public class TestMain {
    
    public static void main(String[] args) {
        Compressor c = new Compressor();
        DeCompressor d = new DeCompressor();
        ArrayList<Integer> t = c.compres("ABBABBBABBA");
        System.out.println(t);
        String a = d.deCompres(t);
        System.out.println(a);
    }
}
