package com.example.weplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText mEdtitextLocation;
    Spinner mSpinnerLocation;
    Button btnAdd;
    DatabaseReference databaseLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseLocation = FirebaseDatabase.getInstance().getReference("locations");


        mEdtitextLocation = (EditText) findViewById(R.id.editTextLocation);
        mSpinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);

        btnAdd = (Button) findViewById(R.id.buttonAddLocation);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addLocation();


            }
        });
    }

    private void addLocation() {

        String playgrandName = mEdtitextLocation.getText().toString().trim();
        String playGroundLocation = mSpinnerLocation.getSelectedItem().toString();

        if(!TextUtils.isEmpty(playgrandName))
        {
           String id = databaseLocation.push().getKey();
           Location location = new Location(id, playgrandName,playGroundLocation);
           databaseLocation.child(id).setValue(location);

            Toast.makeText(this, "the location added", Toast.LENGTH_SHORT).show();


        }
        else{
            Toast.makeText(this, "you should enter the name", Toast.LENGTH_SHORT).show();
        }
    }
}

