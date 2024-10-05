package com.example.simba;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public CategoryRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Відкрити базу даних для запису
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    // Закрити базу даних
    public void close() {
        dbHelper.close();
    }

    // Додавання категорії
    public void addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        values.put("imagePath", category.getImagePath());

        database.insert("categories", null, values);
    }

    // Отримання всіх категорій
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Cursor cursor = database.query("categories", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String imagePath = cursor.getString(cursor.getColumnIndex("imagePath"));

                Category category = new Category();
                category.setId(id);
                category.setName(name);
                category.setImagePath(imagePath);

                categories.add(category);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return categories;
    }
}

