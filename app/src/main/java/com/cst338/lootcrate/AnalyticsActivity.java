package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.GameAnalytics;
import com.cst338.lootcrate.databinding.ActivityAnalyticsBinding;
import com.cst338.lootcrate.viewHolders.AnalyticsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsActivity extends AppCompatActivity {
    ActivityAnalyticsBinding binding;
    LootCrateRepository repository;
    AnalyticsAdapter adapter;
    List<GameAnalytics> analyticsList = new ArrayList<>();

    public static Intent analyticsIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, AnalyticsActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnalyticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = LootCrateRepository.getRepository(getApplication());

        adapter = new AnalyticsAdapter(this, analyticsList);
        RecyclerView recyclerView = binding.analyticsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        repository.getGameAnalytics().observe(this, data -> {
            analyticsList.clear();
            analyticsList.addAll(data);
            adapter.notifyDataSetChanged();
        });

        binding.analyticsBackButton.setOnClickListener(v -> finish());
    }
}