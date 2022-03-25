package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditorLoginActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_login);

        editText=findViewById(R.id.editorPass_ID);
        button=findViewById(R.id.editorSubmit_ID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=editText.getText().toString();

                sentData(password);
            }
        });
    }

    private void sentData(String password) {
        if (password.equals("123")){
            Intent intent=new Intent(EditorLoginActivity.this,EditorHomeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(EditorLoginActivity.this, "Error password", Toast.LENGTH_SHORT).show();
        }
    }
}