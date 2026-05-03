package cart.promotion;

import cart.Product;
import java.util.List;

public class CouponPromotion implements Promotion {

    private final String productCode;

    public CouponPromotion(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public void apply(List<Product> products) {
        for (Product p : products) {
            if (p.getCode().equals(productCode)) {
                p.setDiscountPrice(p.getDiscountPrice() * 0.7);
                break;
            }
        }
    }
}
