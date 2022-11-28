package com.example.activity_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef:
    EditText editID, editName, editSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Memo");
        dbRef = FirebaseDatabase.getInstance().getReference("students");


    }
    public void insertRecord(View v){
        String id = editID.getText().toString();
        String name = editName.getText().toString();
        String section = editSection.getText().toString();
        Student student = new Student(name, section);
        dbRef.child(id).setValue(student);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
    public void showData(View v){
        String id = editID.getText().toString();
        DatabaseReference dbDoc = dbRef.child(id);
        ValueEventListener veListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                Student student = ds.getValue(Student.class);
                editName.setText(student.getName());
                editSection.setText(student.getSection());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbDoc.addValueEventListener(veListener);
    }

    public void updateRecord(View v){
        String id = editID.getText().toString();
        String name = editName.getText().toString();
        String section = editSection.getText().toString();
        HashMap<String, Object> studMap= new HashMap <>();
        studMap.put("name",name);
        studMap.put("section",section);
        dbRef.child(id).updateChildren(studMap).addOnSuccessListener(suc -> {
            Toast.makeText(this, "record updated...", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(err -> {
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
        });;
    }

    public void deleteRecord(View v){
        String id = editID.getText().toString();
        dbRef.child(id).removeValue().addOnSuccessListener(suc -> {
            Toast.makeText(this, "record deleted...", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(err -> {
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }