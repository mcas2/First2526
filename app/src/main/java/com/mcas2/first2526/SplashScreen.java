package com.mcas2.first2526;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class SplashScreen extends AppCompatActivity {

    ImageView fondo;
    ImageView splashLogo;
    TextView nombreApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        try {
            setContentView(R.layout.activity_splash);
        } catch (Exception e) {
            Log.e("TAG", "onCreateView", e);
            throw e;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Animation aparicion = AnimationUtils.loadAnimation(this, R.anim.aparecer);

        nombreApp = findViewById(R.id.splashAppName);
        nombreApp.startAnimation(aparicion);
        splashLogo = findViewById(R.id.splashLogo);
        splashLogo.startAnimation(aparicion);

        fondo = findViewById(R.id.splashImagenFondo);
        Glide.with(this)
                    .load("https://i.postimg.cc/ZYFhn7rQ/estrella.png")
                    .transition(DrawableTransitionOptions.withCrossFade(2000))
                    .centerCrop()
                    .into(fondo);

        launchNextActivity();
    }

    public void launchNextActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
            }
        }, 4000);

    }
}