package entities;

import exceptions.InvalidProductDataException;

public abstract class Product {

    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        setName(name);
        setPrice(price);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDataException("Name cannot be empty.");
        }

        if (!name.matches("[\\p{L}0-9 \\-]+")) {
            throw new InvalidProductDataException(
                "Invalid name. Only letters, digits, spaces and hyphens are allowed.");
        }

        if (name.matches("\\d+")) {
            throw new InvalidProductDataException(
                "Name cannot be only numbers.");
        }

        this.name = name.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new InvalidProductDataException("Price must be greater than zero.");
        }
        this.price = price;
    }

    public abstract String getStockInfo();

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', price=%.2f]", id, name, price);
    }
}
