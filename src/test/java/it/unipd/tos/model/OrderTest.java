////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Order order;
    private LocalTime orderTime = LocalTime.now();
    
    @Before
    public void setup() {
        order = new OrderImpl(34529812L, orderTime, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroIdParam() {
        new OrderImpl(0, orderTime, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullTimeParam() {
        new OrderImpl(34529812L, null, false);
    }

    @Test
    public void testIdGetter() {
        assertEquals(34529812L, order.getId());
    }

    @Test
    public void testTimeGetter() {
        assertEquals(orderTime, order.getTime());
    }
    
    @Test
    public void testRandomFreeGetter() {
        assertEquals(false, order.getRandomFree());
    }

}
