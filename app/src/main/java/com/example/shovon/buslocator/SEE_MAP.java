package com.example.shovon.buslocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class SEE_MAP extends AppCompatActivity {
   AutoCompleteTextView busname,route;
    Button search;
    Firebase firebase;
    ArrayList<String> bn,br,lst;
    ListView listView;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__map);

        busname=(AutoCompleteTextView)findViewById(R.id.busname);
        route=(AutoCompleteTextView)findViewById(R.id.route);
        firebase=new Firebase(Config.FIREBASE_URL);

        listView=(ListView)findViewById(R.id.list);

        bn=new ArrayList<String>();

        br=new ArrayList<String>();

        lst=new ArrayList<String>();

        search=(Button) findViewById(R.id.search);

        firebase.child("Bus").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object> newPost= (Map<String,Object>) dataSnapshot.getValue();
                String bname= String.valueOf(newPost.get("busname"));
                String broute=String.valueOf(newPost.get("busroute"));
                if(!bn.contains(bname))
                bn.add(bname);
                if(!br.contains(broute))
                br.add(broute);
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

        firebase.child("Bus").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setsearch();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.child("Bus").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String,Object> newPost= (Map<String,Object>) dataSnapshot.getValue();
                        String bnam = String.valueOf(newPost.get("busname"));
                        String broute = String.valueOf(newPost.get("busroute"));

                        if(bnam.equalsIgnoreCase(busname.getText().toString()) && broute.equalsIgnoreCase(route.getText().toString()))
                        {
                            id=dataSnapshot.getKey();
                            lst.add(id);
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
                       setlist();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
    }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String bid=(String) parent.getItemAtPosition(position);

                Intent intent=new Intent(SEE_MAP.this,BUS_MAP.class);
                intent.putExtra("id",bid);
                startActivity(intent);
            }
        });
    }

    public void setsearch()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,bn);
        busname.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,br);
        route.setAdapter(adapter1);

    }

    public void setlist()
    {
        BusArraAdapter busArraAdapter=new BusArraAdapter(SEE_MAP.this,lst);
        listView.setAdapter(busArraAdapter);
    }

}
