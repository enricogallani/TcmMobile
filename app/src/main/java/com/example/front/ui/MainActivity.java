package com.example.front.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.front.R;
import com.example.front.ui.client.ClientActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClient = findViewById(R.id.btnCliente);
        Button btnConsultor = findViewById(R.id.btnConsultor);
        Button btnAtividade = findViewById(R.id.btnAtividade);
        Button btnServicos = findViewById(R.id.btnServicos);
        Button btnBusca = findViewById(R.id.btnBusca);

        btnClient.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ClientActivity.class)));

    }
}