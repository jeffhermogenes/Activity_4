package com.example.activity_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef:
    EditText editID, editName, editSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbRef = FirebaseDatabase.getInstance().getReference("students");


    }
    public void insertRecord (){
        FirebaseDatabase.getInstance().getReference()

    }


    public void showData () {

    }

    public void updateRecord () {

    }

    public void deleteRecord () {

    }

}