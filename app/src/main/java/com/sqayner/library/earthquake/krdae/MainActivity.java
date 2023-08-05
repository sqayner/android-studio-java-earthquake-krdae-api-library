package com.sqayner.library.earthquake.krdae;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sqayner.library.earthquake.krdae.model.KrdaeEarthquakeModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private KRDAE krdae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        krdae = new KRDAE(this);
        krdae.setEarthquakeListener(new KRDAE.EarthquakeListener() {
            @Override
            public void onLoaded(ArrayList<KrdaeEarthquakeModel> earthquakes) {
                for (KrdaeEarthquakeModel earthquake : earthquakes) {
                   /*SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ssZ");
                    df.setTimeZone(TimeZone.getDefault());
                    String formattedDate = df.format(earthquake.getDatetime());
                    System.out.println(formattedDate);*/
                    System.out.println(earthquake.toString());
                }
            }

            @Override
            public void onError(Exception e) {
                if (e != null)
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        krdae.load();
    }
}