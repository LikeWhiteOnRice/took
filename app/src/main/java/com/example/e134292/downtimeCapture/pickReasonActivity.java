package com.example.e134292.downtimeCapture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;


public class pickReasonActivity extends AppCompatActivity {

    private String[] arrTemp = new String[6];

    private String studyTitle;
    //private int numofReasons;

    private String[] downtimeStart = new String[500];
    private String[] downtimeEnd = new String[500];
    private String[] downtimeReason = new String[500];
    private int index;

    boolean reasonPicked;

    private Date startTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_reason);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Bundle extras = getIntent().getExtras();
        studyTitle = extras.getString("STUDY_TITLE");
        //numofReasons = extras.getInt("NUM_OF_REASONS");
        //arrTemp = new String[numofReasons];
        arrTemp = extras.getStringArray("REASONS");
        downtimeStart = extras.getStringArray("DT_START");
        downtimeEnd = extras.getStringArray("DT_END");
        downtimeReason = extras.getStringArray("DT_REASONS");
        index = extras.getInt("INDEX");

        getSupportActionBar().setTitle(studyTitle);

        reasonPicked = false;

        /*
        final TextView changeText2 = (TextView) findViewById(R.id.testTV2);
        changeText2.setText("Start Time: " + downtimeStart[index]);

        final TextView changeText = (TextView) findViewById(R.id.testTV);
        changeText.setText("End Time: " + downtimeEnd[index]);
        */

        final Button changeButtonOne = (Button) findViewById(R.id.dtButtonOne);
        changeButtonOne.setText(arrTemp[0]);

        final Button changeButtonTwo = (Button) findViewById(R.id.dtButtonTwo);
        changeButtonTwo.setText(arrTemp[1]);

        final Button changeButtonThree = (Button) findViewById(R.id.dtButtonThree);
        changeButtonThree.setText(arrTemp[2]);

        final Button changeButtonFour = (Button) findViewById(R.id.dtButtonFour);
        changeButtonFour.setText(arrTemp[3]);

        final Button changeButtonFive = (Button) findViewById(R.id.dtButtonFive);
        changeButtonFive.setText(arrTemp[4]);

        final Button changeButtonSix = (Button) findViewById(R.id.dtButtonSix);
        changeButtonSix.setText(arrTemp[5]);

    }

    public void buttonOnePress(View view) {
        downtimeReason[index] = arrTemp[0];
        reasonPicked = true;
        nextDowntime(view);
    }

    public void buttonTwoPress(View view) {
        downtimeReason[index] = arrTemp[1];
        reasonPicked = true;
        nextDowntime(view);
    }

    public void buttonThreePress(View view) {
        downtimeReason[index] = arrTemp[2];
        reasonPicked = true;
        nextDowntime(view);
    }

    public void buttonFourPress(View view) {
        downtimeReason[index] = arrTemp[3];
        reasonPicked = true;
        nextDowntime(view);
    }

    public void buttonFivePress(View view) {
        downtimeReason[index] = arrTemp[4];
        reasonPicked = true;
        nextDowntime(view);
    }

    public void buttonSixPress(View view) {
        downtimeReason[index] = arrTemp[5];
        reasonPicked = true;
        nextDowntime(view);
    }

    public void nextDowntime (View view) {

        if (!reasonPicked) {
            EditText reasonET = (EditText) findViewById(R.id.reasonET);
            String downtimeReasonST = reasonET.getText().toString();
            downtimeReason[index] = downtimeReasonST;
        }

        Intent intent = new Intent(this,stopDowntimeActivity.class);
        Bundle extras = new Bundle();
        extras.putString("STUDY_TITLE", studyTitle);
        //extras.putInt("NUM_OF_REASONS", numofReasons);
        extras.putInt("INDEX", index);
        extras.putStringArray("REASONS", arrTemp);
        extras.putStringArray("DT_START", downtimeStart);
        extras.putStringArray("DT_END", downtimeEnd);
        extras.putStringArray("DT_REASONS", downtimeReason);

        reasonPicked = false;

        intent.putExtras(extras);
        startActivity(intent);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study, menu);
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
    */
}
