/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.FileInputStream;
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

    public DeCompressor() {
        stringDictionary = new HashMap<Integer, String>();
        initDictionary();
    }

    private void initDictionary() {
        for (int i = 0; i < 256; i++) {
            stringDictionary.put(i, Character.toString((char) (i)));
        }
    }

    public String deCompres(FileInputStream input, FileOutputStream output) throws IOException {
        String deCompressed = "";
        String previous = "";
        ArrayList<Integer> codes = codesFromFile(input);
        for (Integer code : codes) {
            if (!stringDictionary.containsKey(code)) {
                stringDictionary.put(code, previous + previous.charAt(0));
                //System.out.println(previous + previous.charAt(0));
            }
            
            else if (previous.length() > 0 && stringDictionary.size() < 4096) {
                stringDictionary.put(stringDictionary.size(),
                        previous + stringDictionary.get(code).charAt(0));
                //System.out.println(previous + stringDictionary.get(code).charAt(0));
            }
            deCompressed += stringDictionary.get(code);
            previous = stringDictionary.get(code);
        }
        for (int i = 0; i < deCompressed.length(); i++) {
            output.write((int) deCompressed.charAt(i));
        }
        output.close();
        return deCompressed;
    }

    ArrayList<Integer> codesFromFile(FileInputStream input) throws IOException {
        ArrayList<Integer> codes = new ArrayList<Integer>();
        boolean[] bits = new boolean[24];
        while (input.available() > 0) {
            for (int i = 0; i < 3; i++) {
                int bYte = input.read();
                for (int j = 0; j < 8; j++) {
                    bits[j + 8 * i] = (bYte & (1 << (7 - j))) != 0;
                }
            }
            for (int i = 0; i < 2; i++) {
                int code = 0;
                for (int j = 0; j < 12; j++) {
                    if (bits[j + 12 * i]) {
                        code += (1 << (11 - j));
                    }
                }
                //System.out.println(code);
                if (input.available() > 3 || code != 0) {
                    codes.add(code);
                }
            }
        }
        return codes;
    }
}
