package com.utils.storage.remote;

import com.async.AsyncInternet;
import com.server.RemoteServer;
import com.utils.storage.StorageUtil;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Charan-celigo on 3/19/2017.
 */
public class RemoteStorage implements StorageUtil{
    @Override
    public boolean insertExpense(HashMap<String, String> expDetails) throws Exception{
        String serverURl = RemoteServer.getRemoteServer().getServerUrl();
        String endpoint = RemoteServer.getRemoteServer().getAddExpenseEndPoint();
        serverURl = serverURl + endpoint;
        JSONObject jsonObj = new JSONObject(expDetails);
        new AsyncInternet().execute(serverURl, jsonObj.toString());
        return true;
    }

    @Override
    public ArrayList<HashMap<String, String>> getExpenseDetails(String columns, String where) {
        return null;
    }

    @Override
    public URLConnection createConnection(String serverUrl) throws Exception{
        URL url = new URL(serverUrl);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;

        return httpConn;
    }
}
