package com.utils.storage;

import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Charan-celigo on 3/19/2017.
 */
public interface StorageUtil {
    public static final String EXPENSE_TYPE = "type";
    public static final String EXPENSE_AMOUNT = "amount";
    public static final String EXPENSE_NOTES = "description";
    public static final String EXPENSE_DATE = "date";

    public boolean insertExpense(HashMap<String, String> expDetails) throws Exception;
    public ArrayList<HashMap<String, String>> getExpenseDetails(String columns, String where);
    public URLConnection createConnection(String url) throws Exception;
}
