package entities;

import exceptions.InvalidProductDataException;

public class UnitProduct extends Product {

    private int quantity;

    public UnitProduct(int id, String name, double price, int quantity) {
        super(id, name, price);
        setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidProductDataException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    @Override
    public String getStockInfo() {
        return quantity + " units";
    }

    @Override
    public String toString() {
        return String.format("UnitProduct[id=%d, name='%s', price=%.2f, qty=%d]",
                getId(), getName(), getPrice(), quantity);
    }
}
