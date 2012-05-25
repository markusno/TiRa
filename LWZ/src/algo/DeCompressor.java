/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author markus
 */
public class DeCompressor {
    
    private HashMap<Integer, String> stringDictionary;
    
    public DeCompressor(){
        stringDictionary = new HashMap<>();
        initDictionary();
    }
    
    private void initDictionary(){
        for (int i = 0; i < 256; i++){
            stringDictionary.put(i, Character.toString((char)(i)));
        }
    }
    
    public String deCompres (ArrayList<Integer> codes, FileOutputStream output) throws IOException{
        String deCompressed = "";
        String previous = "";
        for (Integer code : codes){
            if (!stringDictionary.containsKey(code)){
                stringDictionary.put(code, previous + previous.charAt(0));
            }
            deCompressed += stringDictionary.get(code);            
            if (previous.length() > 0) {
                stringDictionary.put(stringDictionary.size(), 
                        previous + stringDictionary.get(code).charAt(0));
            }
            previous = stringDictionary.get(code);
        }
        for (int i = 0; i < deCompressed.length(); i++){
            output.write((int)deCompressed.charAt(i));
        }
        output.close();
        return deCompressed;
    }
}
