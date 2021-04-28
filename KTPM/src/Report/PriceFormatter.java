/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {

    public static String format(float num) {
        BigDecimal trieu = new BigDecimal(num * 1);
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmoney = NumberFormat.getCurrencyInstance(vietnam);

        return fmoney.format(trieu);
    }
    public static String format1(double num) {
        BigDecimal trieu = new BigDecimal(num * 1);
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmoney = NumberFormat.getCurrencyInstance(vietnam);

        return fmoney.format(trieu);
    }
}