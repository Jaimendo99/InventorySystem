import static org.junit.Assert.*;
import java.util.List;

import com.udla.Inventory;
import com.udla.Product;
import com.udla.ProductValidator;
import org.junit.Test;

public class InventoryTest {

    private ProductValidator createValidator() {
        return new ProductValidator() {
            @Override
            public boolean isQuantityValid(int quantity) {
                return quantity > 0;
            }
            @Override
            public boolean isPriceValid(double price) {
                return price > 0;
            }
        };
    }

    @Test
    public void testAddNewProduct() {
        ProductValidator validator = createValidator();
        Inventory inventory = new Inventory(validator);
        // Create a product with valid values.
        Product product = new Product("Laptop", 5, 1000.0);
        inventory.addProduct(product);

        List<Product> products = inventory.getProducts();
        assertEquals("There should be one product in the inventory", 1, products.size());

        Product addedProduct = products.get(0);
        assertEquals("Product name should match", "Laptop", addedProduct.name());
        assertEquals("Product quantity should match", 5, addedProduct.quantity());
        assertEquals("Product price should match", 1000.0, addedProduct.price(), 0.001);
    }

    @Test
    public void testAddExistingProduct() {
        ProductValidator validator = createValidator();
        Inventory inventory = new Inventory(validator);
        Product product1 = new Product("Laptop", 5, 1000.0);
        Product product2 = new Product("Laptop", 3, 1200.0);
        inventory.addProduct(product1);
        inventory.addProduct(product2);

        List<Product> products = inventory.getProducts();
        assertEquals("There should be one product after merging", 1, products.size());

        Product mergedProduct = products.get(0);
        assertEquals("Product name should remain 'Laptop'", "Laptop", mergedProduct.name());
        assertEquals("Quantity should be merged (5 + 3)", 8, mergedProduct.quantity());
        assertEquals("Price should be updated to the new product's price", 1200.0, mergedProduct.price(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductQuantity() {
        new Product("Laptop", -1, 1000.0);
    }
}
