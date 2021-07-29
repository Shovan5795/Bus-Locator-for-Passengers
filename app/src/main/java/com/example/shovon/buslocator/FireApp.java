package com.example.shovon.buslocator;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.firebase.client.Firebase;

/**
 * Created by HP on 02/12/2016.
 */

public class FireApp extends Application {

   @Override
    public void onCreate(){
       super.onCreate();
       Firebase.setAndroidContext(this);
   }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
