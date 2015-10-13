package com.aliceresponde.hometest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.android.volley.Request.Method.GET;

/**
 * To present a  clear result of the currency change, I propose populate the result in a
 * colourfull barChart, where I avoid float numbers for the answer. Then the result will be
 * a round value.
 * For example for n, any  integer value positive
 *              0               =>  0
 *             n.5              => n.5
 *             n.5< X <(n+1) =  =>  (n+1)
 *             n  <= X < n.5  => (n)
 */
public class ChartActivity extends AppCompatActivity {

    public MyCurrency currency;
    BarChart chart;
    float dollar_amount;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        context = getBaseContext();

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            chart = new BarChart(ChartActivity.this);
            setContentView(chart);

            Bundle extras = getIntent().getExtras();
            dollar_amount = extras.getFloat("dollar_amount");
            currency = new MyCurrency();
            try {
                  updateCurrency();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(context, "Verify your internet access", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /***
     * Web Service Request
     * @return
     * @throws IOException
     */
    public void updateCurrency() throws IOException {
        RequestQueue queue = Volley.newRequestQueue(ChartActivity.this);
        String url ="http://api.fixer.io/latest?base=USD";

        // Request a string response from the provided URL.


        StringRequest stringRequest = new StringRequest(GET, url,
                new Response.Listener<String>()  {
                    @Override
                    public void onResponse(String response) {
                        try {

                            EditText et_dollar = (EditText) findViewById(R.id.et_dollar);
                            JSONObject myResponse = new JSONObject(response);
                            JSONObject object_rates = myResponse.getJSONObject("rates");
                            DecimalFormat df = new DecimalFormat("0.00000");

                            currency.setValue_EUR(Float.parseFloat(df.format(Float.parseFloat(object_rates.getString("EUR")))));
                            currency.setValue_BRL(Float.parseFloat(df.format(Float.parseFloat(object_rates.getString("BRL")))));
                            currency.setValue_GBP(Float.parseFloat(df.format(Float.parseFloat(object_rates.getString("GBP")))));
                            currency.setValue_JPY(Float.parseFloat(df.format(Float.parseFloat(object_rates.getString("JPY")))));

                            Log.i("currency1", currency.toString());
                            loadChart();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "That didn't work!");
                    }
                }
        );
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /**
     * Create a BarChar that said from an specific amount of dollar, the equivalence value for EUR, BRL, GBP, JPY
     */
    public  void loadChart(){

        // Create Axis 'X' horizontal,  with the coin names
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("EUR");
        labels.add("BRL");
        labels.add("GBP");
        labels.add("JPY");

        Log.i("currency 1.1", currency.toString());

        // Create Axis 'Y'  vertical,  with  the currency conversion
        ArrayList<BarEntry> entries = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00000");
        entries.add(new BarEntry(Float.parseFloat(df.format(dollar_amount*currency.getValue_EUR())), 0));
        entries.add(new BarEntry(Float.parseFloat(df.format(dollar_amount*currency.getValue_BRL())), 1));
        entries.add(new BarEntry(Float.parseFloat(df.format(dollar_amount*currency.getValue_GBP())), 2));
        entries.add(new BarEntry(Float.parseFloat(df.format(dollar_amount*currency.getValue_JPY())), 3));

        //Subtitle
        BarDataSet dataset = new BarDataSet(entries, "Convertion of " + dollar_amount +" dollars");

        // I want to use different colous
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(labels, dataset);
        chart.setData(data);

        // 3000 milseg to animate barChart
        chart.animateY(3000);
        chart.setDrawValueAboveBar(true);
        chart.setPinchZoom(true);

    }

}
