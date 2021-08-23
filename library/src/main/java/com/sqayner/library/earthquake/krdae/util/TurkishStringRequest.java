package com.sqayner.library.earthquake.krdae.util;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.nio.charset.Charset;

public class TurkishStringRequest extends com.android.volley.toolbox.StringRequest {


    public TurkishStringRequest(int method, String url, Response.Listener<String> listener, @androidx.annotation.Nullable @org.jetbrains.annotations.Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String utf8String = new String(response.data, Charset.forName("windows-1254"));
        return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
    }
}