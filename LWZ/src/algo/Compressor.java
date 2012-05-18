/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

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
    
    public ArrayList<Integer> compres(String text){
        ArrayList<Integer> compressed = new ArrayList<>();
        String currentString = "";
        char currentChar;
        for (int i = 0; i < text.length(); i++){
            currentChar = text.charAt(i);
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
