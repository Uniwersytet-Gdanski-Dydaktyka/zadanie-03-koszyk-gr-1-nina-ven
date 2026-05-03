package cart;

public class Product {
    private final String code;
    private String name;
    private double price;
    private double discountPrice;

    public Product(String code, String name, double price) {
        if( price < 0 ) {price = 0.0;}
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if( price < 0 ) {price=0.0;}
        this.price = price;
        this.discountPrice = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void resetDiscount() {
        this.discountPrice = this.price;
    }
}
