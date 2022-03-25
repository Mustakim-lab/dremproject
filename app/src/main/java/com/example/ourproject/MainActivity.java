package com.example.ourproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }

    MaterialEditText emailEdit,passwordEdit;
    Button button;
    TextView textView;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEdit=findViewById(R.id.signEmail_ID);
        passwordEdit=findViewById(R.id.signPassword_ID);
        button=findViewById(R.id.signSubmit_ID);
        progressBar=findViewById(R.id.signProgressbar_ID);

        textView=findViewById(R.id.goSignUP_ID);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });

        mAuth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailEdit.getText().toString().trim();
                String password=passwordEdit.getText().toString();

                if (email.isEmpty()){
                    emailEdit.setError("Enter email !!!");
                    emailEdit.requestFocus();
                    return;
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEdit.setError("Enter valid email !!!");
                    emailEdit.requestFocus();
                    return;
                }else if (password.isEmpty()){
                    passwordEdit.setError("Enter password !!!");
                    passwordEdit.requestFocus();
                    return;
                }else if (password.length()<6){
                    passwordEdit.setError("Enter 6 digit password!!!");
                    passwordEdit.requestFocus();
                    return;
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    signInUser(email,password);
                    // emailEdit.setText("");
                    //passwordEdit.setText("");
                }
            }
        });
    }

    private void signInUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}