package com.trien.lyto_different_color;

import android.os.Bundle;
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
    }

    private void init() {
        while (arrOMau.size() < dinhNghia.soO) {
            arrOMau.add(new OMau());
        }
        adapter = new OMauAdapter(this, 0, arrOMau);
    }

    private void anhXa(){
        gdvLisOMau = findViewById(R.id.gdvLisOMau);
    }

    private void setUp(){
        gdvLisOMau.setNumColumns(dinhNghia.soCot);
        gdvLisOMau.setAdapter(adapter);
    }
}