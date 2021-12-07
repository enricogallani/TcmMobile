package com.example.front.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.front.R;
import com.example.front.db.entity.User;
import com.example.front.db.helper.UserHelper;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btLogin = findViewById(R.id.btnLogin);
        Button btRegistro = findViewById(R.id.btnRegistro);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);

        btLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etSenha.getText().toString();

            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "E-mail é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etSenha.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Senha é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new UserHelper(LoginActivity.this).login(email, password);
            if (user != null) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos !!!", Toast.LENGTH_SHORT).show();
            }
        });

        btRegistro.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, CadastroFuncActivity.class));
        });
    }


}