package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public abstract class Goods implements Serializable {
    public String name;
    public Double price;


    public static final String APPLES = "APPLES";
    public static final String BREAD = "BREAD";
    public static final String SOUP = "SOUP";
    public static final String MILK = "MILK";

    public abstract double applyDiscount(double discount) ;

}
