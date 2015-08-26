package com.example.e134292.downtimeCapture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



public class studyEndActivity extends AppCompatActivity {

    //private String[] arrTemp = new String[5];

    //private String studyTitle;
    //private int numofReasons;

    //private String[] downtimeStart = new String[250];
    //private String[] downtimeEnd = new String[250];
    //private String[] downtimeReason = new String[250];
    //private int index;

    private String file;
    private String dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_end);

        Bundle extras = getIntent().getExtras();

        file = extras.getString("FILE_NAME");
        dir = extras.getString("DIR_NAME");
        //studyTitle = extras.getString("STUDY_TITLE");
        //numofReasons = extras.getInt("NUM_OF_REASONS");
        //arrTemp = new String[numofReasons];
        //arrTemp = extras.getStringArray("REASONS");
        //downtimeStart = extras.getStringArray("DT_START");
        //downtimeEnd = extras.getStringArray("DT_END");
        //downtimeReason = extras.getStringArray("DT_REASONS");
        //index = extras.getInt("INDEX");

        /*
        getSupportActionBar().setTitle(studyTitle);

        for (int i=0; i<index; i++) {

            final TextView changeStart = (TextView) findViewById(R.id.startTime);
            changeStart.setText("Start Time: " + downtimeStart[i]);

            final TextView changeEnd = (TextView) findViewById(R.id.endTime);
            changeEnd.setText("Start Time: " + downtimeEnd[i]);

            final TextView changeReason = (TextView) findViewById(R.id.reasonText);
            changeReason.setText("Start Time: " + downtimeReason[i]);
        }

        final TextView changeIndex = (TextView) findViewById(R.id.indexValue);
        changeIndex.setText("Index: " + index);
        */
        final TextView changeIndex = (TextView) findViewById(R.id.startTime);
        changeIndex.setText("Your Lost Time Study is Located at:\n" + file);



    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_end, menu);
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
