package com.example.front.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.front.R;
import com.example.front.db.entity.Client;
import com.example.front.db.helper.ClientHelper;

public class CreateClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText etName = findViewById(R.id.etName);
        EditText etMail = findViewById(R.id.etMail);
        EditText etPhone = findViewById(R.id.etPhone);
        EditText etPaymentMethod = findViewById(R.id.etPaymentMethod);
        EditText etLocation = findViewById(R.id.etLocation);
        EditText etCnpj = findViewById(R.id.etCnpj);
        Button btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(v -> {
            if (etName.getText().toString().isEmpty()) {
                Toast.makeText(CreateClientActivity.this, "Nome é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etMail.getText().toString().isEmpty()) {
                Toast.makeText(CreateClientActivity.this, "Email é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etCnpj.getText().toString().isEmpty()) {
                Toast.makeText(CreateClientActivity.this, "CNPJ é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etPhone.getText().toString().isEmpty()) {
                Toast.makeText(CreateClientActivity.this, "Telefone é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etPaymentMethod.getText().toString().isEmpty()) {
                Toast.makeText(CreateClientActivity.this, "Tipo de pagamento é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etLocation.getText().toString().isEmpty()) {
                Toast.makeText(CreateClientActivity.this, "Localização é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            Client client = new Client();
            client.setName(etName.getText().toString());
            client.setCnpj(etCnpj.getText().toString());
            client.setMail(etMail.getText().toString());
            client.setPhone(etPhone.getText().toString());
            client.setPaymentMethod(etPaymentMethod.getText().toString());
            client.setLocation(etLocation.getText().toString());

            ClientHelper clientHelper = new ClientHelper(CreateClientActivity.this);
            clientHelper.inserir(client);

            onBackPressed();
        });
    }
}