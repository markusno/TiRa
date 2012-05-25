/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author markus
 */
public class Compressor {
    
    private HashMap<String, Integer> codeDictionary;
    
    public Compressor(){
        codeDictionary = new HashMap<>();
        initDictionary();
    }
    
    private void initDictionary(){
        for (int i = 0; i < 256; i++){
            codeDictionary.put(Character.toString((char)(i)), i);
        }
    }
    
    public ArrayList<Integer> compres(FileInputStream input) throws IOException{
        ArrayList<Integer> compressed = new ArrayList<>();
        String currentString = "";
        char currentChar;
        while (input.available() > 0){
            currentChar = (char)input.read();
            if (!codeDictionary.containsKey(currentString+currentChar)){
                Integer currentCode = codeDictionary.get(currentString);
                codeDictionary.put
                        (currentString+currentChar, codeDictionary.size());
                compressed.add(currentCode);
                currentString = "";
            }
            currentString += currentChar;
        }
        compressed.add(codeDictionary.get(currentString));
        return compressed;
    }
}
