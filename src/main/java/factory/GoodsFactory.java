package factory;

import entities.*;

public class GoodsFactory {

    //use goodsType method to get object of type
    public static Goods getGoods(String goodsType){
        if(goodsType == null){
            return null;
        }
        if(goodsType.equalsIgnoreCase(Goods.APPLES)){
            return new Apples();

        } else if(goodsType.equalsIgnoreCase(Goods.BREAD)){
            return new Bread();

        } else if(goodsType.equalsIgnoreCase(Goods.MILK)){
            return new Milk();
        } else if(goodsType.equalsIgnoreCase(Goods.SOUP)){
            return new Soup();
        }

        return null;
    }
}
