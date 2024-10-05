package com.example.simba;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simba.adapters.CategoryAdapter;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private CategoryRepository categoryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryRepository = new CategoryRepository(this);
        categoryRepository.open();

        fetchCategoriesFromApi();
    }

    private void fetchCategoriesFromApi() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Category>> call = apiService.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();

                    // Очищаємо попередні дані з бази і зберігаємо нові категорії
                    categoryRepository.open();
                    for (Category category : categories) {
                        categoryRepository.addCategory(category);
                    }

                    displayCategories();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Якщо не вдалося отримати дані через API, відобразити локальні категорії
                displayCategories();
            }
        });
    }

    private void displayCategories() {
        List<Category> categories = categoryRepository.getAllCategories();
        adapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryRepository.close();
    }

}

