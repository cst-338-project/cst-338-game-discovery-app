package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.room.RoomSQLiteQuery;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.User;
import com.cst338.lootcrate.databinding.ActivityLoginBinding;

public class MainActivity extends AppCompatActivity {
    private LootCrateRepository repository;
    private ActivityLoginBinding binding;


    public static Intent mainActivityIntentFactory(Context applicationContext, int id) {
        Intent intent = new Intent(applicationContext, MainActivity.class);
        intent.putExtra("userId", id);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        repository = LootCrateRepository.getRepository(getApplication());

        // Force query to see usertable in the App Inspector
        repository.getUserByUserName("admin2").observe(this, user -> {
            if (user != null) {
                Log.d("MainActivity", "Admin user found: " + user.toString());
            } else {
                Log.d("MainActivity", "Admin user not found");
            }
        });




    }
}