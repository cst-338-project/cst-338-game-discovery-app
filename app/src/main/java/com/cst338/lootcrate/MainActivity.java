package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.lootcrate.database.LootCrateRepository;

public class MainActivity extends AppCompatActivity {
    private LootCrateRepository repository;
    private static final String MAIN_ACTIVITY_USER_ID = "com.cst338.lootcrate.MAIN_ACTIVITY_USER_ID";
    int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUser();
        if(loggedInUserId == -1){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }

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
    private void loginUser() {
        //TODO: create login method
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
    }
    static Intent mainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}