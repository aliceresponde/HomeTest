package com.aliceresponde.hometest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The user  can enter an int amount of dollars, to get the change in differents coints
 */
public class MainActivity extends AppCompatActivity {

    Context context;
    EditText et_dollar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         context = MainActivity.this;
         et_dollar= (EditText) findViewById(R.id.et_dollar);
    }

    public void did_tap_get_change(View view) {

        String  str_et_dollar = et_dollar.getText().toString();

        if(str_et_dollar.length()>0) {
            float float_dollar = Float.parseFloat(str_et_dollar);
            intent = new Intent(this, ChartActivity.class);
            intent.putExtra("dollar_amount", float_dollar);
            startActivity(intent);
        }
        else {
            Toast.makeText(context, "Please enter the dollar amount",Toast.LENGTH_LONG).show();
        }


    }


}
