package com.exanple.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
   FirebaseDatabase database;
   DatabaseReference ref;
   EditText name,phone,email;
   Button send;
   int maxId=0;
   Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        send=findViewById(R.id.send);
        Member member = new Member();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("user");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if (snapshot.exists()){
                     maxId=(int)snapshot.getChildrenCount();
                 }else {

                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setName(name.getText().toString());
                member.setEmail(email.getText().toString());
                member.setPhone(phone.getText().toString());
                ref.child(String.valueOf(maxId+1)).setValue(member);
            }
        });

    }
}