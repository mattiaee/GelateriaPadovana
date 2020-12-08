////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.time.LocalTime;

public class OrderImpl implements Order {
    
    private long id;

    private LocalTime time;
    
    private boolean randomFree;

    public OrderImpl(long id, LocalTime time, boolean randomFree) {
        super();
        if (id == 0) {
            throw new IllegalArgumentException("Id is not valid");
        }
        if (time == null) {
            throw new IllegalArgumentException("Time is not valid");
        }
        this.id = id;
        this.time = time;
        this.randomFree = randomFree;
    }

    public long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }
    
    public boolean getRandomFree() {
        return randomFree;
    }
    
}
