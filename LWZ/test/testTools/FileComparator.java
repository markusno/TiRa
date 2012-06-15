/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author markus
 */
public class FileComparator {

    public boolean equalFiles(FileInputStream original, FileInputStream newFile) {
        try {
            int i = 0;
            while (original.available() > 0) {
                char o = (char) original.read();
                char d = (char) newFile.read();
                if (o != d) {
                    original.close();
                    newFile.close();
                    return false;
                }
                i++;
            }
            if (newFile.available() == 0) {
                return true;
            }
        } catch (IOException ex) {            
        }
        return false;
    }

    public int fileSizeRatio(FileInputStream file1, FileInputStream file2) {
        int ratio = 0;
        try {
            ratio = file2.available() * 100 / file1.available();
        } catch (IOException ex) {
            Logger.getLogger(FileComparator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ratio;
    }
}
