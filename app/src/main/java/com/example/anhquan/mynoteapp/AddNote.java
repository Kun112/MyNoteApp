package com.example.anhquan.mynoteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    Note note;
    EditText title;
    EditText content;
    Button add;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notelayout);
        init();
        event();

    }

    private void event() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase db = new MyDatabase(getApplicationContext());
                String tieude = title.getText().toString();
                String noidung = content.getText().toString();
                if(tieude==""||noidung=="")
                {
                    Toast.makeText(getApplicationContext(), "Input đi chứ babe???", Toast.LENGTH_SHORT).show();
                    return;
                }
                note = new Note(tieude, noidung);
                db.addNote(note);

            }
        });
    }

    private void init() {
        title = (EditText)this.findViewById(R.id.tieude);
        content = (EditText)this.findViewById(R.id.content);
        add = (Button)this.findViewById(R.id.addbtn);
        back = (Button)this.findViewById(R.id.backbtn);
        Intent myintent = this.getIntent();
    }
}
