package com.utils.storage.local;

import com.utils.storage.StorageUtil;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Charan-celigo on 3/19/2017.
 */
public class LocalStorage implements StorageUtil {
    @Override
    public URLConnection createConnection(String url) throws Exception {
        return null;
    }

    @Override
    public boolean insertExpense(HashMap<String, String> expDetails) {
        return false;
    }

    @Override
    public ArrayList<HashMap<String, String>> getExpenseDetails(String columns, String where) {
        return null;
    }
}
