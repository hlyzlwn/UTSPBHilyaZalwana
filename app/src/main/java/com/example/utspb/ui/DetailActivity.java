package com.example.utspb.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utspb.R;
import com.example.utspb.data.response.ItemsItem;
import com.example.utspb.data.retrofit.ApiConfig;
import com.example.utspb.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<ItemsItem> userCall = apiService.getItem(username);

            ImageView imageView = findViewById(R.id.imageview);
            TextView textView = findViewById(R.id.username);
            TextView textView2 = findViewById(R.id.name);
            TextView textView3 = findViewById(R.id.bio);

            showLoading(true);
            userCall.enqueue(new Callback<ItemsItem>() {
                @Override
                public void onResponse(Call<ItemsItem> call, Response<ItemsItem> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        ItemsItem user = response.body();
                        if (user != null){
                            String usernames = "Username: " + user.getUsername();
                            String name = "Name: " + user.getName();
                            String bio = "Bio: " + user.getBio();
                            String gambar = user.getAvatarUrl();

                            textView.setText(name);
                            textView2.setText(usernames);
                            textView3.setText(bio);
                            Picasso.get().load(gambar).into(imageView);
                        }else {
                            Toast.makeText(DetailActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ItemsItem> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}