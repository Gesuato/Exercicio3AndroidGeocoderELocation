package com.cryptog.exercicio3androidgeocoderelocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextView textViewAdress;
    private Button btnSearch;
    private EditText editTextSearch;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        textViewAdress = findViewById(R.id.textViewAdress);
        editTextSearch = findViewById(R.id.editTextSearch);
        btnSearch = findViewById(R.id.btnSearch);

        final Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String adress = editTextSearch.getText().toString();
                try {
                    List<Address> addressList = geocoder.getFromLocationName(adress,1);

                    if(addressList.size() > 0){
                        textViewAdress.setText(addressList.get(0).getAddressLine(0));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
