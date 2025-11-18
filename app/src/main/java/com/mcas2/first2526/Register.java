package com.mcas2.first2526;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        FormUtils formUtils = new FormUtils();

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContinue = true;
                if (formUtils.isTILEmpty(registerTILuserName)){
                    //Toast.makeText(Register.this, "Nombre vacío", Toast.LENGTH_SHORT).show();
                    registerTILuserName.setErrorEnabled(true);
                    registerTILuserName.setError("Nombre vacío");
                    canContinue = false;
                }
                if (!isEmailCorrect(registerTILemail)) {
                    //Toast.makeText(Register.this, "Email incorrecto", Toast.LENGTH_SHORT).show();
                    registerTILemail.setErrorEnabled(true);
                    registerTILemail.setError("Tu email está mal escrito");
                    canContinue = false;
                }

                if (!arePasswordsTheSame(registerTILpassword, registerTILpasswordDoubleChek))  {
                    registerTILpasswordDoubleChek.setErrorEnabled(true);
                    registerTILpasswordDoubleChek.setError("La contraseña no es válida");
                    if (formUtils.isTILEmpty(registerTILpassword) && formUtils.isTILEmpty(registerTILpasswordDoubleChek)) {
                        Toast.makeText(Register.this, "No has escrito nada en el campo constraseña", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Las dos contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                    canContinue = false;
                }

                if (canContinue) {
                    editor.putString("userName",String.valueOf(registerTILuserName.getEditText().getText()));
                    editor.putString("email",String.valueOf(registerTILemail.getEditText().getText()));
                    editor.putString("password", formUtils.generateHashedPassword(formUtils.getTILText(registerTILpassword)));
                    editor.commit();

                    Intent intent = new Intent(Register.this, Principal.class);
                    startActivity(intent);
                }

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

    public boolean arePasswordsTheSame(TextInputLayout textInputLayout, TextInputLayout textInputLayoutCheck) {
        String firstPassword = String.valueOf(textInputLayout.getEditText().getText());
        String secondPassword = String.valueOf(textInputLayoutCheck.getEditText().getText());
        return firstPassword.equals(secondPassword) && !firstPassword.isEmpty();
    }

}