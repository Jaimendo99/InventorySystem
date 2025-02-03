package com.udla;

public interface ProductValidator {
    boolean isQuantityValid(int quantity);
    boolean isPriceValid(double price);
}
