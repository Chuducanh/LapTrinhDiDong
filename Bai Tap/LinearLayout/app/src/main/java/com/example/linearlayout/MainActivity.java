package com.example.linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private EditText number_a, number_b;
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private ArrayList<String> listItem;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();

        listItem = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listItem);
        listView.setAdapter(arrayAdapter);
        btnAdd.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }
    public void Mapping() {
        listView = (ListView) findViewById(R.id.lv);
        number_a  = (EditText) findViewById(R.id.numberA);
        number_b  = (EditText) findViewById(R.id.numberB);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
    }
    public void ShowErr(String txtError) {
        Toast.makeText(this, txtError, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        if(number_a.getText().toString().equals("") || number_b.getText().toString().equals("")) {
            ShowErr("Types input");
        }
        else {
            int a = Integer.parseInt(number_a.getText().toString());
            int b = Integer.parseInt(number_b.getText().toString());
            int result = 0;
            if(view.getId() == R.id.btnAdd) {
                result = a + b;
                listItem.add(a + " + " + b + " = " + result);
                arrayAdapter.notifyDataSetChanged();
            }
            else if(view.getId() == R.id.btnSub) {
                result = a - b;
                listItem.add(a + " - " + b + " = " + result);
                arrayAdapter.notifyDataSetChanged();
            }
            else if(view.getId() == R.id.btnMul) {
                result = a * b;
                listItem.add(a + " * " + b + " = " + result);
                arrayAdapter.notifyDataSetChanged();
            }
            else {
                if(b == 0)
                    ShowErr("b can not be equal to 0");
                else {
                    result = a / b;
                    listItem.add(a + " / " + b + " = " + result);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}