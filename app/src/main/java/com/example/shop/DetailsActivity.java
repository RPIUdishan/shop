package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shop.model.ProductDetails;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().hide();

        TextView tv = findViewById(R.id.details_text);
        ImageView image = findViewById(R.id.product_details_image);
        TextView priceView = findViewById(R.id.price_text_view);
        TextView descView = findViewById(R.id.description);
        ImageButton imageButton = findViewById(R.id.back_button);


        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String imageUrl = i.getStringExtra("images");
        String price = i.getStringExtra("price");
        String description = i.getStringExtra("description");

        Glide.with(getApplicationContext()).load(imageUrl).into(image);
        tv.setText(title);
        priceView.setText(price);
        descView.setText(description);

        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}