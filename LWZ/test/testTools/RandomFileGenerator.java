/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testTools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author markus
 */
public class RandomFileGenerator {
    
    
    public void generateFile (FileOutputStream out, int maxWordLength,
            int randomWordsAmount, int wordsInFile) throws IOException{
        int[][] randomWords = new int[randomWordsAmount][];
        Random r = new Random();
        for (int i = 0; i < randomWordsAmount; i++){
            int wordLength = 1 + r.nextInt(maxWordLength);
            randomWords[i] = new int[wordLength];
            for (int j = 0; j < wordLength; j++){
                randomWords[i][j] = r.nextInt(256);
            }
        }        
        for (int i = 0; i < wordsInFile; i++){
            int wordInd = r.nextInt(randomWordsAmount);
            for (int bYte : randomWords[wordInd]) {
                out.write(bYte);
            }           
        }
        out.close();
    }
}
