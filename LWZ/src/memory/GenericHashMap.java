/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

/**
 * Hash table type of data structure to store key-value pairs.
 * Both keys and values can be of any type of java objects.
 * GenericHashMap uses overflow lists to store keys with same hashed index.
 * When size of overflow list reaches threshold level or when amount of key-value 
 * pairs reaches 2/3 of maximum possible indexes amount, amount of maximum 
 * possible indexes is doubled and keys rehashed (this doesn't  guarantee that 
 * non of overflow lists is longer than threshold).
 * @author markus
 */
public class GenericHashMap<Key, Value> {

    private GenericList[] keys;
    private GenericList[] values;
    private int size;
    private int overflowListsTresholdSize;
    private int maxSize;

    
    /**
     * Constructs GenericHashMap.
     * 
     * @param initSize defines initial maximum number of indexes.
     * @param overflowListsTresholdSize defines over flow lists threshold size.
     */
    public GenericHashMap(int initSize, int overflowListsTresholdSize) {
        this.maxSize = initSize;
        this.keys = new GenericList[initSize];
        this.values = new GenericList[initSize];
        this.overflowListsTresholdSize = overflowListsTresholdSize;
        this.size = 0;
    }

    /**
     * Constructs GenericHashMap with over flow lists threshold size at 10.
     * @param initSize defines initial maximum number of indexes.
     */
    public GenericHashMap(int initSize) {
        this(initSize, 10);
    }

    /**
     * Constructs GenericHashMap with initial maximum number of indexes at 16 and over flow lists threshold size at 10.
     */
    public GenericHashMap() {
        this(16, 10);
    }

    /**
     * Returns value object corresponding to key object given as parameter.
     * @param key Any object of type that is used as key in current GenericHashMap.
     * @return null if key is not found otherwise corresponding value object.
     */
    @SuppressWarnings({"unchecked"})
    public Value get(Object key) {
        int index = getHashIndex(key);
        if (keys[index] != null) {
            for (int i = 0; i < keys[index].size; i++) {
                if (keys[index].get(i).equals(key)) {
                    return (Value) values[index].get(i);
                }
            }
        }
        return null;
    }

    /**
     * Stores key-value pair.
     * If there's already equal key object stored, corresponding value is changed.
     * @param key Any object of type that is used as key in current GenericHashMap.
     * @param value Any object of type that is used as value in current GenericHashMap.
     */
    public void put(Object key, Object value) {
        int index = getHashIndex(key);
        if (keys[index] != null) {
            if (keys[index].size >= overflowListsTresholdSize || size >= maxSize * 2 / 3) {
                reHash();
                index = getHashIndex(key);
            }
        }
        if (keys[index] != null) {
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

    /**
     * Checks if equal key object given as parameter is to be found in current GenericHashMap.
     * @param key Any object of type that is used as key in current GenericHashMap.
     * @return true if equal key is found otherwise false.
     */
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
    
    /**
     * 
     * @return amount of key-value pairs stored in current GenericHashMap as integer. 
     */
    public int size() {
        return size;
    }

    private void reHash() {
        maxSize *= 2;
        GenericList[] tempKeys = new GenericList[maxSize];
        GenericList[] tempValues = new GenericList[maxSize];
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                for (int j = 0; j < keys[i].size(); j++) {
                    int newIndex = getHashIndex(keys[i].get(j));
                    if (tempKeys[newIndex] == null) {
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
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) {
                continue;
            }
            for (int j = 0; j < keys[i].size; j++) {
                builder.append("{[").append(keys[i].get(j)).append("]:[").append(values[i].get(j)).append("]}");
            }
        }
        return builder.toString();
    }
}
