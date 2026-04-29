package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product1 = new Product("0001", "poduszka", 20.0);
        product2 = new Product("0002", "kołdra", 50.0);
        product3 = new Product("0003", "koc", 30.0);

    }

    @Test
    void addToCartTest(){
        cart.addToCart(product1);
        cart.addToCart(product1);

        assertEquals(product1, cart.getContent()[0]);
        assertEquals(product1, cart.getContent()[1]);
    }

    @Test
    void sizeOfContentTest(){
        assertEquals(0, cart.SizeOfContent());

        cart.addToCart(product1);

        assertEquals(1, cart.SizeOfContent());

        cart.addToCart(product2);

        assertEquals(2, cart.SizeOfContent());
    }

    @Test
    void valueTest(){
        assertEquals(0.0, cart.Value());

        cart.addToCart(product1);

        assertEquals(20.0, cart.Value());

        cart.addToCart(product2);

        assertEquals(70.0, cart.Value());

    }

    @Test
    void cheapestInCartTest(){
        assertNull(cart.CheapestInCart());

        cart.addToCart(product2);

        assertEquals(product2, cart.CheapestInCart());

        cart.addToCart(product1);

        assertEquals(product1, cart.CheapestInCart());
    }

    @Test
    void nCheapestInCart(){
        assertArrayEquals(new Product[0],cart.NCheapestInCart(3));

        cart.addToCart(product2);
        cart.addToCart(product2);
        cart.addToCart(product2);

        assertArrayEquals(new Product[]{product2, product2}, cart.NCheapestInCart(2));

        cart.addToCart(product1);
        cart.addToCart(product1);

        assertArrayEquals(new Product[]{product1, product1, product2}, cart.NCheapestInCart(3));

    }

    @Test
    void mostExpensiveInCartTest(){
        assertNull(cart.MostExpensiveInCart());

        cart.addToCart(product1);

        assertEquals(product1, cart.MostExpensiveInCart());

        cart.addToCart(product2);

        assertEquals(product2, cart.MostExpensiveInCart());
    }

    @Test
    void nCMostExpensiveInCart(){
        assertArrayEquals(new Product[0],cart.NMostExpensiveInCart(3));

        cart.addToCart(product1);
        cart.addToCart(product1);
        cart.addToCart(product1);

        assertArrayEquals(new Product[]{product1, product1}, cart.NMostExpensiveInCart(2));

        cart.addToCart(product2);
        cart.addToCart(product2);

        assertArrayEquals(new Product[]{product2, product2, product1}, cart.NMostExpensiveInCart(3));

    }





}
