package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button add, viewAll;
    EditText name, age;
    Switch  active;
    ListView studentsList;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.button);
        viewAll = findViewById(R.id.button2);
        name= findViewById(R.id.name);
        age= findViewById(R.id.editTextNumber);
        studentsList= findViewById(R.id.listView);
        
    }
}