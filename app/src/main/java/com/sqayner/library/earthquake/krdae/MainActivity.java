package com.sqayner.library.earthquake.krdae;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sqayner.library.earthquake.krdae.model.KrdaeEarthquakeModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private KRDAE krdae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        krdae = new KRDAE(this, new KRDAE.OnEarthquakeLoadListener() {
            @Override
            public void onLoad(List<KrdaeEarthquakeModel> earthquakes) {
                for (KrdaeEarthquakeModel earthquake : earthquakes) {
                   /*SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ssZ");
                    df.setTimeZone(TimeZone.getDefault());
                    String formattedDate = df.format(earthquake.getDatetime());
                    System.out.println(formattedDate);*/
                    System.out.println(earthquake.toString());
                }
            }
        });
        krdae.load();
    }

    public void test(View view) {

    }
}