package com.cst338.lootcrate.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.database.entities.Swipe;
import com.cst338.lootcrate.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={User.class, Game.class, Swipe.class}, version=5, exportSchema = false)
public abstract class LootCrateDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "LootCrateDatabase";
    public static final String USER_TABLE = "usertable";
    public static final String GAME_TABLE = "gametable";
    public static final String SWIPE_TABLE = "swipetable";
    private static volatile LootCrateDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LootCrateDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LootCrateDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    LootCrateDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultUserValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }

    private static final RoomDatabase.Callback addDefaultUserValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin2", "admin2");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
            });
        }
    };

    public abstract UserDAO userDAO();

    public abstract GameDAO gameDAO();

    public abstract SwipeDAO swipeDAO();

}
