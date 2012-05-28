/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author markus
 */
public class Compressor {

    private HashMap<String, Integer> codeDictionary;

    public Compressor() {
        codeDictionary = new HashMap<String, Integer>();
        initDictionary();
    }

    private void initDictionary() {
        for (int i = 0; i < 256; i++) {
            codeDictionary.put(Character.toString((char) (i)), i);
        }
    }

    public ArrayList<Integer> compres(FileInputStream input, FileOutputStream output) throws IOException {
        ArrayList<Integer> compressed = new ArrayList<Integer>();
        String currentString = "";
        char currentChar;
        while (input.available() > 0) {
            currentChar = (char) input.read();
            if (!codeDictionary.containsKey(currentString + currentChar)) {
                Integer currentCode = codeDictionary.get(currentString);
                codeDictionary.put(currentString + currentChar, codeDictionary.size());
                compressed.add(currentCode);
                currentString = "";
            }
            currentString += currentChar;
        }
        compressed.add(codeDictionary.get(currentString));
        toFile(compressed, output);
        return compressed;
    }

    private void toFile(ArrayList<Integer> codes, FileOutputStream output) throws IOException {
        int index = 0;
        int code;
        while (index < codes.size()) {
            boolean[] bits = new boolean[24];
            for (int i = 0; i < 2; i++) {
                if (index < codes.size()) {
                    code = codes.get(index);
                } else {
                    code = 0;
                }
                System.out.println(code);
                for (int j = 0; j < 12; j++) {
                    bits[j + 12*i] = (code & (1 << (11 - j))) != 0;

                    //System.out.println(bits[j + 12*i]);
                }
                index++;
            }
            //System.out.println(bits);
            for (int i = 0; i < 3; i++) {
                int bYte = 0;
                for (int j = 0; j < 8; j++) {
                    
                    if (bits[j + 8 * i]) {
                        //System.out.println(j + 8 * i);
                        bYte += (1 << (7-j));
                    }
                }
                //System.out.println(bYte);
                output.write(bYte);
            }
        }
        output.close();
    }
}
