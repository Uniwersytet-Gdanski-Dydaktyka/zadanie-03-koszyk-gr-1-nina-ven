package cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static cart.ProductComparators.*;
import cart.promotion.Promotion;

public class Cart {

    private final List<Product> content = new ArrayList<>();
    private final List<Promotion> promotions = new ArrayList<>();

    public List<Product> getContent() {
        return List.copyOf(content);
    }

    public List<Promotion> getPromotions() {
        return List.copyOf(promotions);
    }

    public int size() {
        return content.size();
    }

    public void sort(Comparator<Product> comparator) {
        content.sort(comparator);
    }

    public void sortDefault() {
        sort(byDiscountPrice.reversed().thenComparing(byName));
    }

    public void addToCart(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        content.add(p);
        sortDefault();
    }

    public void deleteFromCart(Product p) {
        content.remove(p);
    }

    public double originalValue() {
        return content.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public double finalValue() {
        return content.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();
    }

    private List<Product> sortedCopy(Comparator<Product> comparator) {
        List<Product> copy = new ArrayList<>(content);
        copy.sort(comparator);
        return copy;
    }

    public Product cheapestInCart() {
        return content.isEmpty() ? null :
                sortedCopy(byDiscountPrice).getFirst();
    }

    public List<Product> nCheapestInCart(int n) {
        if (n <= 0) return List.of();

        List<Product> sorted = sortedCopy(byDiscountPrice);
        return new ArrayList<>(sorted.subList(0, Math.min(n, sorted.size())));
    }

    public Product mostExpensiveInCart() {
        return content.isEmpty() ? null :
                sortedCopy(byDiscountPrice.reversed()).getFirst();
    }

    public List<Product> nMostExpensiveInCart(int n) {
        if (n <= 0) return List.of();

        List<Product> sorted = sortedCopy(byDiscountPrice.reversed());
        return new ArrayList<>(sorted.subList(0, Math.min(n, sorted.size())));
    }

    public void addPromotion(Promotion promotion) {
        if (promotion == null) {
            throw new IllegalArgumentException("Promotion cannot be null");
        }
        promotions.add(promotion);
    }

    public void applyPromotions() {
        for (Product p : content) {
            p.resetDiscount();
        }

        for (Promotion promotion : promotions) {
            promotion.apply(content);
        }

        sortDefault();
    }

    private List<Product> copyProducts() {
        List<Product> copy = new ArrayList<>();
        for (Product p : content) {
            copy.add(p.copy());
        }
        return copy;
    }

    private double simulate(List<Promotion> order) {
        List<Product> productsCopy = copyProducts();

        for (Product p : productsCopy) {
            p.resetDiscount();
        }

        for (Promotion promo : order) {
            promo.apply(productsCopy);
        }

        return productsCopy.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();
    }

    private void permute(List<Promotion> arr, int k, List<List<Promotion>> result) {
        if (k == arr.size()) {
            result.add(new ArrayList<>(arr));
        } else {
            for (int i = k; i < arr.size(); i++) {
                Collections.swap(arr, i, k);
                permute(arr, k + 1, result);
                Collections.swap(arr, i, k);
            }
        }
    }

    public List<Promotion> bestPromotionOrder() {
        List<List<Promotion>> permutations = new ArrayList<>();
        permute(new ArrayList<>(promotions), 0, permutations);

        double best = Double.MAX_VALUE;
        List<Promotion> bestOrder = List.of();

        for (List<Promotion> order : permutations) {
            double value = simulate(order);
            if (value < best) {
                best = value;
                bestOrder = order;
            }
        }

        return bestOrder;
    }

}
