package com.frw.tmp;

import com.google.gson.internal.LinkedTreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 3*n+2
 * Created by fruwei on 16/10/5.
 */
public class MaJiaCal {
    static Logger logger = LoggerFactory.getLogger("frw");

    static final int TIAO[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static final int WAN[] = {101, 102, 103, 104, 105, 106, 107, 108, 109};
    static final int TONG[] = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009};


    public static void main(String[] args) {


        int src[] = {3, 3, 4,  5,5,5, 6,6, 7,7, 8,8,8};
//        List<MaJia> list =array2List(src);
//        judgeHu(list);

        findWhoCanHu(src);

    }


    public static void findWhoCanHu(int[] src) {
        TreeMap<Integer, Integer> map=array2Map(src);

        for (int i = 1; ; i++) {
            if(i==10)
                i=101;
            if(i==110)
                i=1001;
            if(i==1010) break;
            TreeMap<Integer, Integer> tmpmap=array2Map(src);

            if(!map.containsKey(i)){
                tmpmap.put(i,1);
            }else{
                if(map.get(i).intValue()<4){
                    tmpmap.put(i,map.get(i).intValue()+1);
                }else{
                    continue;
                }
            }
            logger.info("@@@@@@@@@@@@ + "+i);
            judgeHu(map2List(tmpmap));
        }

    }

    public static TreeMap<Integer, Integer> array2Map(int[] src) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i : src) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i).intValue() + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;

    }
    public static List<MaJia> map2List(TreeMap<Integer, Integer> map) {
        List<MaJia> list = new ArrayList<MaJia>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            MaJia maJia = new MaJia();
            maJia.setNo(entry.getKey());
            maJia.setCount(entry.getValue());
            list.add(maJia);
        }
        return list;
    }


    public static List<MaJia> array2List(int[] src) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i : src) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i).intValue() + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<MaJia> list = new ArrayList<MaJia>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            MaJia maJia = new MaJia();
            maJia.setNo(entry.getKey());
            maJia.setCount(entry.getValue());
            list.add(maJia);
        }
        return list;
    }

    public static void judgeHu(List<MaJia> list) {

        for (int i = 0; i < list.size(); i++) {
            List<MaJia> list1 = MaJiaCal.cloneListMajia(list);
            MaJia item = list1.get(i);

            if (item.getCount() >= 2) {
                if (item.getCount() == 2)
                    item.setCount(0);
                else
                    item.setCount(item.getCount() - 2);
                List<MaJiaPair> huList = new ArrayList<MaJiaPair>();
                MaJiaPair maJiaPair = new MaJiaPair().setPair(item.getNo(), item.getNo()).setType(MaJiaPair.PAIR_TWO);
                huList.add(maJiaPair);
                judgeHuThree(list1, huList);

            }
        }


    }


    public static void judgeHuThree(List<MaJia> list, List<MaJiaPair> huList) {

        int size = list.size();
        int i = 0;
        int mark = 0;
        for (; i < size; i++) {
            if (list.get(i).getCount() > 0) {
                mark = 1;
                break;
            }
        }
        if (mark == 0) {
            logger.info("###############");

            for (MaJiaPair pair : huList) {
                int rel[] = pair.getPair();
                String str = "";
                for (int k = 0; k < rel.length; k++) {
                    str += rel[k] + " ";
                }
                logger.info(str);
            }

            return;

        }

        MaJia current = list.get(i);
        int no = current.getNo();
        int count = current.getCount();
        if (count < 3) { //must 123 * 2
            if (i + 2 < size) {
                if (list.get(i + 1).getNo() == no + 1 && list.get(i + 2).getNo() == no + 2 &&
                        list.get(i + 1).getCount() >= count && list.get(i + 2).getCount() >= count) {
                    list.get(i + 1).setCount(list.get(i + 1).getCount() - count);
                    list.get(i + 2).setCount(list.get(i + 2).getCount() - count);
                    current.setCount(0);
                    for (int j = 0; j < count; j++) {
                        huList.add(new MaJiaPair().setType(MaJiaPair.PAIR_SERIAL)
                                .setPair(current.getNo(), list.get(i + 1).getNo(), list.get(i + 2).getNo()));
                    }
                    judgeHuThree(list, huList);

                }
            }
        } else if (count >= 3) {
            // AAA
            List<MaJia> list1 = MaJiaCal.cloneListMajia(list);
            List<MaJiaPair> huList1 = MaJiaCal.cloneListrMajiaPair(huList);
            current = list1.get(i);
            current.setCount(count - 3);
            huList1.add(new MaJiaPair().setType(MaJiaPair.PAIR_THREE)
                    .setPair(current.getNo(), current.getNo(), current.getNo()));
            judgeHuThree(list1, huList1);


            // ABC * count
            current = list.get(i);
            List<MaJiaPair> huList2 = MaJiaCal.cloneListrMajiaPair(huList);
            if (i + 2 < size) {
                if (list.get(i + 1).getNo() == no + 1 && list.get(i + 2).getNo() == no + 2 &&
                        list.get(i + 1).getCount() >= count && list.get(i + 2).getCount() >= count) {
                    list.get(i + 1).setCount(list.get(i + 1).getCount() - count);
                    list.get(i + 2).setCount(list.get(i + 2).getCount() - count);
                    current.setCount(0);
                    for (int j = 0; j < count; j++) {
                        huList2.add(new MaJiaPair().setType(MaJiaPair.PAIR_SERIAL)
                                .setPair(current.getNo(), list.get(i + 1).getNo(), list.get(i + 2).getNo()));
                    }
                    judgeHuThree(list, huList2);

                }
            }
        }


    }


    public static List<MaJia> cloneListMajia(List<MaJia> src) {
        List<MaJia> dest = new ArrayList<MaJia>();
        for (MaJia item : src) {
            MaJia tmp = new MaJia();
            tmp.setCount(item.getCount());
            tmp.setNo(item.getNo());
            dest.add(tmp);
        }
        return dest;
    }

    public static List<MaJiaPair> cloneListrMajiaPair(List<MaJiaPair> src) {
        List<MaJiaPair> dest = new ArrayList<MaJiaPair>();
        for (MaJiaPair item : src) {
            MaJiaPair tmp = new MaJiaPair();
            tmp.setType(item.getType()).setPair(item.getPair());
            dest.add(tmp);
        }
        return dest;

    }


    public static void randdomListHu() {

    }

    public static void randomList(int mark) {
        int list[] = new int[9];
        int num = 14;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        Random rd = new Random();
        int seed;
        while (num > 0) {
            if (mark == 1) {
                seed = rd.nextInt(1000);
                seed = seed % 9 + 1;
                if (!map.containsKey(seed)) {
                    map.put(seed, 1);
                } else if (map.containsKey(seed) && map.get(seed) <= 4) {
                    map.put(seed, map.get(seed) + 1);
                } else {
                    continue;
                }
                num--;
            }
        }
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            logger.info("{},{}", MaJia.getString(item.getKey()), item.getValue());
        }

    }


}
