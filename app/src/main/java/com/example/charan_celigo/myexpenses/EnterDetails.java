package com.example.charan_celigo.myexpenses;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.main.Initialize;
import com.utils.storage.factory.StorageFactory;
import com.utils.storage.StorageUtil;

import java.util.HashMap;


public class EnterDetails extends ActionBarActivity {
//    DBHelper mydb;
    StorageUtil storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

//        mydb = new DBHelper(this);
        String storageType = "remote";
        String serverUrl = "http://a15cf38e.ngrok.io";
        Initialize init = new Initialize(storageType, serverUrl);
        init.initializeAll();
        storage = init.getStorage();
        Spinner expTypes = (Spinner)findViewById(R.id.expTypes);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.expense_type_array, R.layout.support_simple_spinner_dropdown_item);
        expTypes.setAdapter(spinnerAdapter);


        Button submitExp = (Button)findViewById(R.id.submitExp);

        submitExp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add feature to enable adding multiple columns.
                HashMap<String, String> expDetails = new HashMap<String, String>();
                Spinner expTypes = (Spinner)findViewById(R.id.expTypes);
                String expTypeText = expTypes.getSelectedItem().toString();
                //expDetails.put(StorageUtil.EXPENSE_TYPE, expTypeText);
                expDetails.put("date", "2016-12-19");

                EditText expSpent = (EditText)findViewById(R.id.expAmount);
                //Double expSpentAmount = Double.parseDouble(expSpent.getText().toString());
                String expSpentAmount = expSpent.getText().toString();
                expDetails.put(StorageUtil.EXPENSE_AMOUNT, expSpentAmount);

                EditText expNotes = (EditText)findViewById(R.id.expNotes);
                String expNotesNotes = expNotes.getText().toString();
                expDetails.put(StorageUtil.EXPENSE_NOTES, expNotesNotes);

                try {
                    storage.insertExpense(expDetails);
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Could not update: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                expSpent.setText("");
                expNotes.setText("");
                expTypes.setSelection(0);
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
        if (id == R.id.action_settings) {
            return true;
        }
        Intent intent=null;
        switch ((id)){
            case R.id.main_menu:
                //intent = new Intent(getApplicationContext(),EnterDetails.class);
                //startActivity(intent);
                break;
            case R.id.display_expenses:
                intent = new Intent(getApplicationContext(),DisplayExpenses.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void run(View view){
/*
        if (mydb.insertExpense("10.00","Home")){
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        }
*/
    }
}
