package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.front.db.DBContract;
import com.example.front.db.DBHelper;
import com.example.front.db.entity.User;

public class LoginActivity extends AppCompatActivity {

    private SQLiteDatabase db;

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
            }

            if (etSenha.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Senha é obrigatório!!", Toast.LENGTH_SHORT).show();
            }
        });

        btRegistro.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, CadastroFuncActivity.class));
        });
    }

    public User login(String email, String password) {
        User user = new User();

        String[] columns = new String[]{
                "_id", "name", "email", "password"};
        String[] args = new String[]{email, password};

        db = new DBHelper(LoginActivity.this).getWritableDatabase();
        Cursor c = db.query(DBContract.User.TABLE_NAME, columns,
                "name = ? & password = ?", args, null, null, "nome");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            User carro = fillCarro(c);
            lista.add(carro);
            c.moveToNext();
        }
        c.close();
        db.close();
        return lista;
    }
}