package com.trien.lyto_different_color;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trien.lyto_different_color.object.OMau;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<OMau> arrOMau = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        while (arrOMau.size() < 49) {
            arrOMau.add(new OMau());
        }
    }
}