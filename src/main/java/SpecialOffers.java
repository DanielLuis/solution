import entities.Goods;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Class to apply any discount on goods
 */
public class SpecialOffers {

    public static final double HALF_DISCOUNT = 0.50;
    public static final double DISCOUNT_10_PERCENTAGE = 0.10;

    public static final double ZERO = 0.0;

    /**
     * Apply discount on type of goods
     * @param goods
     * @param discount
     * @param typeGoods
     * @param text
     * @return discount
     */
    public static double applySpecialOfferDiscount(List<Goods> goods,double discount,String typeGoods,String text) {
        double value =  (null != typeGoods)? goods.stream()
                                                     .filter(g-> typeGoods.equals(g.getName()))
                                                     .mapToDouble(g-> g.applyDiscount(discount))
                                                     .sum():ZERO;


        Util.writeDiscounts(text,value);
     return  value;
    }
    /**
     * Calculate total discount on apples (Apples have 10% off their normal price)
     * @param goods
     * @return double
     */
    public static double applySpecialOfferApples(List<Goods> goods) {
        return applySpecialOfferDiscount(goods,DISCOUNT_10_PERCENTAGE,Goods.APPLES,"Apples 10% off: ");
    }


    /** Calculate total discount on Bread (Buy 2 tin and get a loaf of bread for a half price)
     *
     * @param goods
     * @return double
     */
    public static double applySpecialOfferSoup(List<Goods> goods) {

        boolean hasMoreThanOneSoup = goods.stream()
                .filter(g-> Goods.SOUP.equals(g.getName()))
                .count() > 1;

        if (hasMoreThanOneSoup){
            Optional<Goods> bread = goods.stream()
                    .filter(g-> Goods.BREAD.equals(g.getName()))
                    .findFirst();

            double discount =  bread.isPresent() ? bread.get().applyDiscount(HALF_DISCOUNT):0.0;

            Util.writeDiscounts("Buy 2 tin and get a loaf of bread for a half price: ",discount);

            return discount;
        }

        return 0.0;
    }
}
