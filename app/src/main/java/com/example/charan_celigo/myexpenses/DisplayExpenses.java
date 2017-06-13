package com.example.charan_celigo.myexpenses;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class DisplayExpenses extends ActionBarActivity {
    DBHelper dbHelper = new DBHelper(this);
    String ALL_STRING = "-ALL-";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expenses);

        Spinner expTypes = (Spinner)findViewById(R.id.spinner11);
        CharSequence[] strings = this.getResources().getTextArray(R.array.expense_type_array);
        CharSequence[] strings1 = new CharSequence[strings.length+1];
        strings1[0]=ALL_STRING;
        for (int i=0;i<strings.length;i++){
            strings1[i+1]=strings[i];
        }
        //ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.expense_type_array, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter<CharSequence>(this, R.layout.support_simple_spinner_dropdown_item, strings1);
        Spinner month = (Spinner)findViewById(R.id.spinner22);
        ArrayAdapter<CharSequence> spinnerAdapter1 = ArrayAdapter.createFromResource(this, R.array.months_array, R.layout.support_simple_spinner_dropdown_item);
        //String[] strings = (String[])this.getResources().getTextArray(R.array.expense_type_array);
        //CharSequence[] options=new CharSequence[strings.length+1];//{ALL_STRING};new ArrayAdapter<CharSequence>(context, textViewResId, strings);
        //options[0] = ALL_STRING;
        int i=0;
        //ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, strings);
        /*for (CharSequence string : strings){
            options[i++]=string;
        }*/
        expTypes.setAdapter(spinnerAdapter);
        expTypes.setSelection(0);

        month.setAdapter(spinnerAdapter1);
        month.setSelection(0);

/*        Spinner expTypes2 = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> spinnerAdapter2 = ArrayAdapter.createFromResource(this, R.array.months_array, R.layout.support_simple_spinner_dropdown_item);
        expTypes2.setAdapter(spinnerAdapter2);
        expTypes.setSelection(0);*/
        Button button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HashMap<String, String> expDetails = dbHelper.getAllExpenses();
                Spinner expTypes = (Spinner)findViewById(R.id.spinner11);
                String expTypeText = expTypes.getSelectedItem().toString();//ALL_STRING;

                Spinner expMonth = (Spinner)findViewById(R.id.spinner11);
                String expMonthText = expMonth.getSelectedItem().toString();//ALL_STRING;

                String columns = "*";
                String query = "select "+columns+" from expenses";
                ArrayList<HashMap<String, String>> al;
                if (expTypeText.equals(ALL_STRING) && expMonthText.equals(ALL_STRING)) {
                    //al = dbHelper.getExpenseDetails("*", null);

                    //al = dbHelper.getTableContents(query);
                }
                else{
                    query = query+" where "+DBHelper.EXPENSE_TYPE+"='"+expTypeText+"'";
                    //al = dbHelper.getTableContents(query+" where "+DBHelper.EXPENSE_TYPE+"="+expTypeText);
                    //al=dbHelper.getExpenseDetails(expTypeText, null);
                }

                al = dbHelper.getTableContents(query);//+" where "+DBHelper.EXPENSE_TYPE+"="+expTypeText);

                TableLayout tv=(TableLayout) findViewById(R.id.table1);
                tv.removeAllViewsInLayout();
                int flag=1;

/*                TableRow tr=new TableRow(DisplayExpenses.this);

                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                // this will be executed once
                if(flag==1) {

                    TextView b3 = new TextView(DisplayExpenses.this);
                    b3.setText("column heading 1");
                    b3.setTextColor(Color.BLUE);
                    b3.setTextSize(15);
                    tr.addView(b3);

                    TextView b4 = new TextView(DisplayExpenses.this);
                    b4.setPadding(10, 0, 0, 0);
                    b4.setTextSize(15);
                    b4.setText("column heading 2");
                    b4.setTextColor(Color.BLUE);
                    tr.addView(b4);

                    TextView b5 = new TextView(DisplayExpenses.this);
                    b5.setPadding(10, 0, 0, 0);
                    b5.setText("column heading 3");
                    b5.setTextColor(Color.BLUE);
                    b5.setTextSize(15);
                    tr.addView(b5);
                    tv.addView(tr);

                    final View vline = new View(DisplayExpenses.this);
                    vline.setLayoutParams(new
                            TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                    vline.setBackgroundColor(Color.BLUE);
                    tv.addView(vline); // add line below heading
                    flag = 0;
                }*///End comment here.
                HashMap<String, String> rowNames = rowNames();
                int i=1;
                for (HashMap<String, String> expDetails : al){
                    Set<String> keys = expDetails.keySet();
                    TableRow tr=new TableRow(DisplayExpenses.this);
                    tr.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    if (i==1){
                        TableRow tr1=new TableRow(DisplayExpenses.this);
                        for (String rowName : keys){
                            TextView b3 = new TextView(DisplayExpenses.this);
                            if (rowName.equals(DBHelper.EXPENSE_DATE) || rowName.equals("id")){
                                continue;
                            }
                            else {
                                //b3.setText(rowName.substring("Expense".length()+1, rowName.length()));
                                //b3.setText(rowName);
                                b3.setText(rowNames.get(rowName));
                                //Toast.makeText(getApplicationContext(), rowName, Toast.LENGTH_SHORT).show();
                            }
                            b3.setTextColor(Color.BLUE);
                            b3.setTextSize(15);
                            b3.setMaxLines(3);
                            tr1.addView(b3);
                        }
                        tv.addView(tr1);
                        final View vline = new View(DisplayExpenses.this);
                        vline.setLayoutParams(new
                                TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                        vline.setBackgroundColor(Color.BLUE);
                        tv.addView(vline); // add line below heading
                    }
                        for (String rowName : keys){
                            TextView b3 = new TextView(DisplayExpenses.this);
                            b3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            if (rowName.equals(DBHelper.EXPENSE_DATE) || rowName.equals("id")){
                                continue;
                            }
                            else{
                                b3.setText(expDetails.get(rowName));
                            }
                            //b3.setText(expDetails.get(rowName));
                            b3.setTextColor(Color.BLUE);
                            b3.setTextSize(15);
                            //b3.setWidth(0);
                            b3.setMaxLines(3);
                            tr.addView(b3);
                        }
                    tv.addView(tr);
                    final View vline1 = new View(DisplayExpenses.this);
                    vline1.setLayoutParams(new
                            TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                    vline1.setBackgroundColor(Color.WHITE);
                    tv.addView(vline1);  // add line below each row
/*
                    if (i==1) {
                        final View vline = new View(DisplayExpenses.this);
                        vline.setLayoutParams(new
                                TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                        vline.setBackgroundColor(Color.BLUE);
                        tv.addView(vline); // add line below heading
                    }
                    else{
                        final View vline1 = new View(DisplayExpenses.this);
                        vline1.setLayoutParams(new
                                TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                        vline1.setBackgroundColor(Color.WHITE);
                        tv.addView(vline1);  // add line below each row
                    }
*/
                    i=0;
                }

                //EditText detailsText = (EditText)findViewById(R.id.expDetails);
                //detailsText.setText(details);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*
        if (id == R.id.action_settings) {
            return true;
        }
*/
        Intent intent=null;
        switch ((id)){
            case R.id.main_menu:
                intent = new Intent(getApplicationContext(),EnterDetails.class);
                startActivity(intent);
                break;
            case R.id.display_expenses:
                //intent = new Intent(getApplicationContext(),DisplayExpenses.class);
                //startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private HashMap<String, String> rowNames(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(DBHelper.EXPENSE_NOTES, "Notes");
        map.put(DBHelper.EXPENSE_TYPE, "Type");
        map.put(DBHelper.EXPENSE_AMOUNT, "Amount");
        map.put(DBHelper.EXPENSE_DATE, "Date");
        return map;
    }

}
