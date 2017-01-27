package util;

import java.text.DecimalFormat;

/**
 * Created by Hytham on 1/27/2017.
 */

public class Utilities {
    public static String formatNumber( int value){
        DecimalFormat formater = new DecimalFormat("#,###,###");
        String formatted = formater.format(value);
        return formatted;
    }
}
