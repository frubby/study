package com.frw.tmp;

import lombok.Data;
import lombok.Getter;

/**
 * Created by fruwei on 16/10/5.
 */
@Getter
public class MaJiaPair  implements  Cloneable{

    public static  int PAIR_TWO=0;
    public static  int PAIR_THREE=1;
    public static  int PAIR_SERIAL=2;
    private int type;
    private int[] pair;
    public  MaJiaPair setPair(int... args ){
        pair=new int[args.length];
        int i=0;
        for(int val:args){
            pair[i++]=val;
        }
        return this;
    }

    public MaJiaPair setType(int type){
        this.type=type;
        return this;
    }

}
