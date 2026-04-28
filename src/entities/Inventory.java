package entities;

import exceptions.InvalidProductDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Inventory {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        boolean idExists = products.stream().anyMatch(p -> p.getId() == product.getId());
        if (idExists) {
            throw new InvalidProductDataException(
                "ID " + product.getId() + " already exists in the inventory.");
        }
        products.add(product);
    }

    public void removeById(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (!removed) {
            throw new InvalidProductDataException("No product found with ID " + id + ".");
        }
    }

    public Optional<Product> findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Product> getAllSortedById() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort((a, b) -> Integer.compare(a.getId(), b.getId()));
        return Collections.unmodifiableList(sorted);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int size() {
        return products.size();
    }
}
