package com.trien.lyto_different_color;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.appcompat.app.AppCompatActivity;

import com.trien.lyto_different_color.adapter.OMauAdapter;
import com.trien.lyto_different_color.dilog.KetThucGameDilog;
import com.trien.lyto_different_color.object.DinhNghia;
import com.trien.lyto_different_color.object.InForNguoiChoi;
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
    ImageView imgIcon;
    int iconnhay = R.drawable.icon1;
    TextView txvCoin;
    InForNguoiChoi nguoiChoi;
    TextView tvTouchToStart;

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
        nguoiChoi = new InForNguoiChoi(this);
        nguoiChoi.getData();
        taoMau();
        adapter = new OMauAdapter(this, 0, arrOMau);
    }

    private void anhXa(){
        gdvLisOMau = findViewById(R.id.gdvLisOMau);
        txvLevel = findViewById(R.id.txvLevel);
        txvTime = findViewById(R.id.txvTime);
        imgIcon = findViewById(R.id.imgIcon);
        txvCoin = findViewById(R.id.txvCoin);
        tvTouchToStart = findViewById(R.id.tvTouchToStart);
    }

    private void setUp(){
        int soGiay = dinhNghia.timeChay / 1000;
        int miliGiay = (dinhNghia.timeChay % 1000) / 10;
        txvTime.setText(soGiay + ":" + (miliGiay < 10 ? "0" + miliGiay : miliGiay));
        txvCoin.setText(""+nguoiChoi.tienNguoiChoi);
        gdvLisOMau.setNumColumns(dinhNghia.soCot);
        gdvLisOMau.setAdapter(adapter);
        txvLevel.setText(""+dinhNghia.level);
        new CountDownTimer(2000,400){
            @Override
            public void onFinish() {
                if (dinhNghia.hetGame == false) {
                    start();
                }
            }
            @Override
            public void onTick(long millisUntilFinished) {
                if (iconnhay == R.drawable.icon2){
                    iconnhay = R.drawable.icon1;
                }else {
                    iconnhay = R.drawable.icon2;
                }
                imgIcon.setImageResource(iconnhay);
            }
        }.start();
    }

    private void setClick() {
        tvTouchToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTouchToStart.setVisibility(View.GONE);
                upDateTime();
                gdvLisOMau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // TRUYỀN BIẾN view VÀO HÀM checkMau
                        checkMau(arrOMau.get(i), view);
                    }
                });
            }
        });
    }

    // NHẬN THÊM THAM SỐ viewClick ĐỂ BIẾT VỊ TRÍ CHẠM
    private void checkMau(OMau o, View viewClick){
        if(o.maMau.equals(dinhNghia.mauIt)){
            // GỌI 2 HIỆU ỨNG TẠI ĐÂY
            hieuUngCongTien(viewClick);
            hieuUngNhayLevel();

            dinhNghia.level++;
            taoMau();
            upDate();
            dinhNghia.timeChay = dinhNghia.timeChay + dinhNghia.timeCong;
            demnguoc.cancel();
            upDateTime();
            nguoiChoi.tienNguoiChoi = nguoiChoi.tienNguoiChoi + 2;
            txvCoin.setText(""+nguoiChoi.tienNguoiChoi);
            nguoiChoi.setData();
        }else {
            rungDienThoai();
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
        dinhNghia.hetGame = true;
        gdvLisOMau.setOnItemClickListener(null);

        Intent intent = new Intent(this,KetThucActivity.class);
        intent.putExtra("level",dinhNghia.level);
        startActivity(intent);
        finish();
    }

    private void rungDienThoai() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(300);
        }
    }

    // --- CÁC HÀM XỬ LÝ HIỆU ỨNG (ANIMATIONS) ---

    private void hieuUngCongTien(View viewXuatPhat) {
        final TextView tvPlus = new TextView(this);
        tvPlus.setText("+2");
        tvPlus.setTextColor(Color.YELLOW);
        tvPlus.setTextSize(25);
        tvPlus.setTypeface(null, Typeface.BOLD);

        // Thêm TextView vào View cha (bọc ngoài GridView)
        final ViewGroup root = (ViewGroup) gdvLisOMau.getParent();
        root.addView(tvPlus);

        // Tính toán tọa độ xuất hiện dựa trên ô màu được chạm
        tvPlus.setX(gdvLisOMau.getX() + viewXuatPhat.getX() + viewXuatPhat.getWidth() / 3);
        tvPlus.setY(gdvLisOMau.getY() + viewXuatPhat.getY());

        // Hiệu ứng bay lên và mờ dần
        tvPlus.animate()
                .translationYBy(-150)
                .alpha(0)
                .setDuration(800)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        root.removeView(tvPlus); // Giải phóng bộ nhớ
                    }
                }).start();
    }

    private void hieuUngNhayLevel() {
        txvLevel.animate()
                .scaleX(1.3f)
                .scaleY(1.3f)
                .setDuration(150)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txvLevel.animate()
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .setDuration(150)
                                .start();
                    }
                }).start();
    }
}