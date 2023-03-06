package com.example.giuaky2022;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.giuaky2022.R;
import com.example.giuaky2022.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("History");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        detailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        View viewRoot = detailBinding.getRoot();
        setContentView(viewRoot);

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            String title = receivedIntent.getStringExtra("title");
            String desc = receivedIntent.getStringExtra("desc");
            String timestamp = receivedIntent.getStringExtra("timestamp");
            String lat = receivedIntent.getStringExtra("lat");
            String lng = receivedIntent.getStringExtra("lng");
            String addr = receivedIntent.getStringExtra("addr");
            String e = receivedIntent.getStringExtra("e");
            String zip = receivedIntent.getStringExtra("zip");

            detailBinding.tvTitleDetailScreen.setText(title);
            detailBinding.tvDescDetailScreen.setText(desc);
            detailBinding.tvTimestampDetailScreen.setText(timestamp);
            detailBinding.tvLatDetailScreen.setText(lat);
            detailBinding.tvLngDetailScreen.setText(lng);
            detailBinding.tvAddrDetailScreen.setText(addr);
            detailBinding.tvEDetailScreen.setText(e);
            detailBinding.tvZipDetailScreen.setText(zip);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}