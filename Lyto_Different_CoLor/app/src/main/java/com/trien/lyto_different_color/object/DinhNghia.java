package com.trien.lyto_different_color.object;

import java.util.Random;

public class DinhNghia {
    public int soCot = 8;
    public int soO = 7;
    public String mauNhieu ="#FF3399";
    public String mauIt ="#FF33CC";

    public int level = 1;
    public int timeTong = 40;
    public int timeChay = timeTong * 1000;
    public int timeCong = 500;

    public boolean hetGame = false;

    public void layMauNgauNhien() {
        Random r = new Random();
        int red = r.nextInt(180) + 20;
        int green = r.nextInt(180) + 20;
        int blue = r.nextInt(180) + 20;

        int delta = 70 - (level * 2);
        if (delta < 5) {
            delta = 5;
        }
        mauNhieu = String.format("#%02X%02X%02X", red, green, blue);
        int redIt = red + delta;
        int greenIt = green + delta;
        int blueIt = blue + delta;

        if (redIt > 255) redIt = 255;
        if (greenIt > 255) greenIt = 255;
        if (blueIt > 255) blueIt = 255;
        mauIt = String.format("#%02X%02X%02X", redIt, greenIt, blueIt);
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
