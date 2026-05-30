package com.trien.lyto_different_color.dilog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.trien.lyto_different_color.R;

public class KetThucGameDilog extends Dialog {
    public KetThucGameDilog(@NonNull Context context, int level) {
        super(context);
        setContentView(R.layout.dilog_het_game);
        TextView txvLevel = findViewById(R.id.txvLevel);
        txvLevel.setText(""+level);
    }
}
