/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.Iterator;

/**
 * Dynamically growing list type of data structure.
 * Can store any type of java objects.
 * @author markus
 */
public class GenericList<Value>{

    Object[] values;
    int size;

    /**
     * Constructs new GenericList object with initial capacity 10.
     */
    public GenericList() {
        values = new Object[10];
        size = 0;
    }

    /**
     * Constructs new GenericList object.
     * @param initSize defines initial capacity.
     */
    public GenericList(int initSize) {
        values = new Object[initSize];
        size = 0;
    }

    private void increaseSize() {
        Object[] temp = new Object[values.length * 2];
        System.arraycopy(values, 0, temp, 0, size);
        values = temp;
    }

    /**
     * Stores object given as parameter to the list.
     * @param o Any object of type used as value in current list.
     */
    public void add(Object o) {
        if (size == values.length) {
            increaseSize();
        }
        values[size] = o;
        size++;
    }
    
    /**
     * Replaces value in index given as parameter with object given as parameter.
     * @param i index should be between zero and size of list - 1.
     * @param o Any object of type used as value in current list.
     */
    public void set(int i, Object o){
        if (i >= 0 && i < size) {
            values[i] = o;
        }
    }

    /**
     * Returns value from corresponding index.
     * @param i index should be between zero and size of list - 1
     * @return null if index out of bounds or if no value in index otherwise corresponding value.
     */
    @SuppressWarnings({"unchecked"})
    public Value get(int i) {
        if (i >= 0 && i < size) {
            return (Value) values[i];
        }
        return null;
    }
    
    /**
     *
     * @return amount of objects stored in list.
     */
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object object : values) {
            builder.append("[").append(((Value)object)).append("]");
        }
        return builder.toString();
    }
    
    

}
