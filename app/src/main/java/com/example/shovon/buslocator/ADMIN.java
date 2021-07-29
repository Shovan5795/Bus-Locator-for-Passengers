package com.example.shovon.buslocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ADMIN extends AppCompatActivity {
    EditText un,pass;
    Button login;
    Firebase admin;
    String name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        un=(EditText)findViewById(R.id.un);
        pass=(EditText)findViewById(R.id.pass);
        login=(Button) findViewById(R.id.login);

        Firebase.setAndroidContext(this);
        admin=new Firebase(Config.FIREBASE_URL);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                admin.child("admin_name").addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                    @Override
                    public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                        name=(String) dataSnapshot.getValue();

                        admin.child("admin_pass").addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                password=(String) dataSnapshot.getValue();
                                if(un.getText().toString().equalsIgnoreCase(name) && pass.getText().toString().equalsIgnoreCase(password))
                                {
                                    Toast.makeText(ADMIN.this,"Successful Login",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(ADMIN.this,ADD_BUS.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(ADMIN.this,"Name or password not matched",Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });



            }
        });
    }
}
