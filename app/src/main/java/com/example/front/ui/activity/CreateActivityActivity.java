package com.example.front.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.front.R;
import com.example.front.db.entity.Activity;
import com.example.front.db.entity.User;
import com.example.front.db.helper.ActivityHelper;
import com.example.front.db.helper.UserHelper;
import com.example.front.ui.adapter.ActivitySpinnerAdapter;

import java.util.List;

public class CreateActivityActivity extends AppCompatActivity {

    Integer selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnCadastro = findViewById(R.id.btnCadastro);
        EditText etNome = findViewById(R.id.etNome);
        Spinner spUser = findViewById(R.id.user);

        List<User> users = new UserHelper(CreateActivityActivity.this).getAll();
        String[] userArray = new String[users.size() + 1];
        userArray[0] = "Coonsultor:";

        int count = 1;
        for (User user: users) {
            userArray[count] = user.getName();
            count++;
        }

        ArrayAdapter adapter = new ActivitySpinnerAdapter(this, android.R.layout.simple_spinner_item, userArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUser.setAdapter(adapter);

        spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });


        btnCadastro.setOnClickListener(v -> {
            if (etNome.getText().toString().isEmpty()) {
                Toast.makeText(CreateActivityActivity.this, "Nome é obrigatório!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selected == 0) {
                Toast.makeText(CreateActivityActivity.this, "Selecione um consultor!!", Toast.LENGTH_SHORT).show();
                return;
            }

            ActivityHelper activityHelper = new ActivityHelper(CreateActivityActivity.this);

            Activity activity = new Activity();
            activity.setName(etNome.getText().toString());
            activity.setUserId(users.get(selected - 1).getId());

            activityHelper.inserir(activity);

            onBackPressed();
        });
    }
}