package com.trien.lyto_different_color.object;

import android.content.Context;
import android.content.SharedPreferences;

public class InForNguoiChoi {
    public int tienNguoiChoi = 100;
    private String tenFileLuuTru = "gameInFor";
    private Context ct;

    public InForNguoiChoi(Context ct) {
        this.ct = ct;
    }

    public void getData(){
        SharedPreferences sharedPreferences = ct.getSharedPreferences(tenFileLuuTru,Context.MODE_PRIVATE);
        tienNguoiChoi = sharedPreferences.getInt("tienNguoiChoi",100);
    }
    public void setData(){
        SharedPreferences sharedPreferences = ct.getSharedPreferences(tenFileLuuTru,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tienNguoiChoi",tienNguoiChoi);
        editor.apply();
    }

}
