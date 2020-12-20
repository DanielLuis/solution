import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Util {
    /**
     * Write discounts on console
     * @param title
     * @param value
     * @param operator
     */
    public static void write(String title, double value, String operator) {
        DecimalFormat df = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.ENGLISH));

        if (value >=1.0){
            System.out.println(title+"£ "+operator+ df.format(value));
        }else if(value>0.0){
            System.out.println(title+operator+df.format(value*100) +"p");
        }else if (title!=null && title.length()>0){
            System.out.println(title);
        }
    }

    /**
     *  Write discounts on console
     * @param title
     * @param value
     */
    public static void write(String title, double value) {
        write(title,value,"");
    }

    /**
     *  Write discounts on console
     * @param title
     */
    public static void write(String title) {
        write(title,0.0,"");
    }

    /**
     * Write discounts on console
     * @param title
     * @param value
     */
    public static void writeDiscounts(String title,double value) {
       write(title,value,"-");
    }
    /**
     * Write discounts on console
     * @param title
     */
    public static void writeDiscounts(String title) {
        writeDiscounts(title, 0.0);
    }


}
