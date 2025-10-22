package com.mcas2.first2526;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
        setContentView(R.layout.activity_splash);
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
                    .load("https://plus.unsplash.com/premium_photo-1685077715983-772598c45360?q=80&w=686&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
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