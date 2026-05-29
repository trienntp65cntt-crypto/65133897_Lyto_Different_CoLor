package com.trien.lyto_different_color;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trien.lyto_different_color.adapter.OMauAdapter;
import com.trien.lyto_different_color.object.DinhNghia;
import com.trien.lyto_different_color.object.OMau;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    DinhNghia dinhNghia = new DinhNghia();
    ArrayList<OMau> arrOMau = new ArrayList<>();

    GridView gdvLisOMau;
    OMauAdapter adapter;
    TextView txvLevel;
    TextView txvTime;
    CountDownTimer demnguoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init() {
    taoMau();

        adapter = new OMauAdapter(this, 0, arrOMau);
    }

    private void anhXa(){
        gdvLisOMau = findViewById(R.id.gdvLisOMau);
        txvLevel = findViewById(R.id.txvLevel);
        txvTime = findViewById(R.id.txvTime);


    }

    private void setUp(){
        gdvLisOMau.setNumColumns(dinhNghia.soCot);
        gdvLisOMau.setAdapter(adapter);
        txvLevel.setText(""+dinhNghia.level);
        upDateTime();
    }
    private void setClick() {
        gdvLisOMau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkMau(arrOMau.get(i));
            }
        });
    }
    private void checkMau(OMau o ){
        if(o.maMau.equals(dinhNghia.mauIt)){
            dinhNghia.level++;
            taoMau();
            upDate();
            dinhNghia.timeChay = dinhNghia.timeChay + 1000;
            demnguoc.cancel();
            upDateTime();
        }else {
            Toast.makeText(this,"false",Toast.LENGTH_SHORT).show();
        }

    }
    private void taoMau(){
        dinhNghia.setLevel();
        dinhNghia.layMauNgauNhien();
        arrOMau.clear();
        while (arrOMau.size() < dinhNghia.soO) {
            arrOMau.add(new OMau(dinhNghia.mauNhieu));
        }
        Random r = new Random();
        arrOMau.get(r.nextInt(arrOMau.size())).maMau = dinhNghia.mauIt;
    }
    private void upDate(){
        adapter.upDate(arrOMau);
        gdvLisOMau.setNumColumns(dinhNghia.soCot);
        txvLevel.setText(""+dinhNghia.level);
    }

    private void upDateTime(){
        demnguoc =  new CountDownTimer(dinhNghia.timeChay,1){



            @Override
            public void onTick(long millisUntilFinished) {
                dinhNghia.timeChay = (int) millisUntilFinished;
                if (dinhNghia.timeChay != 0 ) {
                    int soGiay = dinhNghia.timeChay / 1000;
                    int miliGiay = dinhNghia.timeChay % 1000/10;
                    String times = soGiay + ":" + miliGiay;
                    txvTime.setText(times);
                }else {
                    txvTime.setText("00:00");
                }
            }
            @Override
            public void onFinish() {
                txvTime.setText("00:00");

                hetGio();
            }
        }.start();
    }
    private void hetGio(){
        gdvLisOMau.setOnItemClickListener(null);
    }
}