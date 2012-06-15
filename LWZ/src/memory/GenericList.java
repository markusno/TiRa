/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.Iterator;

/**
 *
 * @author markus
 */
public class GenericList<Value>{

    Object[] values;
    int size;

    public GenericList() {
        values = new Object[10];
        size = 0;
    }

    public GenericList(int initSize) {
        values = new Object[initSize];
        size = 0;
    }

    private void increaseSize() {
        Object[] temp = new Object[values.length * 2];
        System.arraycopy(values, 0, temp, 0, size);
        values = temp;
    }

    public void add(Object o) {
        if (size == values.length) {
            increaseSize();
        }
        values[size] = o;
        size++;
    }
    
    public void set(int i, Object o){
        if (i >= 0 && i < size) {
            values[i] = o;
        }
    }

    @SuppressWarnings({"unchecked"})
    public Value get(int i) {
        if (i >= 0 && i < size) {
            return (Value) values[i];
        }
        return null;
    }
    
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
