package com.async;

import android.os.AsyncTask;
import android.util.Log;

import com.utils.storage.remote.RemoteStorage;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Charan-celigo on 3/20/2017.
 */
public class AsyncInternet extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            String serverURl = params[0];
            HttpURLConnection httpConn = (HttpURLConnection) (new RemoteStorage().createConnection(serverURl));

            String inputExpense = params[1];

            httpConn.setRequestProperty("Content-Length",
                    String.valueOf(inputExpense));
            httpConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            Log.d("AsyncInternet.java", inputExpense);
            out.write(inputExpense.getBytes("UTF-8"));
            out.close();

            InputStream inputStream = httpConn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            Log.d(this.getClass().getName(), "message from server: " + buf.toString());
        } catch (Exception e) {
            Log.d(this.getClass().getName(), e.getMessage());
        }
        return null;
    }
}
