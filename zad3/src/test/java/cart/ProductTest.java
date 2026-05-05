package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductTest {

    private Product product1;

    @BeforeEach
    void setUp() {
        product1 = new Product("0001", "poduszka", 20.0);
    }

    @Test
    void negativePriceTest(){
        Product product3 = new Product("0001", "poduszka", -20.0);

        assertEquals(0.0, product3.getPrice());

        product3.setPrice(-30.0);

        assertEquals(0.0, product3.getPrice());
    }

    @Test
    void discountPriceTest(){
        assertEquals(20.0, product1.getDiscountPrice());

        product1.setDiscountPrice(10.0);
        assertEquals(10.0, product1.getDiscountPrice());

        product1.setDiscountPrice(-30.0);
        assertEquals(10.0, product1.getDiscountPrice());
    }

    @Test
    void resetTest(){
        product1.setDiscountPrice(10);

        assertEquals(10.0, product1.getDiscountPrice());

        product1.resetDiscount();
        assertEquals(20.0, product1.getDiscountPrice());
    }

    @Test
    void copyTest(){
        Product product2 = product1.copy();

        assertNotEquals(product1, product2);
        assertEquals(product1.getName(), product2.getName());
        assertEquals(product1.getCode(), product2.getCode());
        assertEquals(product1.getPrice(), product2.getPrice());
        assertEquals(product1.getDiscountPrice(), product2.getDiscountPrice());
    }
}
