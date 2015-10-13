//package com.aliceresponde.hometest;
//
//import android.test.ActivityInstrumentationTestCase2;
//
//import com.github.mikephil.charting.charts.BarChart;
//
///**
// * Created by alice on 10/12/15.
// */
//public class TestChart extends ActivityInstrumentationTestCase2 {
//    public TestChart() {
//        super(ChartActivity.class);
//    }
//
//    public void testActivityExists() {
//        ChartActivity activity = (ChartActivity) getActivity();
//        assertNotNull(activity);
//    }
//
//    /**
//     * Did you get MainActivity?
//     */
//    public void testUI() {
//        ChartActivity activity = (ChartActivity) getActivity();
//
//        final BarChart chart = (BarChart) activity.findViewById(R.id.chart1);
//        getInstrumentation().runOnMainSync(new Runnable() {
//            @Override
//            public void run() {
//                chart.requestFocus();
//            }
//        });
//
//
//    }
//}
