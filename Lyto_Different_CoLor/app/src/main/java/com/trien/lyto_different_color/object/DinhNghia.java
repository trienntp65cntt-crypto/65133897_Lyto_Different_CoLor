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
            "#CC6699"

    };

    private String arrMauIt[] = new String[]{
            "#33CC66",
            "#33CCCC",
            "#FF99CC",
            "#3399CC",
            "#CC6666"
    };
    public int level = 1;
    public int timeTong = 10;
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
            soCot = 4;
        }else if (level < 10 ){
            soCot = 5;
        }else if (level < 15 ){
            soCot = 6;
        }else if (level < 20 ){
            soCot = 7;
        }else if (level < 25 ){
            soCot = 8;
        }else if (level < 30 ){
            soCot = 9;
        } else {
            soCot = 10;
        }
        soO = soCot*soCot;
    }
}
