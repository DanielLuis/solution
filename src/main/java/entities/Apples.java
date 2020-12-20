package entities;

public class Apples extends Goods {

    public Apples() {
        super(Goods.APPLES, 1.0);
    }


    @Override
    public double applyDiscount(double discount) {

        if (discount>0.0){
            return discount*this.price;
        }

        return 0.0;

    }
}
