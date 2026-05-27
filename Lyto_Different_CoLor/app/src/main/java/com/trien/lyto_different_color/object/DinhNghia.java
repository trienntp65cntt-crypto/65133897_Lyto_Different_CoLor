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

    public DinhNghia(){
        soO = soCot*soCot;
    }

    public void layMauNgauNhien(){
        Random r = new Random();
        int vt = r.nextInt(arrMauNhieu.length);
        mauNhieu = arrMauNhieu[vt];
        mauIt = arrMauIt[vt];
    }
}
