package cart.promotion;

import cart.Product;
import java.util.List;

public class Over300Promotion implements Promotion {

    @Override
    public void apply(List<Product> products) {
        double sum = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        if (sum > 300) {
            for (Product p : products) {
                p.setDiscountPrice(p.getDiscountPrice() * 0.95);
            }
        }
    }
}