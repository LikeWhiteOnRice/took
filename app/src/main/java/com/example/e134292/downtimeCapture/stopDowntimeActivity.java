package com.example.e134292.downtimeCapture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;
import java.util.Date;


public class stopDowntimeActivity extends AppCompatActivity {

    private String[] arrTemp = new String[6];

    private String studyTitle;
    //private int numofReasons;

    private String[] downtimeStart = new String[500];
    private String[] downtimeEnd = new String[500];
    private String[] downtimeReason = new String[500];
    private int index;
    //Calendar buttonTime;

    private Date startTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_downtime);

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

        /*
        buttonTime = Calendar.getInstance();

        final Button changeButtonText = (Button) findViewById(R.id.stopDowntime);
        changeButtonText.setText((buttonTime.get(Calendar.HOUR) + ":") +
                (buttonTime.get(Calendar.MINUTE) + ":") +
                (buttonTime.get(Calendar.SECOND)));
        /*
        final TextView changeStudyTitle = (TextView) findViewById(R.id.studyTitleTV);
        final TextView changeText = (TextView) findViewById(R.id.newTV);

        changeText.setText((gcalendar.get(Calendar.HOUR) + ":") +
                (gcalendar.get(Calendar.MINUTE) + ":") +
                (gcalendar.get(Calendar.SECOND)));
        */
    }

    public void pickReason (View view) {
        Calendar calendar = Calendar.getInstance();
        downtimeEnd[index] = ((calendar.get(Calendar.HOUR) + ":") +
                (calendar.get(Calendar.MINUTE) + ":") +
                (calendar.get(Calendar.SECOND)));

        index++;

        Intent intent = new Intent(this, startDowntimeActivity.class);
        Bundle extras = new Bundle();
        extras.putString("STUDY_TITLE", studyTitle);
        //extras.putInt("NUM_OF_REASONS", numofReasons);
        extras.putInt("INDEX", index);
        extras.putStringArray("REASONS", arrTemp);
        extras.putStringArray("DT_START", downtimeStart);
        extras.putStringArray("DT_END", downtimeEnd);
        extras.putStringArray("DT_REASONS", downtimeReason);

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
