////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemImpl;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.OrderImpl;
import it.unipd.tos.model.User;
import it.unipd.tos.model.UserImpl;

public class TakeAwayBillTest {

    private TakeAwayBill takeAwayBill;
    private MenuItem cornetto;
    private MenuItem ghiacciolo;
    private MenuItem pannaCotta;
    private MenuItem fanta;
    private Order orderInTime;
    private Order orderOutTime;
    private Order orderRandomFalse;
    private User user;
    private User userMin;
    private LocalTime inTime;
    private LocalTime outTime;

    LinkedList<MenuItem> list = new LinkedList<>();

    @Before
    public void setup() {

        fanta = new MenuItemImpl(16156919L, "Fanta", ItemType.BEVANDA, 2.5D);
        cornetto = new MenuItemImpl(164791919L, "Cornetto", ItemType.GELATO, 2.7D);
        pannaCotta = new MenuItemImpl(16131527L, "Panna Cotta", ItemType.BUDINO, 10.0D);
        ghiacciolo = new MenuItemImpl(18591589L, "Ghiacciolo", ItemType.GELATO, 0.8D);
        
        inTime = LocalTime.parse("18:30:00.00");
        outTime = LocalTime.parse("20:00:00.00");
        
        takeAwayBill = new TakeAwayBillImpl();
        user = new UserImpl(56718903L, "Pinco", 19);
        userMin = new UserImpl(56752903L, "Tizio", 16);
        orderInTime = new OrderImpl(34529812L, inTime, true);
        orderOutTime = new OrderImpl(34529812L, outTime, true);
        orderRandomFalse = new OrderImpl(34529812L, inTime, false);
        list = new LinkedList<>();

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullItems() throws TakeAwayBillException {
        takeAwayBill.getOrderPrice(null, user, orderInTime);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPrice0Items() throws TakeAwayBillException {
        takeAwayBill.getOrderPrice(list, user, orderInTime);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullUser() throws TakeAwayBillException {
        list.add(fanta);
        takeAwayBill.getOrderPrice(list, null, orderInTime);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullOrder() throws TakeAwayBillException {
        list.add(fanta);
        takeAwayBill.getOrderPrice(list, user, null);
    }
    
    @Test(expected = TakeAwayBillException.class)
    public void test_getOrderPrice33Items() throws TakeAwayBillException {
        for (int i = 0; i < 11; i++) {
            list.add(fanta);
            list.add(cornetto);
            list.add(pannaCotta);
        }
        takeAwayBill.getOrderPrice(list, user, orderInTime);
    }
    
    @Test
    public void test_getOrderPriceTotal() throws TakeAwayBillException {
        list.add(cornetto);
        list.add(pannaCotta);
        list.add(fanta);
        assertEquals(15.2D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPriceFeeApplied() throws TakeAwayBillException {
        list.add(fanta);
        assertEquals(3D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPrice50DiscountApplied() throws TakeAwayBillException {
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(fanta);
        assertEquals(17.35D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPrice10DiscountApplied() throws TakeAwayBillException {
        list.add(pannaCotta);
        list.add(pannaCotta);
        list.add(pannaCotta);
        list.add(pannaCotta);
        list.add(pannaCotta);
        list.add(cornetto);
        assertEquals(47.43D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPrice10Discount50DiscountApplied() throws TakeAwayBillException {
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(cornetto);
        list.add(pannaCotta);
        list.add(pannaCotta);
        list.add(pannaCotta);
        list.add(pannaCotta);
        assertEquals(49.365D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPrice50Fee50DiscountApplied() throws TakeAwayBillException {
        list.add(ghiacciolo);
        list.add(ghiacciolo);
        list.add(ghiacciolo);
        list.add(ghiacciolo);
        list.add(ghiacciolo);
        list.add(cornetto);
        assertEquals(6.8D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPriceFreeRandomOrderApplied() throws TakeAwayBillException {
        list.add(pannaCotta);
        assertEquals(0D, takeAwayBill.getOrderPrice(list, userMin, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPriceFreeRandomOrderAdult() throws TakeAwayBillException {
        list.add(pannaCotta);
        assertEquals(10.0D, takeAwayBill.getOrderPrice(list, user, orderInTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPriceFreeRandomOrderOutTime() throws TakeAwayBillException {
        list.add(pannaCotta);
        assertEquals(10.0D, takeAwayBill.getOrderPrice(list, userMin, orderOutTime), 0.0001D);
    }
    
    @Test
    public void test_getOrderPriceFreeRandomFalse() throws TakeAwayBillException {
        list.add(pannaCotta);
        assertEquals(10.0D, takeAwayBill.getOrderPrice(list, userMin, orderRandomFalse), 0.0001D);
    }

}
