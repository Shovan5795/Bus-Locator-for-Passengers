package com.example.shovon.buslocator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by shovonswajan on 12/31/16.
 */

public class BusArraAdapter extends ArrayAdapter<String> {

    public BusArraAdapter(Context context, ArrayList<String> strings)
    {
        super(context, 0, strings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String bus = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);
        }
        final TextView bname = (TextView) convertView.findViewById(R.id.bname);
        final TextView broute = (TextView) convertView.findViewById(R.id.broute);
        final TextView bno = (TextView)  convertView.findViewById(R.id.bno);

        final Firebase firebase=new Firebase(Config.FIREBASE_URL);

        firebase.child("Bus").child(bus).child("busname").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String z=(String)dataSnapshot.getValue();
                z="Bus Name: "+z;
                bname.setText(z);

                firebase.child("Bus").child(bus).child("busroute").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String y=(String)dataSnapshot.getValue();
                        y="Bus Route: "+y;
                        broute.setText(y);

                        firebase.child("Bus").child(bus).child("busno").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String x=(String)dataSnapshot.getValue();
                                x="Bus No.: "+x;
                                bno.setText(x);
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

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return convertView;
    }
}