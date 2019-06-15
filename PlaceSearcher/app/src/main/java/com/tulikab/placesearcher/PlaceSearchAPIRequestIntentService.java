package com.tulikab.placesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PlaceSearchAPIRequestIntentService extends JobIntentService {


    ResultReceiver resultReceiver;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        if(intent != null && intent.hasExtra("RESULT_RECEIVER")){
            resultReceiver = intent.getParcelableExtra("RESULT_RECEIVER");
        }

        if(intent != null && intent.hasExtra("URL")){
            processRequest(intent.getStringExtra("URL"));
        }

    }

    private void processRequest(String requestUrl){

        InputStream inputStream = null;
        try {
            Log.d("PS", "REQUEST_URL ::" + requestUrl);
            URL url = new URL(requestUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            }else{
                inputStream = new BufferedInputStream(httpURLConnection.getErrorStream());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();

            Log.d("PS", "Code::" + responseCode);

            Bundle bundle = new Bundle();
            bundle.putString("RESPONSE", sb.toString());
            resultReceiver.send(responseCode, bundle);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
