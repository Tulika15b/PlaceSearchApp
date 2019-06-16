package com.tulikab.placesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import com.tulikab.placesearcher.utils.PlaceSearcherConstants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIRequestIntentService extends JobIntentService {


    ResultReceiver resultReceiver;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        if(intent != null && intent.hasExtra(PlaceSearcherConstants.RESULT_RECEIVER_KEY)){
            resultReceiver = intent.getParcelableExtra(PlaceSearcherConstants.RESULT_RECEIVER_KEY);
        }

        if(intent != null && intent.hasExtra(PlaceSearcherConstants.URL_KEY)){
            processRequest(intent.getStringExtra(PlaceSearcherConstants.URL_KEY));
        }

    }

    private void processRequest(String requestUrl){

        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod(PlaceSearcherConstants.HTTP_GET);
            httpURLConnection.setRequestProperty(PlaceSearcherConstants.HTTP_CONTENT_TYPE_KEY,
                    PlaceSearcherConstants.JSON_CONTENT_TYPE);

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

            Bundle bundle = new Bundle();
            bundle.putString(PlaceSearcherConstants.RESPONSE_KEY, sb.toString());
            resultReceiver.send(responseCode, bundle);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
