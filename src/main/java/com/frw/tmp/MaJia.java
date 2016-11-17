package com.frw.tmp;

import lombok.Data;

/**
 * Created by fruwei on 16/10/5.
 */
@Data
public class MaJia implements Cloneable {
    private int no;
    private int count;


    public static String getString(int nb) {
        String unit = "";
        String numStr = "";
        int num = 0;
        if (nb < 10) {
            unit = "条";
            num = nb;
        } else if (nb < 110) {
            unit = "万";
            num = nb - 100;
        } else if (nb < 1010) {
            unit = "筒";
        } else {
            unit = "E";
            num = nb - 1000;
        }

        switch (num) {
            case 1:
                numStr = "一";
                break;
            case 2:
                numStr = "二";
                break;
            case 3:
                numStr = "三";
                break;
            case 4:
                numStr = "四";
                break;
            case 5:
                numStr = "五";
                break;
            case 6:
                numStr = "六";
                break;
            case 7:
                numStr = "七";
                break;
            case 8:
                numStr = "八";
                break;
            case 9:
                numStr = "九";
                break;
        }
//        logger.info(numStr+unit);
        return numStr+unit;
    }

}
