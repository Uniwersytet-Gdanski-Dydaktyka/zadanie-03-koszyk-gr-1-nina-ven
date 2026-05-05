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
        if( discountPrice < 0 ) {discountPrice = this.discountPrice;}
        this.discountPrice = discountPrice;
    }

    public void resetDiscount() {
        this.discountPrice = this.price;
    }

    public Product copy() {
        Product p = new Product(this.code, this.name, this.price);
        p.setDiscountPrice(this.discountPrice);
        return p;
    }
}

//moja klasa Product jest mutowalna z dwóch powodów
//1. cena czy nazwa produktu moga się zmieniać
//2. discountPrice nie miałoby zbyt sensu bez mutowalności
