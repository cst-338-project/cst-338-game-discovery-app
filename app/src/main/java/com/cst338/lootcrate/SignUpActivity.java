package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.databinding.ActivityLoginBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LootCrateRepository repository;




    public static Intent signUpIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, SignUpActivity.class);
    }
    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
