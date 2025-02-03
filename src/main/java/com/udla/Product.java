package com.udla;

public record Product(String name, int quantity, Double price) {
    public Product {
        if (!isQuantityValid(quantity)) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        if (!isPriceValid(price)) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
    }

    private static boolean isQuantityValid(int quantity) {
        return quantity > 0;
    }

    private static boolean isPriceValid(Double price) {
        return price > 0.0;
    }
}
