package entities;

public class Milk extends Goods{
    public Milk() {
        super(Goods.MILK, 1.3);
    }

    @Override
    public double applyDiscount(double discount) { return 0; }
}
