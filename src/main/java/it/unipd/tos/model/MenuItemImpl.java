////////////////////////////////////////////////////////////////////
// Mattia Episcopo 1187587
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItemImpl implements MenuItem {

    private long id;

    private String name;

    private ItemType type;

    private double price;

    public MenuItemImpl(long id, String name, ItemType type, double price) {
        super();
        if (id == 0) {
            throw new IllegalArgumentException("Id is not valid");
        }
        if (type == null) {
            throw new IllegalArgumentException("ItemType cannot be null");
        }
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name is not valid");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

}
