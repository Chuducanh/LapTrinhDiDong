package com.example.giuaky2021de1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.giuaky2021de1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private AppDatabase appDatabase;
    private DetailDao detailDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = mainBinding.getRoot();
        setContentView(viewRoot);

        mainBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainBinding.inputEditText.getText().toString().equals("") || mainBinding.actionEditText.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter inputs before clicking submit button", Toast.LENGTH_SHORT).show();
                } else {
                    String input = mainBinding.inputEditText.getText().toString();
                    String action = mainBinding.actionEditText.getText().toString();
                    String output = "";
                    switch (action) {
                        case "count-letter-digit":
                            output = countLetterDigit(input);
                            break;
                        case "remove-even":
                            output = removeEven(input);
                            break;
                        default:
                            break;
                    }
                    if (!output.equals("")) {
                        Detail newDetail = new Detail(input, action, output);
                        appDatabase = AppDatabase.getInstance(getApplicationContext());
                        detailDao = appDatabase.detailDao();
                        detailDao.insert(newDetail);
                        mainBinding.outputTextViewMainScreen.setText(output);
                        Toast.makeText(MainActivity.this, "Add new record successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mainBinding.historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });
    }

    private String removeEven(String input) {
        try {
            List<String> result = new ArrayList<>();
            String[] digits = input.split(",");
            for (int i = 0; i < digits.length; i++) {
                if (Integer.parseInt(digits[i]) % 2 != 0)
                    result.add(digits[i]);
            }
            String output = Arrays.toString(result.toArray());
            return output.substring(1, output.length() - 1);
        } catch (Exception ignored) {
            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    private String countLetterDigit(String input) {
        int letters = 0;
        int digits = 0;
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'))
                letters++;
            if ((input.charAt(i) >= '0' && input.charAt(i) <= '9'))
                digits++;
        }
        return "LETTERS: " + letters + ", DIGITS: " + digits;
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
//                        String fullName = data.getStringExtra("fullName");
//                        String phoneNumber = data.getStringExtra("phoneNumber");
//                        String email = data.getStringExtra("email");
//
//                        Contact newContact = new Contact(fullName, phoneNumber, email);
//
//                        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
//                        appDatabase.contactDao().insert(newContact);
                        System.err.println("add new contact successfully");
                        Toast.makeText(MainActivity.this, "Add new record successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });

}