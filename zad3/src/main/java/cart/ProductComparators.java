package cart;

import java.util.Comparator;

public class ProductComparators {
    public static final Comparator<Product> byDiscountPrice =
            Comparator.comparing(Product::getDiscountPrice);

    public static final Comparator<Product> byName =
            Comparator.comparing(Product::getName);
}
