package com.cst338.lootcrate.viewHolders;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cst338.lootcrate.database.LootCrateRepository;

import java.util.List;

public class GamesViewModel {
    private final LootCrateRepository repository;

    public GamesViewModel(Application application) {
        //super(application);
        repository = LootCrateRepository.getRepository(application);
    }

}
