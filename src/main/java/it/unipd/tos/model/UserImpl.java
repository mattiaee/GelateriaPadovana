////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class UserImpl implements User {

    private long id;

    private String name;
    
    private int age;

    public UserImpl(long id, String name, int age) {
        super();
        if (id == 0) {
            throw new IllegalArgumentException("Id is not valid");
        }
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name is not valid");
        }
        if (age == 0) {
            throw new IllegalArgumentException("Age is not valid");
        }
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }

}
