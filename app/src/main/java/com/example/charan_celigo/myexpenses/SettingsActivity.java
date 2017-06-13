package com.example.charan_celigo.myexpenses;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SettingsActivity extends ActionBarActivity {
    DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button submitExpType = (Button)findViewById(R.id.submitExType);

        submitExpType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newExpType = (EditText)findViewById(R.id.newExpType);
                String s = newExpType.getText().toString();
                if (!dbHelper.insertNewExpType(s))
                    Toast.makeText(getApplicationContext(), "Expense Type "+s+" already exists", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Expense Type "+s+" updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
