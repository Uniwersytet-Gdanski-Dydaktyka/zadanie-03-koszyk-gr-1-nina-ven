package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        cart.AddToCart(product1);
        cart.AddToCart(product1);

        assertEquals(product1, cart.getContent()[0]);
        assertEquals(product1, cart.getContent()[1]);

        cart.DeleteFromCart(product1);
        cart.DeleteFromCart(product1);

        cart.AddToCart(product2);

        assertEquals(product2, cart.getContent()[0]);

    }

    @Test
    void sizeOfContentTest(){
        assertEquals(0, cart.SizeOfContent());

        cart.AddToCart(product1);

        assertEquals(1, cart.SizeOfContent());

        cart.AddToCart(product2);

        assertEquals(2, cart.SizeOfContent());

        cart.DeleteFromCart(product1);
        cart.DeleteFromCart(product2);

        assertEquals(0, cart.SizeOfContent());
    }

    @Test
    void originalValueTest(){
        assertEquals(0.0, cart.OriginalValue());

        cart.AddToCart(product1);

        assertEquals(20.0, cart.OriginalValue());

        cart.AddToCart(product2);

        assertEquals(70.0, cart.OriginalValue());

        cart.DeleteFromCart(product2);

        assertEquals(20.0, cart.OriginalValue());
    }

    @Test
    void finalValueTest(){
        assertEquals(0.0, cart.FinalValue());

        cart.AddToCart(product1);
        product1.setDiscountPrice(product1.getDiscountPrice()/2);

        assertEquals(10.0, cart.FinalValue());

        cart.AddToCart(product2);
        product2.setDiscountPrice(product2.getDiscountPrice()/5);
        cart.Sort();

        assertEquals(20.0, cart.FinalValue());

        cart.DeleteFromCart(product2);

        assertEquals(10.0, cart.FinalValue());
    }

    @Test
    void cheapestInCartTest(){
        assertNull(cart.CheapestInCart());

        cart.AddToCart(product2);

        assertEquals(product2, cart.CheapestInCart());

        cart.AddToCart(product1);

        assertEquals(product1, cart.CheapestInCart());
    }

    @Test
    void nCheapestInCart(){
        assertArrayEquals(new Product[0],cart.NCheapestInCart(3));

        cart.AddToCart(product2);
        cart.AddToCart(product2);
        cart.AddToCart(product2);

        assertArrayEquals(new Product[]{product2, product2}, cart.NCheapestInCart(2));

        cart.AddToCart(product1);
        cart.AddToCart(product1);

        assertArrayEquals(new Product[]{product1, product1, product2}, cart.NCheapestInCart(3));

    }

    @Test
    void mostExpensiveInCartTest(){
        assertNull(cart.MostExpensiveInCart());

        cart.AddToCart(product1);

        assertEquals(product1, cart.MostExpensiveInCart());

        cart.AddToCart(product2);

        assertEquals(product2, cart.MostExpensiveInCart());
    }

    @Test
    void nCMostExpensiveInCart(){
        assertArrayEquals(new Product[0],cart.NMostExpensiveInCart(3));

        cart.AddToCart(product1);
        cart.AddToCart(product1);
        cart.AddToCart(product1);

        assertArrayEquals(new Product[]{product1, product1}, cart.NMostExpensiveInCart(2));

        cart.AddToCart(product2);
        cart.AddToCart(product2);

        assertArrayEquals(new Product[]{product2, product2, product1}, cart.NMostExpensiveInCart(3));

    }

    @Test
    void sortTest(){
        cart.AddToCart(product1);
        cart.AddToCart(product2);

        assertArrayEquals(new Product[]{product2,product1}, cart.getContent());

        product2.setDiscountPrice(product2.getDiscountPrice()/5);
        cart.Sort();

        assertArrayEquals(new Product[]{product1,product2}, cart.getContent());

        product2.setDiscountPrice(product2.getDiscountPrice()*2);
        cart.Sort();

        assertArrayEquals(new Product[]{product2,product1}, cart.getContent());
    }





}
