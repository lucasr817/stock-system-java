package application;

import entities.*;
import exceptions.InvalidProductDataException;
import util.InventoryRepository;
import util.ProductFormatter;

import java.util.Optional;
import java.util.Scanner;

public class MenuHandler {

    private final Inventory inventory;
    private final ProductFormatter formatter;
    private final InventoryRepository repository;
    private final Scanner sc;

    public MenuHandler(Inventory inventory, Scanner sc) {
        this.inventory  = inventory;
        this.sc         = sc;
        this.formatter  = new ProductFormatter();
        this.repository = new InventoryRepository();
    }

    public void run() {
        repository.load(inventory);
        System.out.println("Inventory loaded. " + inventory.size() + " product(s) in stock.");

        int option;
        do {
            printMenu();
            option = readInt("Choose: ");

            try {
                switch (option) {
                    case 1 -> addProduct();
                    case 2 -> formatter.printTable(inventory.getAllSortedById());
                    case 3 -> removeProduct();
                    case 4 -> updateProduct();
                    case 0 -> {
                        repository.save(inventory);
                        System.out.println("Inventory saved. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidProductDataException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n===============================");
        System.out.println("        STOCK SYSTEM");
        System.out.println("===============================");
        System.out.println("1 - Add Product");
        System.out.println("2 - List Products");
        System.out.println("3 - Remove Product");
        System.out.println("4 - Update Product");
        System.out.println("0 - Save & Exit");
    }

    private void addProduct() {
        int type = readInt("Type (1-Unit, 2-Weight): ");
        int id   = readInt("ID: ");
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        if (type == 1) {
            double price    = readDouble("Unit price: ");
            int    quantity = readInt("Quantity in stock: ");
            inventory.addProduct(new UnitProduct(id, name, price, quantity));
            System.out.println("Product added!");

        } else if (type == 2) {
            double price  = readDouble("Price per kg: ");
            double weight = readDouble("Weight in stock (kg): ");
            inventory.addProduct(new WeightedProduct(id, name, price, weight));
            System.out.println("Product added!");

        } else {
            System.out.println("Invalid type. Product not added.");
        }
    }

    private void removeProduct() {
        int id = readInt("Enter ID to remove: ");
        inventory.removeById(id);
        System.out.println("Product removed!");
    }

    private void updateProduct() {
        int id = readInt("Enter ID to update: ");
        Optional<Product> opt = inventory.findById(id);

        if (opt.isEmpty()) {
            System.out.println("Product not found.");
            return;
        }

        Product p = opt.get();
        sc.nextLine();

        System.out.print("New name (leave blank to keep '" + p.getName() + "'): ");
        String newName = sc.nextLine();
        if (!newName.isBlank()) {
            p.setName(newName);
        }

        System.out.print("New price (0 to keep current): ");
        double newPrice = sc.nextDouble();
        if (newPrice > 0) {
            p.setPrice(newPrice);
        }

        System.out.println("Product updated: " + p);
    }


    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("Please enter a valid integer.");
            sc.nextLine();
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            }
            System.out.println("Please enter a valid number.");
            sc.nextLine();
        }
    }
}
