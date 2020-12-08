////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setup() {
        user = new UserImpl(34529875L, "Pinco", 19);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroIdParam() {
        new UserImpl(0, "Pinco", 19);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullNameParam() {
        new UserImpl(34529875L, null, 19);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroLengthNameParam() {
        new UserImpl(34529875L, "", 19);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroAgeParam() {
        new UserImpl(34529875L, "Pinco", 0);
    }

    @Test
    public void testIdGetter() {
        assertEquals(34529875L, user.getId());
    }

    @Test
    public void testNameGetter() {
        assertEquals("Pinco", user.getName());
    }
    
    @Test
    public void testAgeGetter() {
        assertEquals(19, user.getAge());
    }

}
