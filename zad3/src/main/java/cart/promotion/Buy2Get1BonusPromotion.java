package cart.promotion;

import cart.Product;
import java.util.Comparator;
import java.util.List;

public class Buy2Get1BonusPromotion implements Promotion {

    @Override
    public void apply(List<Product> products) {
        if (products.size() < 3) return;

        products.stream()
                .min(Comparator.comparing(Product::getDiscountPrice))
                .ifPresent(cheapest -> cheapest.setDiscountPrice(0.0));

    }
}
