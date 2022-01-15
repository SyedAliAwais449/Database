package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add, viewAll, deleteButton, updateButton;
    EditText name, age;
    Switch  active;
    ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.button);
        viewAll = findViewById(R.id.button2);
        name= findViewById(R.id.name);
        age= findViewById(R.id.editTextNumber);
        studentsList= findViewById(R.id.listView);
        active= findViewById(R.id.switch1);
        deleteButton= findViewById(R.id.button3);
        add.setOnClickListener(new View.OnClickListener() {
            Student student1;
            @Override
            public void onClick(View v) {
                try {

                    student1 = new Student(name.getText().toString(), Integer.parseInt(age.getText().toString()), active.isChecked());
                    Toast.makeText(MainActivity.this, student1.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                dbHelper.addStudent(student1);
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                List<String> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list);
                studentsList.setAdapter(arrayAdapter);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper= new DBHelper(MainActivity.this);
                if(dbHelper.delete(name.getText().toString())){
                    Toast.makeText(MainActivity.this,name.getText().toString()+" has been deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Record with this name doesnot exist",Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbhelper= new DBHelper(MainActivity.this);
//                dbhelper.update();
            }
        });
    }
}