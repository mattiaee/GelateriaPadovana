////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemImpl;
import it.unipd.tos.model.User;
import it.unipd.tos.model.UserImpl;

public class TakeAwayBillTest {

    private TakeAwayBill takeAwayBill;
    private MenuItem cornetto;
    private MenuItem sandwich;
    private MenuItem creamCaramel;
    private MenuItem cola;
    private MenuItem fanta;
    private User user;
    private User nullUser;

    LinkedList<MenuItem> list = new LinkedList<>();

    @Before
    public void setup() {

        fanta = new MenuItemImpl(16156919L, "Fanta", ItemType.BEVANDA, 2.5D);
        cola = new MenuItemImpl(16193019L, "CocaCola", ItemType.BEVANDA, 2.5D);
        cornetto = new MenuItemImpl(164791919L, "Cornetto", ItemType.GELATO, 2.7D);
        creamCaramel = new MenuItemImpl(16131919L, "Cream Caramel", ItemType.BUDINO, 5.5D);
        sandwich = new MenuItemImpl(16191589L, "Sandwich", ItemType.GELATO, 2.2D);

        takeAwayBill = new TakeAwayBillImpl();
        user = new UserImpl(56718903L, "Pinco");
        list = new LinkedList<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullItems() throws TakeAwayBillException {
        takeAwayBill.getOrderPrice(null, user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPrice0Items() throws TakeAwayBillException {
        takeAwayBill.getOrderPrice(list, user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullUser() throws TakeAwayBillException {
        list.add(cola);
        nullUser = null;
        takeAwayBill.getOrderPrice(list, nullUser);
    }

    @Test
    public void test_getOrderPriceTotal() throws TakeAwayBillException {
        list.add(sandwich);
        list.add(cornetto);
        list.add(creamCaramel);
        list.add(fanta);
        assertEquals(12.9D, takeAwayBill.getOrderPrice(list, user), 0.0001D);
    }

}
