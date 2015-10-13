package com.aliceresponde.hometest;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by alice on 10/12/15.
 * UI Testing for App
 *
 * TDD : create test before write actual program
 *
 * Test for UI/Logic that  doesnt exist yet
 * Expect to see build error o test failure
 * Then UI element/logic  is created
 * Finally run the test to be sure that it will be succed
 *
 */
public class Test extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;
    private TextView tv_question;
    private Button btn_get_change;
    private EditText et_dollar;
    private Intent intent;

    public Test() {
        super(MainActivity.class);
    }

    /**
     * Test UI elements
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

         activity = (MainActivity) getActivity();
         tv_question = (TextView) activity.findViewById(R.id.tv_question);
         btn_get_change = (Button) activity.findViewById(R.id.btn_change);
         et_dollar =(EditText) activity.findViewById(R.id.et_dollar);

         intent = new Intent(getInstrumentation().getContext(),MainActivity.class);
    }

    /**
     * To know  if MainActivityy Exist, our app, and  testcode was configurate right
     * RedColor - Is to bad
     * BlueColor - it can be better
     * GreenColor - Success
     */
    public void testPrecondition() {
        assertNotNull("Activity is null", activity);
        assertNotNull("EditText Question is null", tv_question);
        assertNotNull("Button get_change is null", btn_get_change);

    }

    @MediumTest
    public void testClickMeButton_layout() {
        //Retrieve the top-level window decor view
        final View decorView = activity.getWindow().getDecorView();

        //Verify that the mClickMeButton is on screen
        ViewAsserts.assertOnScreen(decorView, btn_get_change);

        //Verify width and heights
        final ViewGroup.LayoutParams layoutParams = btn_get_change.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    /**
     * Verify  editText  is  empty
     */
    @MediumTest
    public void test_EditText_dollar_isEmpty() {
        //Verify that the mInfoTextView is initialized with the correct default value
        assertEquals("", et_dollar.getText().toString());
    }

        /**
         * Did you get MainActivity?
         */
    public void testUI() {
        //select EditText input calling et_dollar.requestFocus in UI thread
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                et_dollar.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync(); //, wait that app will be inactive
        getInstrumentation().sendStringSync("1"); // setting "1" in my input


        //Testing Button btn_change, to get change of dollar to others currencies
        TouchUtils.clickView(this, btn_get_change);  // is possible to click the button?
    }

    /**
     *  Tests the correctness of the initial text.
     */
    public void test_activity_question_labelText() {
        //It is good practice to read the string from your resources in order to not break
        //multiple tests when a string changes.
        final String expected = activity.getString(R.string.question);
        final String actual = tv_question.getText().toString();
        assertEquals("Question contains wrong text", expected, actual);
    }

    public void test_activity_button_labelText() {
        //It is good practice to read the string from your resources in order to not break
        //multiple tests when a string changes.
        final String expected = activity.getString(R.string.btn_get_change);
        final String actual = btn_get_change.getText().toString();
        assertEquals("Button contains wrong text", expected, actual);
    }





}
