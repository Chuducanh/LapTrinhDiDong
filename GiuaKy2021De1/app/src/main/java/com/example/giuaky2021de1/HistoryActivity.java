package com.example.giuaky2021de1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.giuaky2021de1.databinding.ActivityHistoryBinding;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding historyBinding;
    private AppDatabase appDatabase;
    private DetailDao detailDao;
    private ArrayList<Detail> detailArrayList;
    private DetailsAdapter detailsAdapter;
    private RecyclerView rvDetailsHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("History");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        rvDetailsHistory = findViewById(R.id.rv_history);
        rvDetailsHistory.setLayoutManager(new LinearLayoutManager(this));
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        detailDao = appDatabase.detailDao();
        detailArrayList = new ArrayList<>(detailDao.getAll());
        detailsAdapter = new DetailsAdapter(getApplicationContext(), detailArrayList);
        rvDetailsHistory.setAdapter(detailsAdapter);
        detailsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history_screen, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                detailDao = appDatabase.detailDao();
                detailDao.deleteAllDetails();
                onResume();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}