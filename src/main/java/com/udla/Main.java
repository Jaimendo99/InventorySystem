package com.udla;

public class Main {
    public static void main(String[] args) {
        InventoryOutput view = new InventoryOutput();
        ProductValidator validator = new DefaultProductValidator();
        Inventory inventory = new Inventory(validator);

        try {
            inventory.addProduct(new Product("Laptop", 1, 1000.0));
            inventory.addProduct(new Product("Laptop", 1, 3000.0));
            inventory.addProduct(new Product("Mouse", 5, 20.0));
            inventory.addProduct(new Product("Keyboard", 2, 50.0));
            inventory.addProduct(new Product("Monitor", 1, 200.0));

            view.printSuccessMessage();
        } catch (IllegalArgumentException e) {
            view.printErrorMessage(e.getMessage());
        }

        view.showInventory(inventory);
    }
}
