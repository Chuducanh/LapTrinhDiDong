package com.example.contactapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.contactapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactsAdapter contactAdapter;

    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                loadDataFromDatabase();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        assert data != null;
                        String fullName = data.getStringExtra("fullName");
                        String phoneNumber = data.getStringExtra("phoneNumber");
                        String email = data.getStringExtra("email");

                        Contact newContact = new Contact(fullName, phoneNumber, email);

                        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                        appDatabase.contactDao().insert(newContact);
                        System.err.println("add new contact successfully");
                        Toast.makeText(MainActivity.this, "Add new contact successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    void loadDataFromDatabase() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        contactDao = appDatabase.contactDao();
        contactList = (ArrayList<Contact>) contactDao.getAll();
        contactAdapter = new ContactsAdapter(getApplicationContext(), contactList);
        binding.rvContacts.setAdapter(contactAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_top_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_contact);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return true;
            }
        });
        return true;
    }

    private void searchContact(String newText) {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        contactDao = appDatabase.contactDao();
        contactList = (ArrayList<Contact>) contactDao.getAll();
        ArrayList<Contact> newList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(newText))
                newList.add(contact);
        }
        contactAdapter = new ContactsAdapter(getApplicationContext(), newList);
        binding.rvContacts.setAdapter(contactAdapter);
    }

}