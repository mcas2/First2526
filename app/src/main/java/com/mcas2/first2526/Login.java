package com.mcas2.first2526;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginButton = findViewById(R.id.loginButton);
        TextView loginTVRegister = findViewById(R.id.loginTVRegister);
        TextInputLayout loginUserNameTIL = findViewById(R.id.loginTILuserName);
        TextInputLayout loginPasswordTIL = findViewById(R.id.loginTILpassword);
        FormUtils formUtils = new FormUtils();

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        String hashedPassword = sharedPref.getString("password", "NADA");
        Log.d("hashedPassword", hashedPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContinue = true;
                if (formUtils.isTILEmpty(loginUserNameTIL)) {
                    loginUserNameTIL.setErrorEnabled(true);
                    loginUserNameTIL.setError("Necesitas acceder con un nombre de usuario.");
                    canContinue = false;
                }
                if (formUtils.isTILEmpty(loginPasswordTIL)) {
                    loginPasswordTIL.setErrorEnabled(true);
                    loginPasswordTIL.setError("La contraseña está vacía.");
                    canContinue = false;
                } else if (!formUtils.checkPassword(formUtils.getTILText(loginPasswordTIL), hashedPassword)) {
                    loginPasswordTIL.setErrorEnabled(true);
                    loginPasswordTIL.setError("La contraseña es incorrecta.");
                    canContinue = false;
                }
                if (canContinue) {
                    Intent intentMain = new Intent(Login.this, Register.class);
                    startActivity(intentMain);
                }
            }
        });


        loginTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(Login.this, Register.class);
                startActivity(intentRegister);
            }
        });

    }
}