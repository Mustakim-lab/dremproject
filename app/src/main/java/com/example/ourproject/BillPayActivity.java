package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BillPayActivity extends AppCompatActivity {
    EditText billerNameEdit,amountEdit,dateEdit,tranjectionEdit,statusEdit;
    Button submitBtn;

    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_pay);

        billerNameEdit=findViewById(R.id.billName_ID);
        amountEdit=findViewById(R.id.bilAomunt_ID);
        dateEdit=findViewById(R.id.billDate_ID);
        tranjectionEdit=findViewById(R.id.billTrans_ID);
        statusEdit=findViewById(R.id.billStatus_ID);


        submitBtn=findViewById(R.id.billSubmit_ID);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String myId=firebaseUser.getUid();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=billerNameEdit.getText().toString().trim();
                String amount=amountEdit.getText().toString().trim();
                String date=dateEdit.getText().toString().trim();
                String transaction=tranjectionEdit.getText().toString().trim();
                String status=statusEdit.getText().toString();

                if (name.isEmpty()){
                    billerNameEdit.setError("Enter name !!");
                    billerNameEdit.requestFocus();
                    return;
                }else if (amount.isEmpty()){

                }

                sentData(name,amount,date,transaction,myId);
                /*
                billerNameEdit.setText("");
                amountEdit.setText("");
                dateEdit.setText("");
                tranjectionEdit.setText("");

                 */
            }
        });
    }

    private void sentData(String name, String amount, String date, String transaction, String myId) {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Bill_folder");
        String new_id=reference.push().getKey();

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("amount",amount);
        hashMap.put("date",date);
        hashMap.put("transaction",transaction);
        hashMap.put("id",myId);
        hashMap.put("new_id",new_id);

        reference.child(new_id).setValue(hashMap);
        //reference.child("Bill_folder").push().setValue(hashMap);
        Toast.makeText(BillPayActivity.this, "আপনার বিল প্রেরন করা হয়েছে", Toast.LENGTH_SHORT).show();
    }
}