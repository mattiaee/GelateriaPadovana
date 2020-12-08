////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.time.LocalTime;
import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.User;

public class TakeAwayBillImpl implements TakeAwayBill {
    
    private int ordersCounter = 0;
    LocalTime freeFrom = LocalTime.parse("18:00:00.00");
    LocalTime freeTo = LocalTime.parse("19:00:00.00");
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, Order order) throws TakeAwayBillException {
        
        if (itemsOrdered == null) {
            throw new IllegalArgumentException("ItemsOrder is null");
        }
        if (itemsOrdered.size() == 0) {
            throw new IllegalArgumentException("ItemsOrder is empty");
        }

        if (user == null) {
            throw new IllegalArgumentException("User is not set");
        }
        
        if (order == null) {
            throw new IllegalArgumentException("Order is not set");
        }
        
        if(itemsOrdered.size() > 30) {
            throw new TakeAwayBillException();
        }
        
        double result = 0;
        int nIcecream = 0;
        double totIcecremAndPudding = 0D;
        MenuItem cheapestIcecream = null;
        
        for (MenuItem m : itemsOrdered) {
            
            result += m.getPrice();
            
            if(m.getType() == ItemType.GELATO) {
                nIcecream++;
                if(cheapestIcecream == null ||
                        cheapestIcecream.getPrice() > m.getPrice()) {
                    cheapestIcecream = m;
                }
            }
            
            if(m.getType() == ItemType.GELATO || m.getType() == ItemType.BUDINO) {
                totIcecremAndPudding += m.getPrice();
            }
            
        }
        
        if(nIcecream > 5) {
            result -= cheapestIcecream.getPrice() * 0.5D;
        }
        
        if(totIcecremAndPudding > 50D) {
            result -= result * 0.1D; 
        }
        
        if(result < 10D) {
            result += 0.5D;
        }
        
        if(ordersCounter < 10 && order.getRandomFree() && user.getAge() < 18) {
            if(order.getTime().isAfter(freeFrom) && order.getTime().isBefore(freeTo)) {
                result = 0D;
            }
        }
            
        
        return result;

    }

}
