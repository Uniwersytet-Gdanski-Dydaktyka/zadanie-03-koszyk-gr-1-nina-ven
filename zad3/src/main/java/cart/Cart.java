package cart;

import java.util.Arrays;
import java.util.Comparator;

public class Cart {
    private Product[] content = {};

    public Product[] getContent() {
        return content;
    }


    public void addToCart(Product p) {
        content = Arrays.copyOf(content, content.length + 1);
        content[content.length - 1] = p;
    }

    public int SizeOfContent() {
        return content.length;
    }

    public double Value() {
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
            Product p=content[0];

            for( int i=0; i<SizeOfContent(); i++){
                if(content[i].getDiscountPrice()<p.getDiscountPrice()){
                    p=content[i];
                }
            }
            return p;
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
            Product p=content[0];

            for( int i=0; i<SizeOfContent(); i++){
                if(content[i].getDiscountPrice()>p.getDiscountPrice()){
                    p=content[i];
                }
            }
            return p;
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
