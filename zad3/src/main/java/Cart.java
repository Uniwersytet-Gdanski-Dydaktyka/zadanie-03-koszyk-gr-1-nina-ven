import java.util.Arrays;

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
        for( int i=0; i<= SizeOfContent(); i++){
            value+=content[i].getDiscountPrice();
        }
        return value;
    }

    public Product CheapestInCart() {
        Product p=content[0];
        for( int i=0; i<= SizeOfContent(); i++){
            if(content[i].getDiscountPrice()<p.getDiscountPrice()){
                p=content[i];
            }
        }
        return p;
    }

    public Product MostExpensiveInCart() {
        Product p=content[0];
        for( int i=0; i<= SizeOfContent(); i++){
            if(content[i].getDiscountPrice()>p.getDiscountPrice()){
                p=content[i];
            }
        }
        return p;
    }


}
