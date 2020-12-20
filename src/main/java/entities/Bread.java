package entities;

import java.math.RoundingMode;

public class Bread extends Goods{
    public Bread() {
        super(Goods.BREAD, 0.8);
    }


    @Override
    public double applyDiscount(double discount) {
        if (discount>0.0){
            return discount*this.price;
        }

        return 0.0;
    }
}
