package com.example.simba;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuHandler {

    private Context context;

    public MenuHandler(Context context) {
        this.context = context;
    }

    public void inflateMenu(Menu menu, MenuInflater inflater) {
        // Підключаємо меню до активності
        inflater.inflate(R.menu.main_menu, menu);
    }

    public boolean handleMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_main_activity) {
            // Перехід на MainActivity
            Intent intentMain = new Intent(context, MainActivity.class);
            context.startActivity(intentMain);
            return true;
        } else if (id == R.id.menu_category_activity) {
            // Перехід на CategoryActivity
            Intent intentCategory = new Intent(context, CategoryActivity.class);
            context.startActivity(intentCategory);
            return true;
        }
        return false;
    }
}

