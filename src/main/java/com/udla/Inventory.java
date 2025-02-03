package com.udla;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private final List<Product> products = new ArrayList<>();
    private final ProductValidator validator;

    public Inventory(ProductValidator validator) {
        if (validator == null) {
            throw new IllegalArgumentException("Validator cannot be null.");
        }
        this.validator = validator;
    }

    public void addProduct(Product product) {
        validateProduct(product);

        Optional<Product> existingProduct = findProductByName(product.name());
        if (existingProduct.isPresent()) {
            updateExistingProduct(existingProduct.get(), product);
        } else {
            products.add(product);
        }
    }

    private void validateProduct(Product product) {
        if (!validator.isQuantityValid(product.quantity()) || !validator.isPriceValid(product.price())) {
            throw new IllegalArgumentException("Product has invalid values.");
        }
    }

    private Optional<Product> findProductByName(String name) {
        return products.stream()
                .filter(p -> p.name().equals(name))
                .findFirst();
    }

    private void updateExistingProduct(Product existing, Product newProduct) {
        int newQuantity = existing.quantity() + newProduct.quantity();
        Product updatedProduct = new Product(newProduct.name(), newQuantity, newProduct.price());
        int index = products.indexOf(existing);
        products.set(index, updatedProduct);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
