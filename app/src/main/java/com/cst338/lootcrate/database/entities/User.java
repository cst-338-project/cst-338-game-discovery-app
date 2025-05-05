package com.cst338.lootcrate.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.lootcrate.database.LootCrateDatabase;

import java.util.Objects;

@Entity(tableName = LootCrateDatabase.USER_TABLE)
public class User {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;

    private int pageNum;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
        this.isAdmin = false;
        pageNum = 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && isAdmin == user.isAdmin && pageNum == user.pageNum && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, isAdmin, pageNum);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

