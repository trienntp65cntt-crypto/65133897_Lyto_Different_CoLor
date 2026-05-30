package com.trien.lyto_different_color;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trien.lyto_different_color.adapter.OMauAdapter;
import com.trien.lyto_different_color.object.InForNguoiChoi;

public class KetThucActivity extends AppCompatActivity {
    TextView txvLevel;
    int level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_thuc);
        init();
        anhXa();
        setUp();
        setClick();
    }

        private void init() {
            level = getIntent().getIntExtra("level",0);
        }

        private void anhXa(){
        txvLevel = findViewById(R.id.txvLevel);

        }

        private void setUp(){
            txvLevel.setText(""+level);
        }

        private void setClick() {

        }
        public void choiLai(View view){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        public void thoatGame(View view){
        finish();
        }

}