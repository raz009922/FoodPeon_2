package com.example.dcastalia.foodpeon3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

/**
 * Created by dcastalia on 2/20/18.
 */

public class IntroManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("first",0);
        editor=sharedPreferences.edit();
    }


    public void setFirst(boolean isfirst){
        editor.putBoolean("check",isfirst);
        editor.commit();

    }

    public boolean check()
    {

        return sharedPreferences.getBoolean("check",true);
    }
}
