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

public class CadastroFuncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_func);

        Button btnCadastro = findViewById(R.id.btnCadastro);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);
        EditText etCargo = findViewById(R.id.etCargo);
        EditText etNome = findViewById(R.id.etNome);

        btnCadastro.setOnClickListener(v -> {
            if (etNome.getText().toString().isEmpty()) {
                Toast.makeText(CadastroFuncActivity.this, "Nome é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(CadastroFuncActivity.this, "E-mail é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etCargo.getText().toString().isEmpty()) {
                Toast.makeText(CadastroFuncActivity.this, "Cargo é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etSenha.getText().toString().isEmpty()) {
                Toast.makeText(CadastroFuncActivity.this, "Senha é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            UserHelper userHelper = new UserHelper(CadastroFuncActivity.this);
            if (userHelper.findByEmail(etEmail.getText().toString()) != null) {
                Toast.makeText(CadastroFuncActivity.this, "Esse e-mail já existe !!!", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.setName(etNome.getText().toString());
            user.setMail(etEmail.getText().toString());
            user.setOffice(etCargo.getText().toString());
            user.setPassword(etSenha.getText().toString());
            user = userHelper.inserir(user);

            if (user.getId() != null){
                startActivity(new Intent(CadastroFuncActivity.this, MainActivity.class));
            } else {
                Toast.makeText(CadastroFuncActivity.this, "Tivemos um problema, tente novamente !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}