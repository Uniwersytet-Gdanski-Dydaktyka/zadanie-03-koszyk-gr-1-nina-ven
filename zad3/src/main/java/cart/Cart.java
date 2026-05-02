package cart;

import java.util.Arrays;
import java.util.Comparator;

import static cart.ProductComparators.*;

public class Cart {
    private Product[] content = {};

    public Product[] getContent() {
        return content;
    }

    public int SizeOfContent() {
        return content.length;
    }

    public void sort(Comparator<Product> comparator) {
        Arrays.sort(content, comparator);
    }

    public void Sort() {
        sort(byDiscountPrice.reversed().thenComparing(byName));
    }

    public void AddToCart(Product p) {
        content = Arrays.copyOf(content, content.length + 1);
        content[content.length - 1] = p;
        Sort();
    }


    public void DeleteFromCart(Product p) {
        int index = -1;

        for (int i = 0; i < content.length; i++) {
            if (content[i].equals(p)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        Product[] newContent = new Product[content.length - 1];

        for (int i = 0, j = 0; i < content.length; i++) {
            if (i != index) {
                newContent[j++] = content[i];
            }
        }

        content = newContent;
    }

    public double OriginalValue() {
        double value = 0.0;

        if(SizeOfContent() !=0){
            for( int i=0; i<SizeOfContent(); i++){
                value+=content[i].getPrice();
            }
        }

        return value;
    }

    public double FinalValue() {
        double value = 0.0;

        if(SizeOfContent() !=0){
            for( int i=0; i<SizeOfContent(); i++){
                value+=content[i].getDiscountPrice();
            }
        }

        return value;
    }

    public Product CheapestInCart() {
        if(SizeOfContent() != 0){
            Product[] products = Arrays.copyOf(content, SizeOfContent());

            Arrays.sort(products, Comparator.comparingDouble(Product::getDiscountPrice));
            return products[0];
        }

        return null;
    }

    public Product[] NCheapestInCart(int n) {
        Product[] products = Arrays.copyOf(content, SizeOfContent());

        Arrays.sort(products, Comparator.comparingDouble(Product::getDiscountPrice));

        if (n > products.length) {
            n = products.length;
        }

        return Arrays.copyOf(products, n);
    }

    public Product MostExpensiveInCart() {
        if(SizeOfContent() != 0){
            Product[] products = Arrays.copyOf(content, SizeOfContent());

            Arrays.sort(products, Comparator.comparingDouble(Product::getDiscountPrice).reversed());
            return products[0];
        }

        return null;
    }

    public Product[] NMostExpensiveInCart(int n) {
        Product[] products = Arrays.copyOf(content, SizeOfContent());

        Arrays.sort(products, Comparator.comparingDouble(Product::getDiscountPrice).reversed());

        if (n > products.length) {
            n = products.length;
        }

        return Arrays.copyOf(products, n);
    }




}
