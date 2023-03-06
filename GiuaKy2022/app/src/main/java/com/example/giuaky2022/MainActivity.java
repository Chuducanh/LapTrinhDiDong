package com.example.giuaky2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.giuaky2022.R;
import com.example.giuaky2022.database.AppDatabase;
import com.example.giuaky2022.database.RecordDao;
import com.example.giuaky2022.databinding.ActivityMainBinding;
import com.example.giuaky2022.model.Record;
import com.example.giuaky2022.viewmodel.RecordAdapter;
import com.example.giuaky2022.viewmodel.RecordApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private RecordAdapter recordAdapter;
    private RecyclerView rvRecords;
    private ArrayList<Record> recordArrayList;
    private RecordApiService apiService;
    private AppDatabase appDatabase;
    private RecordDao recordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = mainBinding.getRoot();
        setContentView(viewRoot);

        rvRecords = findViewById(R.id.rv_full_data);
        recordArrayList = new ArrayList<>();
        recordAdapter = new RecordAdapter(getApplicationContext(), recordArrayList);
        rvRecords.setAdapter(recordAdapter);
        rvRecords.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        recordDao = appDatabase.recordDao();

//        loadDataFromApi();
        loadDataFromDatabase();
        System.err.println("check");

    }

    private void loadDataFromDatabase() {
        recordArrayList = (ArrayList<Record>) recordDao.getAll();
        System.err.println("load success " + recordArrayList.size());
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        recordAdapter = new RecordAdapter(getApplicationContext(), recordArrayList);
        rvRecords.setAdapter(recordAdapter);
        recordAdapter.notifyDataSetChanged();
    }

    private void loadDataFromApi() {
        apiService = new RecordApiService();
        apiService.getRecords().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Record>>() {
                    @Override
                    public void onSuccess(@NonNull List<Record> records) {
                        Log.d("DEBUG", "SUCCESS");
//                        for (Record record : records)
//                            recordDao.insert(record);
                        recordArrayList.addAll(records);
                        recordAdapter.notifyDataSetChanged();
                        System.err.println(recordArrayList.size());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG", "FAIL: " + e.getMessage());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_top_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRecord(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRecord(newText);
                return true;
            }
        });
        return true;
    }

    private void searchRecord(String query) {
        ArrayList<Record> records = new ArrayList<>();
        for (Record record : recordArrayList) {
            if (record.getTitle().toLowerCase().contains(query))
                records.add(record);
        }
        recordAdapter = new RecordAdapter(getApplicationContext(), records);
        mainBinding.rvFullData.setAdapter(recordAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataFromDatabase();
    }
}