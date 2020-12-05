////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {

    private MenuItem item;

    @Before
    public void setup() {
        item = new MenuItemImpl(16156919L, "Fanta", ItemType.BEVANDA, 2.5D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroIdParam() {
        new MenuItemImpl(0L, "Fanta", null, 2.5D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullItemTypeParam() {
        new MenuItemImpl(16156919L, "Fanta", null, 2.5D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullNameParam() {
        new MenuItemImpl(16156919L, null, ItemType.BEVANDA, 2.5D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroLengthNameParam() {
        new MenuItemImpl(16156919L, "", ItemType.BEVANDA, 2.5D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNegativePriceParam() {
        new MenuItemImpl(16156919L, "Fanta", ItemType.BEVANDA, -2.5D);
    }

    @Test
    public void testIdGetter() {
        assertEquals(16156919L, item.getId());
    }

    @Test
    public void testNameGetter() {
        assertEquals("Fanta", item.getName());
    }

    @Test
    public void testPriceGetter() {
        assertEquals(2.5D, item.getPrice(), 0.0001D);
    }

    @Test
    public void testTypeGetter() {
        assertEquals(ItemType.BEVANDA, item.getType());
    }

}
