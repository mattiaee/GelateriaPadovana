////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class UserImpl implements User {

    private long id;

    private String name;

    public UserImpl(long id, String name) {
        super();
        if (id == 0) {
            throw new IllegalArgumentException("Id is not valid");
        }
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name is not valid");
        }
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
