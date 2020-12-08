////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.User;

public interface TakeAwayBill {
    
    //passare anche orario o testate ordine
    double getOrderPrice(List<MenuItem> itemsOrdered, User user, Order order) throws TakeAwayBillException;
}
