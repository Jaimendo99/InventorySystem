package com.udla;

public class InventoryOutput {
    public void showInventory(Inventory inventory) {
        for (Product product : inventory.getProducts()) {
            System.out.println("Product: " + product.name() +
                    " Quantity: " + product.quantity() +
                    " Price: $" + product.price());
        }
    }

    public void printSuccessMessage() {
        System.out.println("Product added successfully.");
    }

    public void printErrorMessage(String message) {
        System.out.println("Error adding product: " + message);
    }
}
