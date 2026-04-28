package util;

import entities.Inventory;
import entities.Product;
import entities.UnitProduct;
import entities.WeightedProduct;

import java.io.*;
import java.nio.file.*;

public class InventoryRepository {

    private static final String FILE_PATH = "inventory.csv";
    private static final String HEADER = "type,id,name,price,stock";

    public void save(Inventory inventory) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println(HEADER);
            for (Product p : inventory.getAllSortedById()) {
                if (p instanceof UnitProduct up) {
                    pw.printf("unit,%d,%s,%.2f,%d%n",
                            up.getId(), up.getName(), up.getPrice(), up.getQuantity());
                } else if (p instanceof WeightedProduct wp) {
                    pw.printf("weighted,%d,%s,%.2f,%.3f%n",
                            wp.getId(), wp.getName(), wp.getPrice(), wp.getWeight());
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: could not save inventory — " + e.getMessage());
        }
    }

    public void load(Inventory inventory) {
        Path path = Path.of(FILE_PATH);
        if (!Files.exists(path)) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine(); // skip header
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split(",", 5);
                if (parts.length < 5) continue;

                try {
                    String type  = parts[0].trim();
                    int    id    = Integer.parseInt(parts[1].trim());
                    String name  = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());

                    if ("unit".equals(type)) {
                        int qty = Integer.parseInt(parts[4].trim());
                        inventory.addProduct(new UnitProduct(id, name, price, qty));
                    } else if ("weighted".equals(type)) {
                        double weight = Double.parseDouble(parts[4].trim());
                        inventory.addProduct(new WeightedProduct(id, name, price, weight));
                    }
                } catch (Exception e) {
                    System.err.printf("Warning: skipping malformed line %d: %s%n", lineNumber, line);
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: could not load inventory — " + e.getMessage());
        }
    }
}
