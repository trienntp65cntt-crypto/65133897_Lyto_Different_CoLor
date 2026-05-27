package com.trien.lyto_different_color;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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
    }

    private void setUp(){
        gdvLisOMau.setNumColumns(dinhNghia.soCot);
        gdvLisOMau.setAdapter(adapter);
    }
    private void setClick() {
        gdvLisOMau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                taoMau();
                upDate();
            }


        });
    }
    private void taoMau(){
        arrOMau.clear();
        while (arrOMau.size() < dinhNghia.soO) {
            arrOMau.add(new OMau(dinhNghia.mauNhieu));
        }
        Random r = new Random();
        arrOMau.get(r.nextInt(arrOMau.size())).maMau = dinhNghia.mauIt;
    }
    private void upDate(){
        adapter.upDate(arrOMau);
    }
}