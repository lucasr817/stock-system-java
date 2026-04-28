package entities;

import exceptions.InvalidProductDataException;

public class WeightedProduct extends Product {

    private double weight;

    public WeightedProduct(int id, String name, double price, double weight) {
        super(id, name, price);
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new InvalidProductDataException("Weight cannot be negative.");
        }
        this.weight = weight;
    }

    @Override
    public String getStockInfo() {
        return String.format("%.2f kg", weight);
    }

    @Override
    public String toString() {
        return String.format("WeightedProduct[id=%d, name='%s', price=%.2f/kg, weight=%.2fkg]",
                getId(), getName(), getPrice(), weight);
    }
}
