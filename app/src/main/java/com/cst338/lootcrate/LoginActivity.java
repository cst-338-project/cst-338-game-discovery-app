package com.cst338.lootcrate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.User;
import com.cst338.lootcrate.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LootCrateRepository repository;
    String username = "";
    String password = "";
    int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertUser();
                Intent intent = mainActivityIntentFactory(getApplicationContext(), 0);
                startActivity(intent);
            }
        });

    }

    private void insertUser() {
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a username and password.", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(password, username);
        repository.insertUser(user);
    }

    private void getInformationFromDisplay() {
        username = binding.usernameEditText.getText().toString();
        password = binding.passwordEditText.getText().toString();
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}