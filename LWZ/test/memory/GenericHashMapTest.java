/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.Random;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author markus
 */
public class GenericHashMapTest {
    
    private GenericHashMap<String, Integer> stringIntegerMap;
    private GenericHashMap<Integer, String> integerStringMap;
    
    public GenericHashMapTest() {
    }
    
    private String randomString () {
        StringBuilder builder = new StringBuilder();
        Random r = new Random();
        int length = r.nextInt(200);
        for (int i = 0; i < length; i++){
            builder.append((char)r.nextInt(256));
        }
        return builder.toString();
    }
    
    @Before
    public void setUp() {
        stringIntegerMap = new GenericHashMap<String, Integer>();
        integerStringMap = new GenericHashMap<Integer, String>();
        for (int i = 0; i < 200; i++){
            String word = randomString();
            stringIntegerMap.put(word, i);
            integerStringMap.put(i, word);
        }
    }

    @Test
    public void testPutAndGetStringInteger() {
        String key = "key";
        int value = 12345;
        stringIntegerMap.put(key, value);
        assertEquals((Integer)12345, stringIntegerMap.get(key));
 
    }

    @Test
    public void testPutAndGetIntegerString() {
        String value = "value";
        int key = 12345;
        stringIntegerMap.put(key, value);
        assertEquals("value", stringIntegerMap.get(key));
    }

    @Test
    public void testContainsKeyTrue() {
        assertEquals(true, integerStringMap.containsKey(10));
    }

    @Test
    public void testSize() {
        stringIntegerMap = new GenericHashMap<String, Integer>();
        String s = "";
        for (int i = 0; i < 200; i++){
            s += "a";
            stringIntegerMap.put(s, i);
        }
        assertEquals(200, stringIntegerMap.size());

    }
}
