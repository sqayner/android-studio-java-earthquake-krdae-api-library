package com.sqayner.library.earthquake.krdae;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.sqayner.library.earthquake.krdae.model.KrdaeEarthquakeModel;
import com.sqayner.library.earthquake.krdae.model.LatLongModel;
import com.sqayner.library.earthquake.krdae.model.RevisedModel;
import com.sqayner.library.earthquake.krdae.util.TurkishStringRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class KRDAE {

    private OnEarthquakeLoadListener onEarthquakeLoadListener;
    private Context context;

    public KRDAE(Context context, OnEarthquakeLoadListener onEarthquakeLoadListener) {
        this.context = context;
        this.onEarthquakeLoadListener = onEarthquakeLoadListener;
    }

    public void load() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://www.koeri.boun.edu.tr/scripts/lst" + getRandomNumber(0, 10, 3) + ".asp";

        TurkishStringRequest stringRequest = new TurkishStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<KrdaeEarthquakeModel> earthquakes = new ArrayList<>();
                        List<String> earthquakeStringLines = Arrays.asList(response.split("\n"));
                        for (int i = arrayFirstIndexOf(earthquakeStringLines, "<pre>") + 7; i < arrayFirstIndexOf(earthquakeStringLines, "</pre>") - 1; i++) {
                            String earthquakeString = earthquakeStringLines.get(i);
                            earthquakes.add(new KrdaeEarthquakeModel(earthquakeString.substring(71, 121).trim(),
                                    new LatLongModel(
                                            Double.parseDouble(earthquakeString.substring(21, 28).trim()),
                                            Double.parseDouble(earthquakeString.substring(31, 38).trim())),
                                    getMagnitude(earthquakeString),
                                    Double.parseDouble(earthquakeString.substring(41, 49).trim()),
                                    getDateTime(earthquakeString),
                                    getRevised(earthquakeString)
                            ));
                        }
                        onEarthquakeLoadListener.onLoad(earthquakes);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        queue.add(stringRequest);
    }

    private RevisedModel getRevised(String data) {
        String revised = data.substring(121).trim();

        if (revised.equals("İlksel")) {
            return null;
        } else {
            Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            date.set(Calendar.YEAR, Integer.parseInt(data.substring(0, 4)));
            date.set(Calendar.MONTH, Integer.parseInt(data.substring(5, 7)) - 1);
            date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data.substring(8, 10)));
            date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data.substring(11, 13)));
            date.set(Calendar.MINUTE, Integer.parseInt(data.substring(14, 16)));
            date.set(Calendar.SECOND, Integer.parseInt(data.substring(17, 19)));
            date.add(Calendar.HOUR, -3);
            return new RevisedModel(Integer.parseInt(revised.substring(7, 8)), date.getTime());
        }
    }

    private Date getDateTime(String data) {
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        date.set(Calendar.YEAR, Integer.parseInt(data.substring(0, 4)));
        date.set(Calendar.MONTH, Integer.parseInt(data.substring(5, 7)) - 1);
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data.substring(8, 10)));
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data.substring(11, 13)));
        date.set(Calendar.MINUTE, Integer.parseInt(data.substring(14, 16)));
        date.set(Calendar.SECOND, Integer.parseInt(data.substring(17, 19)));
        date.add(Calendar.HOUR, -3);
        return date.getTime();
    }

    private Double getMagnitude(String data) {
        String MD = data.substring(55, 58);
        String ML = data.substring(60, 63);
        String MW = data.substring(65, 68);
        if (MW.equals("-.-")) {
            if (ML.equals("-.-")) {
                if (MD.equals("-.-")) {
                    return 0.0;
                } else {
                    return Double.parseDouble(MD);
                }
            } else {
                return Double.parseDouble(ML);
            }
        } else {
            return Double.parseDouble(MW);
        }
    }

    private int arrayFirstIndexOf(List<String> array, String index) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).contains(index))
                return i;
        }
        return -1;
    }

    private int getRandomNumber(int min, int max, int... without) {
        Random random = new Random();
        int randomNumber;
        boolean withoutNumberContains;
        do {
            withoutNumberContains = false;
            randomNumber = random.nextInt(max - min) + min;
            for (int withoutNumber : without) {
                if (withoutNumber == randomNumber) {
                    withoutNumberContains = true;
                    break;
                }
            }
        } while (withoutNumberContains);
        return randomNumber;
    }

    public interface OnEarthquakeLoadListener {
        void onLoad(List<KrdaeEarthquakeModel> earthquakes);
    }

}
