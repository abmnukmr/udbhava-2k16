package com.udbhava;

import android.os.Build;

/**
 * Created by ABHIMANYU SINGH KASH on 8/8/2016.
 */
public class Utill {
    public  static boolean islollipoporGreater(){
        return Build.VERSION.SDK_INT >= 21?true:false;

    }
    public  static boolean isjellybeanGreater(){
        return Build.VERSION.SDK_INT >= 16?true:false;

    }


}
