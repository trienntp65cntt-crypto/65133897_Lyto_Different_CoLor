package com.trien.lyto_different_color; // Nhớ đổi lại tên package nếu em đã đổi

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
    TextView txvLevel, txvTime, txvCoin, tvTouchToStart;
    CountDownTimer demnguoc;
    ImageView imgIcon, btnPause;
    int iconnhay = R.drawable.icon1;
    InForNguoiChoi nguoiChoi;

    LinearLayout layoutPauseMenu;
    Button btnResume, btnToggleSound;
    boolean isSoundOn = true;
    SharedPreferences sharedPreferences;
    MediaPlayer bgmPlayer;
    Toast toastThongBao;

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
        sharedPreferences = getSharedPreferences("CaiDatGame", MODE_PRIVATE);
        isSoundOn = sharedPreferences.getBoolean("sound", true);

        setupNhacNen();
    }

    private void anhXa(){
        gdvLisOMau = findViewById(R.id.gdvLisOMau);
        txvLevel = findViewById(R.id.txvLevel);
        txvTime = findViewById(R.id.txvTime);
        imgIcon = findViewById(R.id.imgIcon);
        txvCoin = findViewById(R.id.txvCoin);
        tvTouchToStart = findViewById(R.id.tvTouchToStart);
        btnPause = findViewById(R.id.btnPause);
        layoutPauseMenu = findViewById(R.id.layoutPauseMenu);
        btnResume = findViewById(R.id.btnResume);
        btnToggleSound = findViewById(R.id.btnToggleSound);
    }

    private void setUp(){
        updateTimeUI(dinhNghia.timeChay);
        txvCoin.setText(""+nguoiChoi.tienNguoiChoi);
        gdvLisOMau.setNumColumns(dinhNghia.soCot);
        gdvLisOMau.setAdapter(adapter);
        txvLevel.setText(""+dinhNghia.level);
        btnToggleSound.setText(isSoundOn ? "ÂM THANH: BẬT" : "ÂM THANH: TẮT");

        new CountDownTimer(2000,400){
            @Override
            public void onFinish() { if (!dinhNghia.hetGame) start(); }
            @Override
            public void onTick(long millisUntilFinished) {
                iconnhay = (iconnhay == R.drawable.icon2) ? R.drawable.icon1 : R.drawable.icon2;
                imgIcon.setImageResource(iconnhay);
            }
        }.start();
    }

    private void setClick() {
        tvTouchToStart.setOnClickListener(v -> {
            tvTouchToStart.setVisibility(View.GONE);
            upDateTime();
            gdvLisOMau.setOnItemClickListener((parent, view, position, id) ->
                    checkMau(arrOMau.get(position), view)
            );
        });

        // Xử lý nút Menu Pause
        btnPause.setOnClickListener(v -> {
            if (demnguoc != null) demnguoc.cancel();
            layoutPauseMenu.setVisibility(View.VISIBLE);
            gdvLisOMau.setEnabled(false);
            if (bgmPlayer != null && bgmPlayer.isPlaying()) bgmPlayer.pause();
        });

        btnResume.setOnClickListener(v -> {
            layoutPauseMenu.setVisibility(View.GONE);
            gdvLisOMau.setEnabled(true);
            upDateTime();
            if (isSoundOn && bgmPlayer != null && !bgmPlayer.isPlaying()) bgmPlayer.start();
        });

        btnToggleSound.setOnClickListener(v -> {
            isSoundOn = !isSoundOn;
            btnToggleSound.setText(isSoundOn ? "ÂM THANH: BẬT" : "ÂM THANH: TẮT");
            sharedPreferences.edit().putBoolean("sound", isSoundOn).apply();

            if (isSoundOn) {
                if (bgmPlayer != null && !bgmPlayer.isPlaying()) bgmPlayer.start();
            } else {
                if (bgmPlayer != null && bgmPlayer.isPlaying()) bgmPlayer.pause();
            }
        });
    }

    private void checkMau(OMau o, View viewClick ){
        if(o.maMau.equals(dinhNghia.mauIt)){
            hieuUngCongTien(viewClick);
            hieuUngNhayLevel();
            phatAmThanh(R.raw.tieng_dung);

            dinhNghia.level++;
            taoMau();
            upDate();
            dinhNghia.timeChay = dinhNghia.timeChay + dinhNghia.timeCong;
            demnguoc.cancel();
            upDateTime();
            nguoiChoi.tienNguoiChoi += 2;
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
        demnguoc =  new CountDownTimer(dinhNghia.timeChay, 20){
            @Override
            public void onTick(long millisUntilFinished) {
                dinhNghia.timeChay = (int) millisUntilFinished;
                updateTimeUI(dinhNghia.timeChay);
            }
            @Override
            public void onFinish() {
                txvTime.setText("00:00");
                hetGio();
            }
        }.start();
    }

    private void updateTimeUI(int timeLeftMs) {
        if (timeLeftMs > 0 ) {
            int soGiay = timeLeftMs / 1000;
            int miliGiay = (timeLeftMs % 1000) / 10;
            String miliStr = (miliGiay < 10) ? "0" + miliGiay : "" + miliGiay;
            txvTime.setText(soGiay + ":" + miliStr);
        } else {
            txvTime.setText("00:00");
        }
    }

    private void hetGio(){
        dinhNghia.hetGame = true;
        gdvLisOMau.setOnItemClickListener(null);
        Intent intent = new Intent(this,KetThucActivity.class);
        intent.putExtra("level",dinhNghia.level);
        startActivity(intent);
        finish();
    }

    private void setupNhacNen() {
        bgmPlayer = MediaPlayer.create(this, R.raw.nhac_nen);
        bgmPlayer.setLooping(true);
        if (isSoundOn) bgmPlayer.start();
    }

    private void phatAmThanh(int idAmThanh) {
        if (!isSoundOn) return;
        MediaPlayer mediaPlayer = MediaPlayer.create(this, idAmThanh);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mp.release());
    }

    private void rungDienThoai() {
        if (!isSoundOn) return;
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) vibrator.vibrate(300);
    }

    private void hieuUngCongTien(View viewXuatPhat) {
        final TextView tvPlus = new TextView(this);
        tvPlus.setText("+2");
        tvPlus.setTextColor(Color.YELLOW);
        tvPlus.setTextSize(25);
        tvPlus.setTypeface(null, Typeface.BOLD);

        final ViewGroup root = (ViewGroup) gdvLisOMau.getParent();
        root.addView(tvPlus);
        tvPlus.setX(gdvLisOMau.getX() + viewXuatPhat.getX() + viewXuatPhat.getWidth() / 3);
        tvPlus.setY(gdvLisOMau.getY() + viewXuatPhat.getY());

        tvPlus.animate()
                .translationYBy(-150).alpha(0).setDuration(800)
                .withEndAction(() -> root.removeView(tvPlus)).start();
    }

    private void hieuUngNhayLevel() {
        txvLevel.animate().scaleX(1.3f).scaleY(1.3f).setDuration(150)
                .withEndAction(() ->
                        txvLevel.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start()
                ).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bgmPlayer != null && bgmPlayer.isPlaying()) bgmPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSoundOn && layoutPauseMenu.getVisibility() == View.GONE) {
            if (bgmPlayer != null && !bgmPlayer.isPlaying()) bgmPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bgmPlayer != null) {
            bgmPlayer.release();
            bgmPlayer = null;
        }
    }
}