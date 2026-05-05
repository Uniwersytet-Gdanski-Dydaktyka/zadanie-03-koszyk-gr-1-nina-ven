package cart;

import cart.promotion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionCartTest {
    private Cart cart;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product1 = new Product("0001", "poduszka", 20.0);
        product2 = new Product("0002", "kołdra", 200.0);
        product3 = new Product("0003", "koc", 300.0);
    }

    @Test
    void addPromotionTest(){
        cart.addPromotion(new Buy2Get1BonusPromotion());

        assertEquals(1, cart.getPromotions().size());
    }

    @Test
    void applyPromotionTest(){
        cart.addToCart(product1);
        cart.addToCart(product2);
        cart.addToCart(product3);

        cart.addPromotion(new Buy2Get1BonusPromotion());
        cart.applyPromotions();

        assertEquals(500.0, cart.finalValue());

        cart.addPromotion(new Over300Promotion());
        cart.applyPromotions();

        assertEquals(475,  cart.finalValue());
    }

    @Test
    void buy2Get1BonusPromotionTest(){
        cart.addPromotion(new Buy2Get1BonusPromotion());
        cart.applyPromotions();

        assertEquals(0.0, cart.finalValue());

        cart.addToCart(product1);
        cart.addToCart(product3);
        cart.applyPromotions();

        assertEquals(320.0, cart.finalValue());

        cart.addToCart(product2);
        cart.applyPromotions();

        assertEquals(500.0, cart.finalValue());
    }

    @Test
    void freeMugPromotionTest(){
        cart.addPromotion(new FreeMugPromotion());
        cart.applyPromotions();

        assertEquals(0 , cart.size());

        cart.addToCart(product2);
        cart.applyPromotions();

        assertEquals(1, cart.size());

        cart.addToCart(product3);
        cart.applyPromotions();

        assertEquals(3, cart.size());
        assertEquals(500.0, cart.finalValue());
        assertEquals("Firmowy kubek", cart.getContent().getLast().getName());
    }

    @Test
    void over300PromotionTest(){
        cart.addPromotion(new Over300Promotion());
        cart.applyPromotions();

        assertEquals(0.0 , cart.finalValue());

        cart.addToCart(product3);
        cart.applyPromotions();

        assertEquals(300.0, cart.finalValue());

        cart.addToCart(product2);
        cart.applyPromotions();

        assertEquals(475.0, cart.finalValue());
    }

    @Test
    void couponPromotionTest(){
        cart.addPromotion(new CouponPromotion("0001"));
        cart.applyPromotions();

        assertEquals(0.0 , cart.finalValue());

        cart.addToCart(product3);
        cart.applyPromotions();

        assertEquals(300.0 , cart.finalValue());

        cart.addToCart(product1);
        cart.applyPromotions();

        assertEquals(314.0 , cart.finalValue());
    }

    @Test
    void multiplePromotionsTest(){
        cart.addPromotion(new CouponPromotion("0001"));
        cart.addPromotion(new FreeMugPromotion());
        cart.addToCart(product3);
        cart.addToCart(product2);
        cart.addToCart(product1);
        cart.applyPromotions();

        assertEquals(4, cart.size());
        assertEquals("Firmowy kubek", cart.getContent().getLast().getName());
        assertEquals(514.0, cart.finalValue());

        cart.addPromotion(new Over300Promotion());
        cart.applyPromotions();

        assertEquals(488.3, cart.finalValue());
    }

    @Test
    void bestPromotionOrderBasicTest(){
        assertEquals(List.of(), cart.bestPromotionOrder());

        cart.addPromotion(new CouponPromotion("0001"));
        cart.addPromotion(new FreeMugPromotion());
        cart.addPromotion(new Over300Promotion());
        cart.addToCart(product3);
        cart.addToCart(product2);
        cart.addToCart(product1);

        assertEquals(List.of( cart.getPromotions().getFirst(), cart.getPromotions().get(1),cart.getPromotions().get(2)), cart.bestPromotionOrder());
    }

    @Test
    void bestPromotionOrderCupBefore21(){
        cart.addPromotion(new FreeMugPromotion());
        cart.addPromotion(new Buy2Get1BonusPromotion());
        cart.addToCart(product3);
        cart.addToCart(product2);
        cart.addToCart(product1);

        assertEquals(List.of(cart.getPromotions().get(1), cart.getPromotions().get(0)), cart.bestPromotionOrder());
    }

    @Test
    void bestPromotionOrder21before300Test(){
        Product product4 = new Product("0004", "torebka", 101.0);
        cart.addToCart(product4.copy());
        cart.addToCart(product4.copy());
        cart.addToCart(product4.copy());

        cart.addPromotion(new Buy2Get1BonusPromotion());
        cart.addPromotion(new Over300Promotion());

        assertEquals(List.of( cart.getPromotions().get(1), cart.getPromotions().get(0)), cart.bestPromotionOrder());
    }



}
