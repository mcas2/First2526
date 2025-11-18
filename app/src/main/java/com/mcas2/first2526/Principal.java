package com.mcas2.first2526;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.mcas2.first2526.databinding.ActivityPrincipalBinding;
import com.mcas2.first2526.ui.frmanager.Paginador;

public class Principal extends AppCompatActivity {
    Button mainButton;
    TextView mainTV;
    int contador;

    private ActivityPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Paginador paginador = new Paginador(this, getSupportFragmentManager());
        ViewPager viewPager = binding.coralViewPager;
        viewPager.setAdapter(paginador);



       //Button mainButton = findViewById(R.id.mainButton);
       //TextView mainTV = findViewById(R.id.mainTV);

       //Bundle bundle = getIntent().getExtras();
       //if (bundle != null) {
       //    String tiempo = String.valueOf(bundle.getLong("tiempoInvertido", 0));
       //    mainTV.setText(tiempo+" has tardado en llenar el login.");
       //}
    }
}