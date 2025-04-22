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
import com.cst338.lootcrate.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {


    private ActivitySignupBinding binding;
    private LootCrateRepository repository;

    String username = "";
    String password = "";
    String confirmPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = LootCrateRepository.getRepository(getApplication());


        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                completeSignUp();



            }
        });




    }

    private void completeSignUp() {

        String username = binding.usernameEditText.getText().toString();
        String password = binding.passwordEditText.getText().toString();
        String confirmPassword = binding.confirmPasswordEditText.getText().toString();

        if (username.isEmpty()) {
            toastMaker("Username may not be blank.");
            return;
        }
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            toastMaker("Password fields may not be blank.");
            return;
        }




        if (!repository.alrHasUser(username)) {
            if (password.equals(confirmPassword)) {
                User newUser = new User(password, username);
                repository.insertUser(newUser);
                toastMaker("Account created successfully.");

                Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
                startActivity(intent);
            } else {
                toastMaker("Passwords do not match.");
                binding.passwordEditText.setSelection(0);
                binding.passwordEditText.setText("");
                binding.confirmPasswordEditText.setSelection(0);
                binding.confirmPasswordEditText.setText("");
            }
        } else {
            toastMaker(String.format("%s is already taken.", username));
            binding.usernameEditText.setSelection(0);

        }



    }



    static Intent signUpIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, SignUpActivity.class);
    }
    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
