package com.mcas2.first2526;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout registerTILuserName = findViewById(R.id.registerTILuserName);
        TextInputLayout registerTILemail = findViewById(R.id.registerTILemail);
        TextInputLayout registerTILpassword = findViewById(R.id.registerTILpassword);
        TextInputLayout registerTILpasswordDoubleChek = findViewById(R.id.registerTILpasswordDoubleCheck);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserNameEmpty(registerTILuserName)){
                    //Toast.makeText(Register.this, "Nombre vacío", Toast.LENGTH_SHORT).show();
                    registerTILuserName.setErrorEnabled(true);
                    registerTILuserName.setError("Nombre vacío");
                }
                if (!isEmailCorrect(registerTILemail)) {
                    //Toast.makeText(Register.this, "Email incorrecto", Toast.LENGTH_SHORT).show();
                    registerTILemail.setErrorEnabled(true);
                    registerTILemail.setError("Tu email está mal escrito");
                }/** else if (!arePasswordsTheSame())  {asd
                    Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    public boolean isEmailCorrect(TextInputLayout emailTIL) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern p = Pattern.compile(emailPattern);
        String email = String.valueOf(emailTIL.getEditText().getText());
        Matcher m = p.matcher(email);
        return m.find();
    }

    public boolean isUserNameEmpty(TextInputLayout userNameTIL) {
        String username = String.valueOf(userNameTIL.getEditText().getText());
        return username.isEmpty();
    }
}