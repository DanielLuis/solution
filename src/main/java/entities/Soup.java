package entities;

public class Soup extends Goods{
    public Soup() {
        super(Goods.SOUP, 0.65);
    }



    @Override
    public double applyDiscount(double discount) {
        return 0;
    }
}
