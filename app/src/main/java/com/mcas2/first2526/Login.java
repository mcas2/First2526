package com.mcas2.first2526;

import android.content.Intent;
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
        TextInputLayout loginTIL = findViewById(R.id.loginTILuserName);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * No hace falta
                 */
                String username = String.valueOf(loginTIL.getEditText().getText());
                Toast toast = Toast.makeText(Login.this, username, Toast.LENGTH_SHORT);
                toast.show();
                // Esto sí:
                Intent intentMain = new Intent(Login.this, Register.class);
                startActivity(intentMain);
            }
        });

        /**
         * TAREA: HACER UNA ACTIVIDAD PARA REGISTRARSE Y PODER LLEGAR A ELLA DESDE EL loginTVRegister
         */

    }
}