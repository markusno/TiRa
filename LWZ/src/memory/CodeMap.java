/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author markus
 */
public class CodeMap {
    
    private int[] codes;
    private String[] words;
    private int currentSize;
    private int maxSize;
    
    public CodeMap () {
        codes = new int[5333];
        words = new String[5333];
        currentSize = 0;
        maxSize = 4096;
    }
    
//    public CodeMap (int initSize) {
//        
//    }

    private int hashIndex (String string) {
        
        return 0;
    }
    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object get(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
//
//    @Override
//    public Object remove(Object key) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void putAll(Map m) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Set keySet() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Collection values() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Set entrySet() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
