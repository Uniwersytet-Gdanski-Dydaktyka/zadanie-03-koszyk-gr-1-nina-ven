package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product1 = new Product("0001", "poduszka", 20.0);
        product2 = new Product("0002", "kołdra", 50.0);

    }

    @Test
    void addToCartTest(){
        cart.addToCart(product1);
        cart.addToCart(product1);

        assertEquals(product1, cart.getContent().getFirst());
        assertEquals(product1, cart.getContent().get(1));

        cart.deleteFromCart(product1);
        cart.deleteFromCart(product1);

        cart.addToCart(product2);

        assertEquals(product2, cart.getContent().getFirst());

    }

    @Test
    void deleteFromCartTest(){
        cart.addToCart(product1);
        cart.addToCart(product1);
        cart.deleteFromCart(product1);

        assertEquals(product1, cart.getContent().getFirst());
        assertEquals(1 ,cart.size());

        cart.deleteFromCart(product1);

        assertEquals(0 ,cart.size());

    }

    @Test
    void sizeOfContentTest(){
        assertEquals(0, cart.size());

        cart.addToCart(product1);

        assertEquals(1, cart.size());

        cart.addToCart(product2);

        assertEquals(2, cart.size());

        cart.deleteFromCart(product1);
        cart.deleteFromCart(product2);

        assertEquals(0, cart.size());
    }

    @Test
    void originalValueTest(){
        assertEquals(0.0, cart.originalValue());

        cart.addToCart(product1);

        assertEquals(20.0, cart.originalValue());

        cart.addToCart(product2);

        assertEquals(70.0, cart.originalValue());

        cart.deleteFromCart(product2);

        assertEquals(20.0, cart.originalValue());
    }

    @Test
    void finalValueTest(){
        assertEquals(0.0, cart.finalValue());

        cart.addToCart(product1);
        product1.setDiscountPrice(product1.getDiscountPrice()/2);

        assertEquals(10.0, cart.finalValue());

        cart.addToCart(product2);
        product2.setDiscountPrice(product2.getDiscountPrice()/5);
        cart.sortDefault();

        assertEquals(20.0, cart.finalValue());

        cart.deleteFromCart(product2);

        assertEquals(10.0, cart.finalValue());
    }

    @Test
    void cheapestInCartTest(){
        assertNull(cart.cheapestInCart());

        cart.addToCart(product2);

        assertEquals(product2, cart.cheapestInCart());

        cart.addToCart(product1);

        assertEquals(product1, cart.cheapestInCart());
    }

    @Test
    void nCheapestInCart(){
        assertEquals(List.of(),cart.nCheapestInCart(3));

        cart.addToCart(product2);
        cart.addToCart(product2);
        cart.addToCart(product2);

        assertEquals(List.of(product2,product2), cart.nCheapestInCart(2));

        cart.addToCart(product1);
        cart.addToCart(product1);

        assertEquals(List.of(product1,product1,product2), cart.nCheapestInCart(3));

    }

    @Test
    void mostExpensiveInCartTest(){
        assertNull(cart.mostExpensiveInCart());

        cart.addToCart(product1);

        assertEquals(product1, cart.mostExpensiveInCart());

        cart.addToCart(product2);

        assertEquals(product2, cart.mostExpensiveInCart());
    }

    @Test
    void nCMostExpensiveInCart(){
        assertEquals(List.of(),cart.nMostExpensiveInCart(3));

        cart.addToCart(product1);
        cart.addToCart(product1);
        cart.addToCart(product1);

        assertEquals(List.of(product1,product1), cart.nMostExpensiveInCart(2));

        cart.addToCart(product2);
        cart.addToCart(product2);

        assertEquals(List.of(product2,product2,product1), cart.nMostExpensiveInCart(3));

    }

    @Test
    void sortTest(){
        cart.addToCart(product1);
        cart.addToCart(product2);

        assertEquals(List.of(product2, product1), cart.getContent());

        product2.setDiscountPrice(product2.getDiscountPrice()/5);
        cart.sortDefault();

        assertEquals(List.of(product1, product2), cart.getContent());

        product2.setDiscountPrice(product2.getDiscountPrice()*2);
        cart.sortDefault();

        assertEquals(List.of(product2, product1), cart.getContent());
    }





}
