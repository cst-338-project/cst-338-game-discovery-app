package com.cst338.lootcrate;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.lootcrate.databinding.ActivityLikedGamesBinding;
import com.cst338.lootcrate.viewHolders.GameRowModel;

import java.util.ArrayList;

public class LikedGames extends AppCompatActivity {
    ActivityLikedGamesBinding binding;
    ArrayList<GameRowModel> gameModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikedGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void getGameModels() {

    }
}