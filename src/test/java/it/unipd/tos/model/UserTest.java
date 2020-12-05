////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setup() {
        user = new UserImpl(34529875L, "Pinco");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroIdParam() {
        new UserImpl(0L, "Pinco");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullNameParam() {
        new UserImpl(34529875L, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroLengthNameParam() {
        new UserImpl(34529875L, "");
    }

    @Test
    public void testIdGetter() {
        assertEquals(34529875L, user.getId());
    }

    @Test
    public void testNameGetter() {
        assertEquals("Pinco", user.getName());
    }

}
