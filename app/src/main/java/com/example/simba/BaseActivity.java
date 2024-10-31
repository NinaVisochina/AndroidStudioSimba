package com.example.simba;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simba.category.CategoryCreateActivity;
import com.example.simba.category.CategoryEditActivity;
import com.example.simba.utils.CommonUtils;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
        CommonUtils.setContext(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int select = item.getItemId();
        if (select == R.id.menu_main_activity) {
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (select == R.id.menu_category_create_activity) {
            Intent intent = new Intent(BaseActivity.this, CategoryCreateActivity.class);
            startActivity(intent);
            return true;
        } else if (select == R.id.menu_category_edit_activity) {
            Intent intent = new Intent(BaseActivity.this, CategoryEditActivity.class);
            // Передайте необхідні дані для редагування, наприклад ID категорії
            // intent.putExtra("categoryId", someCategoryId);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
