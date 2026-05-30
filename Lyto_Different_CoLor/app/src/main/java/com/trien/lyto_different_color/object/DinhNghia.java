package com.trien.lyto_different_color.object;

import java.util.Random;

public class DinhNghia {
    public int soCot = 8;
    public int soO = 7;
    public String mauNhieu ="#FF3399";
    public String mauIt ="#FF33CC";
    private String arrMauNhieu[] = new String[]{
            "#33CC33",
            "#33CCFF",
            "#FF99FF",
            "#3399FF",
            "#CC6699",
            "#3300CC",
            "#993300",
            "#FF9933",
            "#CC0066",
            "#660099",
            "#333300",

    };

    private String arrMauIt[] = new String[]{
            "#33CC66",
            "#33CCCC",
            "#FF99CC",
            "#3399CC",
            "#CC6666",
            "#3300FF",
            "#993333",
            "#FF9900",
            "#CC0099",
            "#6600CC",
            "#333333",
    };
    public int level = 1;
    public int timeTong = 30;
    public int timeChay = timeTong * 1000;
    public int timeCong = 100;

    public boolean hetGame = false;

    public void layMauNgauNhien(){
        Random r = new Random();
        int vt = r.nextInt(arrMauNhieu.length);
        mauNhieu = arrMauNhieu[vt];
        mauIt = arrMauIt[vt];
    }

    public void setLevel(){
        if (level<3) {
            soCot = 2;
        }else if (level < 10 ){
            soCot = 3;
        }else if (level < 15 ){
            soCot = 4;
        }else if (level < 20 ){
            soCot = 5;
        }else if (level < 25 ){
            soCot = 6;
        }else if (level < 30 ){
            soCot = 7;
        }else if (level < 35 ){
            soCot = 8;
        }else if (level < 40 ){
            soCot = 9;
        }else if (level < 45 ){
            soCot = 10;
        }else if (level < 50 ){
            soCot = 11;
        }else if (level < 55 ){
            soCot = 12;
        }else if (level < 60 ){
            soCot = 13;
        } else {
            soCot = 18;
        }
        soO = soCot*soCot;
    }
}
