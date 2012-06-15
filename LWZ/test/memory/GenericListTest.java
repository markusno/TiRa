/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author markus
 */
public class GenericListTest {
    
    GenericList<Integer> list;
    
    public GenericListTest() {
    }
    
    @Before
    public void setUp() {
        list = new GenericList<Integer>(5);
    }

    @Test
    public void testAddAndGet() {
        for (int i = 0; i < 50; i++){
            list.add(i);
        }
        assertEquals((Integer)49, list.get(49));
    }

}
