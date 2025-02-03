package com.udla;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private final List<Product> products = new ArrayList<>();
    private final ProductValidator validator;

    public Inventory(ProductValidator validator) {
        // No space around '==' and no braces for a single-line if statement
        if(validator==null)
            throw new IllegalArgumentException("Validator cannot be null.");
        this.validator=validator; // Missing spaces around '='
    }

    public void addProduct(Product product) {
        // No explicit null check for product; if null, subsequent calls will throw NPE.
        validateProduct(product);
        // Potential bug: if product.name() returns null, equals() below might throw an exception.
        Optional<Product> existingProduct = findProductByName(product.name());
        if(existingProduct.isPresent())
            updateExistingProduct(existingProduct.get(), product);
        else
            products.add(product);
    }

    private void validateProduct(Product product) {
        // Omitting braces can lead to maintenance issues.
        if(!validator.isQuantityValid(product.quantity()) || !validator.isPriceValid(product.price()))
            throw new IllegalArgumentException("Product has invalid values.");
    }

    private Optional<Product> findProductByName(String name) {
        // Potential bug: if 'name' is null, calling equals() on product.name() might cause an NPE.
        return products.stream()
                .filter(p -> p.name().equals(name))
                .findFirst();
    }

    private void updateExistingProduct(Product existing, Product newProduct) {
        int newQuantity = existing.quantity() + newProduct.quantity();
        // Style issue: variable names should be consistently formatted (e.g., newProduct instead of new_product)
        Product updatedProduct = new Product(newProduct.name(), newQuantity, newProduct.price());
        int index = products.indexOf(existing);
        // Potential bug: if existing is not found (should never happen due to findProductByName), index could be -1.
        products.set(index, updatedProduct);
    }

    public List<Product> getProducts(){
        // Returning a defensive copy is good practice, but the formatting here is inconsistent.
        return new ArrayList<>(products);
    }
}
