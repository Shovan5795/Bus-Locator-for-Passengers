package com.example.shovon.buslocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Update_location extends AppCompatActivity {
    Button update;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);
        Bundle bundle = getIntent().getExtras();
        id=bundle.getString("id");


        update=(Button)findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Update_location.this,MapsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}