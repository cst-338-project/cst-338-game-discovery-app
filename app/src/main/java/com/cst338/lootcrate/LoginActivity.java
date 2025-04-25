package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

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

        repository = LootCrateRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getInformationFromDisplay();
//                insertUser();
//                Intent intent = mainActivityIntentFactory(getApplicationContext(), 0);
//                startActivity(intent);
                verifyUser();
            }
        });

    }

    private void verifyUser() {
        String username = binding.usernameEditText.getText().toString();

        if (username.isEmpty()) {
            toastMaker("Username may not be blank.");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                String password = binding.passwordEditText.getText().toString();
                if (password.equals(user.getPassword())) {
//                    startActivity(LandingPageActivity.landingIntentFactory(getApplicationContext(), user.getId()));
                    startActivity(GameDetailsActivity.gameDetailsIntentFactory(getApplicationContext()));
                } else {
                    toastMaker("Invalid password");
                    binding.passwordEditText.setSelection(0);
                }
            } else {
                toastMaker(String.format("%s is not a valid username.", username));
                binding.usernameEditText.setSelection(0);
            }
        });
    }

    /**
     * Should be moved to sign up instead.
     *
    private void insertUser() {
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a username and password.", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(password, username);
        repository.insertUser(user);
    }
     */

    /**
     * Deprecated
    private void getInformationFromDisplay() {
        username = binding.usernameEditText.getText().toString();
        password = binding.passwordEditText.getText().toString();
    }
     */

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}