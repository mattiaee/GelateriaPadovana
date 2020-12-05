////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImpl implements TakeAwayBill {

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {

        if (itemsOrdered == null) {
            throw new IllegalArgumentException("ItemsOrder is null");
        }
        if (itemsOrdered.size() == 0) {
            throw new IllegalArgumentException("ItemsOrder is empty");
        }

        if (user == null) {
            throw new IllegalArgumentException("User is not set");
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
        
        return result;

    }

}
