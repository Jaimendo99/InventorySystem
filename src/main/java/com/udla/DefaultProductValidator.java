package com.udla;

public class DefaultProductValidator implements ProductValidator {
    @Override
    public boolean isQuantityValid(int quantity) {
        return quantity > 0;
    }

    @Override
    public boolean isPriceValid(double price) {
        return price > 0.0;
    }
}
