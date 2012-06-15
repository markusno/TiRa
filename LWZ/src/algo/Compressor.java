/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import memory.*;

/**
 * Object from this class reads file from FileInputStream compresses it and writes it into FileoutputStream.
 * This compressor uses lossless LZW (Lempel-Ziv-Welch) algorithm to compress information.
 * It is useful only with files that have repetitive information.
 * @see DeCompressor
 * @author markus
 */
public class Compressor {

    private GenericHashMap<String, Integer> codeDictionary;

    /**
     * Constructs new compressor object.
     */
    public Compressor() {
        codeDictionary = new GenericHashMap<String, Integer>();
        initDictionary();
    }

    private void initDictionary() {
        for (int i = 0; i < 256; i++) {
            codeDictionary.put(Character.toString((char) (i)), i);
        }
    }

    /**
     * Reads file from FileInputStream compresses it and writes it into FileOutputStream.
     * @param input FileInputStream constructed using any type of file.
     * @param output FileOutputStream constructed with file into which compressed file is wanted.
     * @throws IOException 
     * 
     */
    public void compress(FileInputStream input, FileOutputStream output) throws IOException {
        GenericList<Integer> compressed = new GenericList<Integer>();
        String currentString = "";
        char currentChar;
        while (input.available() > 0) {
            currentChar = (char) input.read();
            if (!codeDictionary.containsKey(currentString + currentChar)) {
                Integer currentCode = codeDictionary.get(currentString);
                if (codeDictionary.size() < 4096) {
                    codeDictionary.put(currentString + currentChar, codeDictionary.size());
                }
                compressed.add(currentCode);
                currentString = "";
            }
            currentString += currentChar;
        }
        compressed.add(codeDictionary.get(currentString));
        codesToFile(compressed, output);
    }

    private void codesToFile(GenericList<Integer> codes, FileOutputStream output) throws IOException {
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
                for (int j = 0; j < 12; j++) {
                    bits[j + 12 * i] = (code & (1 << (11 - j))) != 0;
                }
                index++;
            }
            for (int i = 0; i < 3; i++) {
                int bYte = 0;
                for (int j = 0; j < 8; j++) {

                    if (bits[j + 8 * i]) {
                        bYte += (1 << (7 - j));
                    }
                }
                output.write(bYte);
            }
        }
        output.close();
    }
}
