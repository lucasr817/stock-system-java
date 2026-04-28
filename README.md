# 📦 Stock Management System (Java)

A console-based inventory management system built with Java, designed to demonstrate solid object-oriented programming (OOP), clean code practices, and a layered architecture.

This project simulates a real-world stock system with data validation, file persistence, and clear separation of responsibilities — making it a strong backend portfolio project.

---

## 🛠️ Technologies & Concepts

* Java 25
* Object-Oriented Programming (OOP)
* Layered Architecture
* Command Line Interface (CLI)
* File Persistence (CSV)
* Input Validation (Custom Exceptions)
* Optional (Null Safety)

---

## 🚀 Features

* Add products by **unit** (e.g. notebooks, bottles)
* Add products by **weight** (e.g. rice, coffee)
* List products in a **formatted table**, sorted by ID
* Update product name and price
* Remove products by ID
* Automatic persistence using a CSV file (`inventory.csv`)
* Robust validation using **custom exceptions**
* Safe lookups using **Optional (no null handling issues)**

---

## 🖥️ Demo

```text id="demo_final"
Inventory loaded. 2 product(s) in stock.

===============================
        STOCK SYSTEM
===============================
1 - Add Product
2 - List Products
3 - Remove Product
4 - Update Product
0 - Save & Exit
Choose: 2

====================================================
ID    | NAME            | PRICE      | STOCK         
----------------------------------------------------
12    | Rice            | $ 5.00     | 10 units      
25    | Coffee          | $ 12.90/kg | 5.00 kg       
====================================================
```

---

## 🧱 Project Structure

```text id="struct_final"
src/
├── application/
│   ├── Program.java
│   └── MenuHandler.java
│
├── entities/
│   ├── Product.java
│   ├── UnitProduct.java
│   ├── WeightedProduct.java
│   ├── Inventory.java
│   └── ProductType.java
│
├── exceptions/
│   └── InvalidProductDataException.java
│
└── util/
    ├── ProductFormatter.java
    └── InventoryRepository.java
```

---

## 🧠 Design Decisions

### 🔹 Layered Architecture

Each component has a single responsibility:

* `Inventory` → business rules and product storage
* `InventoryRepository` → file persistence (CSV)
* `ProductFormatter` → output formatting
* `MenuHandler` → user interaction

This reduces coupling and improves maintainability.

---

### 🔹 Validation via Exceptions

Instead of allowing invalid data, the system throws `InvalidProductDataException`.

This ensures:

* Data integrity
* Explicit error handling
* Cleaner and safer code

---

### 🔹 Optional for Safer Code

`findById()` returns `Optional<Product>` instead of `null`, making the "not found" case explicit and avoiding null-related issues.

---

### 🔹 Realistic Name Validation

Product names:

* Allow letters, numbers, spaces, and hyphens
* Reject empty values
* Reject names composed only of numbers

---

### 🔹 Enum over Magic Numbers

`ProductType` replaces raw values like `1` and `2`, improving readability and preventing invalid inputs.

---

## ▶️ How to Run

### Requirements

* Java 25+

### Compile and Run

```bash id="run_final"
javac -d out $(find src -name "*.java")
java -cp out application.Program
```

Or run directly using Eclipse or IntelliJ IDEA.

---

## 💾 Persistence

All data is saved automatically to:

```text id="file_final"
inventory.csv
```

On startup, previously saved data is loaded automatically.

---

## 📈 Future Improvements

* Add **JUnit 5 tests**
* Implement **JSON persistence (Gson or Jackson)**
* Introduce a **Service layer**
* Add stock movement (entry/exit tracking)
* Build a **REST API using Spring Boot**
* Add authentication and multi-user support

---

## 👨‍💻 Author

Developed by **Lucas Ramalho**
GitHub: https://github.com/lucasr817
