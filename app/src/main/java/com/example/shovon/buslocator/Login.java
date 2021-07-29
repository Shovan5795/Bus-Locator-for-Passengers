package com.example.shovon.buslocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class Login extends AppCompatActivity {

    EditText un,pass,busno;
    Button login;
    Firebase firebase;
    String name,password,no,id;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_login);
        un=(EditText)findViewById(R.id.un);
        pass=(EditText)findViewById(R.id.pass);
        busno=(EditText)findViewById(R.id.busno);

        login=(Button) findViewById(R.id.login);

        firebase=new Firebase(Config.FIREBASE_URL);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.child("Bus").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String,Object> newPost= (Map<String,Object>) dataSnapshot.getValue();
                        name = String.valueOf(newPost.get("busname"));
                        password = String.valueOf(newPost.get("buspass"));
                        no = String.valueOf(newPost.get("busno"));

                        if(name.equalsIgnoreCase(un.getText().toString()) && password.equalsIgnoreCase(pass.getText().toString()) && busno.getText().toString().equalsIgnoreCase(no))
                        {
                            id=dataSnapshot.getKey();
                            Intent intent = new Intent(Login.this,Update_location.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                            flag=1;
                        }
                    }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(flag!=1)
                        {
                            Toast.makeText(Login.this,"No such username or password",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });




    }
}
