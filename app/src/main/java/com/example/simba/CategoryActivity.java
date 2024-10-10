package com.example.simba;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;


public class CategoryActivity extends AppCompatActivity {
    private MenuHandler menuHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String url = "http://malyska123.somee.com/images/noimage.jpg";
        ImageView imgTest = findViewById(R.id.imgTest);
        Glide.with(this).load(url).into(imgTest);
        menuHandler = new MenuHandler(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Підключаємо меню через MenuHandler
        menuHandler.inflateMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обробляємо натискання пунктів меню через MenuHandler
        return menuHandler.handleMenuItemClick(item) || super.onOptionsItemSelected(item);
    }
}