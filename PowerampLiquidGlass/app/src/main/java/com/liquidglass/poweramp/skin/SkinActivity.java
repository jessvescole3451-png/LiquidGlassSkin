package com.liquidglass.poweramp.skin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.color.DynamicColors;

public class SkinActivity extends AppCompatActivity {

    private static final String POWERAMP_PACKAGE = "com.maxmpz.audioplayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplica Material You (Dynamic Colors) automáticamente
        // En Android 12+ usará los colores de tu fondo de pantalla
        // En Android 11 y menores usará el color base del tema
        DynamicColors.applyToActivityIfAvailable(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        Button btnApply = findViewById(R.id.btn_apply);
        Button btnOpen  = findViewById(R.id.btn_open_poweramp);

        btnApply.setOnClickListener(v -> applySkin());
        btnOpen.setOnClickListener(v -> openPoweramp());
    }

    private void applySkin() {
        // Lanza Poweramp indicando que aplique esta skin
        Intent intent = new Intent("com.maxmpz.audioplayer.action.OPEN_LIBRARY");
        intent.setPackage(POWERAMP_PACKAGE);
        intent.putExtra("skin", getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
            openPlayStore();
        }
    }

    private void openPoweramp() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(POWERAMP_PACKAGE);
        if (intent != null) {
            startActivity(intent);
        } else {
            openPlayStore();
        }
    }

    private void openPlayStore() {
        startActivity(new Intent(Intent.ACTION_VIEW,
            Uri.parse("market://details?id=" + POWERAMP_PACKAGE)));
    }
}
