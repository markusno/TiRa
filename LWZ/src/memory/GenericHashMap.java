/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.lang.reflect.Array;
import java.lang.Class;

/**
 *
 * @author markus
 */
public class GenericHashMap<Key, Value> {

    private GenericList[] keys;
    private GenericList[] values;
    private int size;
    private int maxValuesPerList;
    private int maxSize;

    public GenericHashMap(int initSize, int maxValuesPerList) {
        this.maxSize = initSize;
        this.keys = new GenericList[initSize];
        this.values = new GenericList[initSize];
        this.maxValuesPerList = maxValuesPerList;
        this.size = 0;
    }

    public GenericHashMap(int initSize) {
        this(initSize, 10);
    }

    public GenericHashMap() {
        this(16, 10);
    }

    @SuppressWarnings({"unchecked"})
    public Value get(Object key) {
        int index = getHashIndex(key);
        if (keys[index] != null) {
            for (int i = 0; i < keys[index].size; i++) {
                if (keys[index].get(i).equals(key)) {
                    return (Value)values[index].get(i);
                }
            }
        }
        return null;
    }
    
    public void put(Object key, Object value) {
        int index = getHashIndex(key);
        if (keys[index] != null) {
            if (keys[index].size >= maxValuesPerList || size >= maxSize*2/3) {
                reHash();
                this.put(key, value);
                return;
            }
            for (int i = 0; i < keys[index].size; i++) {
                if (keys[index].get(i).equals(key)) {
                    values[index].set(i, key);
                    return;
                }
            }
        } else {
            keys[index] = new GenericList<Key>();
            values[index] = new GenericList<Value>();
        }
        keys[index].add(key);
        values[index].add(value);
        size++;
    }

    public boolean containsKey(Object key) {
        int index = getHashIndex(key);
        if (keys[index] != null) {
            for (int i = 0; i < keys[index].size; i++) {
                if (keys[index].get(i).equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int size () {
        return size;
    }

    private void reHash() {
        maxSize *= 2;
        GenericList[] tempKeys = new GenericList[maxSize];
        GenericList[] tempValues = new GenericList[maxSize];
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null){
                for (int j = 0; j < keys[i].size(); j++){
                    int newIndex = getHashIndex(keys[i].get(j));
                    if (tempKeys[newIndex] == null){
                        tempKeys[newIndex] = new GenericList<Key>();
                        tempValues[newIndex] = new GenericList<Value>();
                    }
                    tempKeys[newIndex].add(keys[i].get(j));
                    tempValues[newIndex].add(values[i].get(j));
                }
            }
        }
        keys = tempKeys;
        values = tempValues;        
    }

    private int getHashIndex(Object o) {
        double a = (Math.sqrt(5) - 1) / 2;
        int k = Math.abs(((Key) o).hashCode());
        return (int) Math.floor(maxSize * (a * k - (int) (a * k)));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keys.length; i++){
            if (keys[i] == null){
                continue;
            }
            for (int j = 0; j < keys[i].size; j++){
                builder.append("{[").append(keys[i].get(j)).append("]:[").append(values[i].get(j)).append("]}");
            }
        }
        return builder.toString();
    }
    
    
}
