/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import memory.*;

/**
 * Object from this class reads file, compressed with Compressor, from FileInputStream and writes decompressed data into FileOutputStream.
 * This decompressor uses lossless LZW (Lempel-Ziv-Welch) algorithm to decompress information.
 * It is useful only with files that have repetitive information.
 * @author markus
 * @see Compressor
 */
public class DeCompressor {

    private GenericHashMap<Integer, String> stringDictionary;

    /**
     * Constructs DeCompressor object.
     */
    public DeCompressor() {
        stringDictionary = new GenericHashMap<Integer, String>();
        initDictionary();
    }

    private void initDictionary() {
        for (int i = 0; i < 256; i++) {
            stringDictionary.put(i, Character.toString((char) (i)));
        }
    }

    /**
     * Reads compressed file from FileInputStream decompresses it and writes it into FileOutputStream.
     * @param input FileInputStream constructed using file outputted by Compressor object.
     * @param output FileOutputStream constructed with file into which decompressed file is wanted.
     * @throws IOException 
     * 
     */
    public void deCompress(FileInputStream input, FileOutputStream output) throws IOException {

        String previous = "";
        GenericList<Integer> codes = codesFromFile(input);
        String[] deCompressed = new String[codes.size()];
        int index = 0;
        for (int i = 0; i < codes.size(); i++) {
            int code = codes.get(i);
            if (!stringDictionary.containsKey(code)) {
                stringDictionary.put(code, previous + previous.charAt(0));
            } else if (previous.length() > 0 && stringDictionary.size() < 4096) {
                stringDictionary.put(stringDictionary.size(),
                        previous + stringDictionary.get(code).charAt(0));
            }
            deCompressed[index] = stringDictionary.get(code);
            index++;
            previous = stringDictionary.get(code);
        }
        for (String string : deCompressed) {
            for (int i = 0; i < string.length(); i++) {
                output.write((int) string.charAt(i));
            }
        }
        output.close();
    }

    private GenericList<Integer> codesFromFile(FileInputStream input) throws IOException {
        GenericList<Integer> codes = new GenericList<Integer>();
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
                if (input.available() > 3 || code != 0) {
                    codes.add(code);
                }
            }
        }
        return codes;
    }
}
