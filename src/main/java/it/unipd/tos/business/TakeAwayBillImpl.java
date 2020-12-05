////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
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

        double result = 0;

        for (MenuItem m : itemsOrdered) {
            result += m.getPrice();
        }

        return result;

    }

}
