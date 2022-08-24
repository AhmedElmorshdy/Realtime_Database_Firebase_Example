package com.exanple.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
   FirebaseDatabase database;
   DatabaseReference ref;
   EditText name,phone,email;
   Button send,show;
   int maxId=0;
   Member member;
    RecyclerView recyclerView;

    UserAdapter adapter;

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        send=findViewById(R.id.send);

        recyclerView=findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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

        FirebaseRecyclerOptions<   Member> options =
                new FirebaseRecyclerOptions.Builder<Member>().setQuery(ref,Member.class).build();

        adapter=new UserAdapter(options);
        recyclerView.setAdapter(adapter);

    }
}