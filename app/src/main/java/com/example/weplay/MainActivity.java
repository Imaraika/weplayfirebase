package com.example.weplay;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText mEdtitextLocation;
    Spinner mSpinnerLocation;
    ImageView btnAdd;
    DatabaseReference databaseLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        databaseLocation = FirebaseDatabase.getInstance().getReference("locations");


        mEdtitextLocation = (EditText) findViewById(R.id.editTextLocation);
        mSpinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);

        btnAdd = (ImageView) findViewById(R.id.searchBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addLocation();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        Intent intn = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intn);
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_favorites:
                        Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_nearby:
                        Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void addLocation() {

        String playgrandName = mEdtitextLocation.getText().toString().trim();
        String playGroundLocation = mSpinnerLocation.getSelectedItem().toString();

        if(!TextUtils.isEmpty(playgrandName))
        {
           String id = databaseLocation.push().getKey();
           Location location = new Location(id,playgrandName,playGroundLocation);
           databaseLocation.push().child(id).setValue(location);

            Toast.makeText(this, "the location added", Toast.LENGTH_SHORT).show();


        }
        else{
            Toast.makeText(this, "you should enter the name", Toast.LENGTH_SHORT).show();
        }
    }
}

