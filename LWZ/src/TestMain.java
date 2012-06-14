
import algo.Compressor;
import algo.DeCompressor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import testTools.FileComparator;
import testTools.RandomFileGenerator;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author markus
 */
public class TestMain {

    private static String[] testFiles = { 
        "PrideAndPrejudice.txt", "PrideAndPrejudice.pdf",
    "Leafy_wallpaper.bmp", "Leafy_wallpaper.jpg", "bible.txt"};
    
    private static void testFile(File file){
        Compressor comp = new Compressor();
        DeCompressor deComp = new DeCompressor();
        FileComparator compare = new FileComparator();
        System.out.println("Alkuperäinen tiedosto: " + file.getName());
        try {
            FileInputStream input = new FileInputStream(file);
            System.out.println("Alkuperäisen koko: " + (input.available()/1000) + " kilotavua");
            FileOutputStream output = new FileOutputStream("compressed");
            Long startTime = System.currentTimeMillis();
            comp.compres(input, output);
            Long finishTime = System.currentTimeMillis();
            System.out.println("Pakkaamiseen kului: " + (finishTime-startTime) + " ms");
            FileInputStream compressed = new FileInputStream("compressed");
            input = new FileInputStream(file);
            System.out.println("Pakatun koko: " + (compressed.available()/1000) + " kilotavua ("
                    + compare.fileSizeRatio(input, compressed) + " % alkuperäisestä)");
            output = new FileOutputStream("decomp_"+file.getName());
            startTime = System.currentTimeMillis();
            deComp.deCompres(compressed, output);
            finishTime = System.currentTimeMillis();
            System.out.println("Purkamiseen kului: " + (finishTime-startTime) + " ms");
            FileInputStream deCompressed = new FileInputStream("decomp_"+file.getName());
            System.out.println("Purettu vastaa alkuperäistä: " + compare.equalFiles(input, deCompressed));
            
        } catch (Exception ex) {
            System.out.println("Tiedotossa ongelma: " + ex.getMessage());
            return;
        }
        
    }
    
    public static void main(String[] args) {        
        for (String fileName : testFiles) {
            System.out.println("-----------------------------");
            File file = new File(fileName);
            testFile(file);
        }
        
        RandomFileGenerator generator = new RandomFileGenerator();
        FileOutputStream output;
        
        for (int i = 20000; i <= 100000; i += 20000){
            System.out.println("---------------------");
            String fileName = i+"_random_words";
            try {
                output = new FileOutputStream(fileName);
                generator.generateFile(output, 10, 300, i);
                testFile(new File(fileName));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        }

    }
}
