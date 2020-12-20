import entities.Goods;
import factory.GoodsFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



public class PriceBasket {

    private static final String PRICE_BASKET = "PRICEBASKET";
    public static void main(String[] args) {
        pricingBasketGoods(Arrays.asList(args));
    }

    /**
     * Calculate discount and pricing of basket goods
     * @param args
     */
    public static void pricingBasketGoods(List<String> args){
//        System.out.println("Arguments count:"+args.size());

        List<Goods> listGoods = getListGoods(args);

        listGoods.forEach(System.out::println);

        double subtotal = getTotalPrice(listGoods);

        double discount = applySpecialOffers(listGoods);

        double total = subtotal-discount;

        Util.write("Subtotal: £",subtotal,"");

        if (discount == 0.0){
            Util.write("(no offers available)");
        }
        Util.write("Total: £",total);
    }

    /**
     * Sum all prices
     * @param listGoods
     * @return
     */
    private static double getTotalPrice(List<Goods> listGoods) {
        return listGoods.stream().mapToDouble(Goods::getPrice).sum();
    }

    /**
     * Apply and Calculate Special Offers
     * @param goods
     * @return double
     */
    public static double applySpecialOffers(List<Goods> goods){
        double totalDiscount = 0.0;
        double discount = 0.0;

        discount = SpecialOffers.applySpecialOfferApples(goods);

        totalDiscount += discount;

        discount = SpecialOffers.applySpecialOfferSoup(goods);

        totalDiscount += discount;

        return totalDiscount;
    }


    /**
     * Remove "Price Basket" String from args and transform String List to Goods List
     * @param goods
     * @return List<Goods>
     */
    private static List<Goods> getListGoods(List<String> goods) {
        return goods.stream()
                .map(String::toUpperCase)
                .filter(str -> !PRICE_BASKET.equals(str))
                .map(GoodsFactory::getGoods)
                .collect(Collectors.toList());
    }

}
