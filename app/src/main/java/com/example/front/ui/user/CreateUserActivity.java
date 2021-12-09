package com.example.front.ui.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.front.R;
import com.example.front.db.entity.User;
import com.example.front.db.helper.UserHelper;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnCadastro = findViewById(R.id.btnCadastro);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);
        EditText etCargo = findViewById(R.id.etCargo);
        EditText etNome = findViewById(R.id.etNome);

        btnCadastro.setOnClickListener(v -> {
            if (etNome.getText().toString().isEmpty()) {
                Toast.makeText(CreateUserActivity.this, "Nome é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(CreateUserActivity.this, "E-mail é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etCargo.getText().toString().isEmpty()) {
                Toast.makeText(CreateUserActivity.this, "Cargo é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etSenha.getText().toString().isEmpty()) {
                Toast.makeText(CreateUserActivity.this, "Senha é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            UserHelper userHelper = new UserHelper(CreateUserActivity.this);
            if (userHelper.findByEmail(etEmail.getText().toString()) != null) {
                Toast.makeText(CreateUserActivity.this, "Esse e-mail já existe !!!", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.setName(etNome.getText().toString());
            user.setMail(etEmail.getText().toString());
            user.setOffice(etCargo.getText().toString());
            user.setPassword(etSenha.getText().toString());
            userHelper.inserir(user);

            onBackPressed();
        });
    }
}