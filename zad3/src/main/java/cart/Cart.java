package cart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static cart.ProductComparators.*;

public class Cart {
    private final List<Product> content = new ArrayList<>();

    public List<Product> getContent() {
        return content;
    }

    public int SizeOfContent() {
        return content.size();
    }

    public void sort(Comparator<Product> comparator) {
        content.sort(comparator);
    }

    public void Sort() {
        sort(byDiscountPrice.reversed().thenComparing(byName));
    }

    public void AddToCart(Product p) {
        content.add(p);
        Sort();
    }

    public void DeleteFromCart(Product p) {
        content.remove(p);
    }

    public double OriginalValue() {
        double value = 0.0;

        for (Product p : content) {
            value += p.getPrice();
        }

        return value;
    }

    public double FinalValue() {
        double value = 0.0;

        for (Product p : content) {
            value += p.getDiscountPrice();
        }

        return value;
    }

    public Product CheapestInCart() {
        if (SizeOfContent() == 0) return null;

        List<Product> copy = new ArrayList<>(content);
        copy.sort(byDiscountPrice);

        return copy.getFirst();
    }

    public List<Product> NCheapestInCart(int n) {
        List<Product> copy = new ArrayList<>(content);
        copy.sort(byDiscountPrice);

        if (n > copy.size()) {
            n = copy.size();
        }

        return copy.subList(0, n);
    }

    public Product MostExpensiveInCart() {
        if (SizeOfContent() == 0) return null;

        List<Product> copy = new ArrayList<>(content);
        copy.sort(byDiscountPrice.reversed());

        return copy.getFirst();
    }

    public List<Product> NMostExpensiveInCart(int n) {
        List<Product> copy = new ArrayList<>(content);
        copy.sort(byDiscountPrice.reversed());

        if (n > copy.size()) {
            n = copy.size();
        }

        return copy.subList(0, n);
    }




}
