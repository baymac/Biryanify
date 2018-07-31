package com.biryanify.parichay.biryanify;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class FragmentActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
        }

        Intent intent = getIntent();
        String intentStringExtra = intent.getStringExtra("extra");

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(intentStringExtra.equals("no orders")) {
            NoOrderFragment noOrderFragment = new NoOrderFragment();
            fragmentTransaction.add(R.id.fragment_container, noOrderFragment, null);
            fragmentTransaction.commit();
        }
        else if(intentStringExtra.equals("expand order")) {
            String name = intent.getStringExtra("name");
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            ViewOrderFragment viewOrderFragment = new ViewOrderFragment();
            fragmentTransaction.add(R.id.fragment_container, viewOrderFragment, null);
            fragmentTransaction.commit();
        }
    }
}
