package cart.promotion;

import cart.Product;
import java.util.List;

public class FreeMugPromotion implements Promotion {

    @Override
    public void apply(List<Product> products) {
        double sum = products.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();

        boolean alreadyAdded = products.stream()
                .anyMatch(p -> p.getCode().equals("9990"));

        if (sum > 200 && !alreadyAdded) {
            products.add(new Product("9990", "Firmowy kubek", 0.0));
        }
    }
}
