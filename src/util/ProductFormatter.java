package util;

import entities.Product;
import entities.WeightedProduct;
import java.util.List;

public class ProductFormatter {

    private static final String DIVIDER = "=".repeat(52);
    private static final String THIN_LINE = "-".repeat(52);

    public void printTable(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("\nNo products in stock.\n");
            return;
        }

        System.out.println("\n" + DIVIDER);
        System.out.printf("%-5s | %-15s | %-10s | %-14s%n",
                "ID", "NAME", "PRICE", "STOCK");
        System.out.println(THIN_LINE);

        for (Product p : products) {
            String priceFormatted = p instanceof WeightedProduct
                    ? String.format("%.2f/kg", p.getPrice())
                    : String.format("$ %.2f", p.getPrice());

            System.out.printf("%-5d | %-15s | %-10s | %-14s%n",
                    p.getId(),
                    truncate(p.getName(), 15),
                    priceFormatted,
                    p.getStockInfo());
        }

        System.out.println(DIVIDER + "\n");
    }

    private String truncate(String text, int max) {
        return text.length() <= max ? text : text.substring(0, max - 1) + "…";
    }
}
