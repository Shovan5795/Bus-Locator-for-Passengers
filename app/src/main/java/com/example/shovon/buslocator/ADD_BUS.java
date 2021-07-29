package com.example.shovon.buslocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class ADD_BUS extends AppCompatActivity {

    EditText busname,busno,busroute,password;
    Button submit;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__bus);
        busname=(EditText)findViewById(R.id.name);
        busno=(EditText)findViewById(R.id.busno);
        busroute=(EditText)findViewById(R.id.route);
        password=(EditText)findViewById(R.id.pass);

        firebase=new Firebase(Config.FIREBASE_URL);


        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase f2=firebase.child("Bus").push();
                f2.child("busname").setValue(busname.getText().toString());
                f2.child("busno").setValue(busno.getText().toString());
                f2.child("busroute").setValue(busroute.getText().toString());
                f2.child("buspass").setValue(password.getText().toString());
                f2.child("lat").setValue("0");
                f2.child("long").setValue("0");
                Toast.makeText(ADD_BUS.this,"BUS ADDED",Toast.LENGTH_SHORT).show();
            }
        });
    }
}