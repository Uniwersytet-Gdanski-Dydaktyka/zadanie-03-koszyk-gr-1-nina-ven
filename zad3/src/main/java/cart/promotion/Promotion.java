package cart.promotion;

import cart.Product;
import java.util.List;

public interface Promotion {
    void apply(List<Product> products);
}


//wybrałam wzorzez strategy
// ponieważ pozwala mi on dodawać i usuwać promocje w trakcie działania programu bez skomplikowanych ifów
// i kod jest otawarty na rozszerzenia w postaci nowych promocji
// Open/Closed Principle (OCP)

//promocje są uaktualniane za pomocą applyPromotions()
// co pozwala mi na wiekszą kontrolę i brak niespodziewanych komplikacji