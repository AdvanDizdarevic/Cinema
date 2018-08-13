package ba.fit.kino.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by adnan_000 on 8.7.2014.
 */
public class HttpManager2 {




    public static String responseFromUrlGet(String url, NameValuePair... params) {
        try {
            List<NameValuePair> listParams = new ArrayList<NameValuePair>();
            for (NameValuePair param : params) {
                listParams.add(param);
            }
            url = url + "?" + URLEncodedUtils.format(listParams, "utf-8");


            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();


            InputStream inputStream = httpEntity.getContent();
            return buildString(inputStream);
        } catch (Exception ex) {
            Log.e("greska getResponseFromUrl", ex.getMessage());
        }

        return null;
    }

    private static String buildString(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder result = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
            inputStream.close();
            return result.toString();
        } catch (Exception ex) {
            Log.e("Buffering Error  ", ex.toString());
            return null;
        }
    }

    public static String responseFromUrlPost(String url, NameValuePair... params) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);


            if (params != null) {
                List<NameValuePair> listParams = new ArrayList<NameValuePair>();
                for (NameValuePair param : params) {
                    listParams.add(param);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(listParams));
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();

                InputStream inputStream = httpEntity.getContent();
                return buildString(inputStream);
            } else {
                httpResponse.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (Exception ex) {
            Log.e("greska getResponseFromUrl", ex.getMessage());
        }

        return null;
    }

}