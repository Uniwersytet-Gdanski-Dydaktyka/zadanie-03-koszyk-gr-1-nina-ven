package cart.promotion;

import cart.Product;
import java.util.List;

public interface Promotion {
    void apply(List<Product> products);
}