package com.example.e134292.downtimeCapture;

import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class startDowntimeActivity extends AppCompatActivity {

    private String[] arrTemp = new String[6];

    private String studyTitle;
    //private int numofReasons;

    private String[] downtimeStart = new String[500];
    private String[] downtimeEnd = new String[500];
    private String[] downtimeReason = new String[500];
    private int index;


    //private Date startTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_downtime);

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
        final TextView changeStudyTitle = (TextView) findViewById(R.id.studyTitleTV);
        final TextView changeText = (TextView) findViewById(R.id.newTV);

        changeText.setText((gcalendar.get(Calendar.HOUR) + ":") +
                (gcalendar.get(Calendar.MINUTE) + ":") +
                (gcalendar.get(Calendar.SECOND)));
        */
    }

    public void stopDowntime (View view) {
        Calendar calendar = Calendar.getInstance();
        downtimeStart[index] = ((calendar.get(Calendar.HOUR) + ":") +
                (calendar.get(Calendar.MINUTE) + ":") +
                (calendar.get(Calendar.SECOND)));

        Intent intent = new Intent(this, pickReasonActivity.class);
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

    public void studyEnd(View view) {
        /*
        Intent intent = new Intent(this, studyEndActivity.class);
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
        */
        String start;
        String end;
        String reason;

        final String fileName = (studyTitle + ".xls");

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/My Documents/LostTimeStudies");

        //create directory if not exist
        if(!directory.exists()){
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;

        String fileNameST = file.toString();
        String directoryNameST = directory.toString();
        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("Study Data", 0);

            try {
                sheet.addCell(new Label(0, 0, "Start Time"));
                sheet.addCell(new Label(1, 0, "Stop Time"));
                sheet.addCell(new Label(2, 0, "Duration"));
                sheet.addCell(new Label(3, 0, "Reason"));

                for (int i=0; i<250; i++) {
                    start = downtimeStart[i];
                    end = downtimeEnd[i];
                    reason = downtimeReason[i];

                    sheet.addCell(new Label(0, i+1, start));
                    sheet.addCell(new Label(1, i+1, end));
                    //sheet.addCell(new Label(2, i+1, (downtimeEnd[i] - downtimeStart[i])));
                    sheet.addCell(new Label(3, i+1, reason));
                }
                /*
                if (cursor.moveToFirst()) {
                    do {
                        String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SUBJECT));
                        String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESC));

                        int i = cursor.getPosition() + 1;
                        sheet.addCell(new Label(0, i, title));
                        sheet.addCell(new Label(1, i, desc));
                    } while (cursor.moveToNext());
                }
                //closing cursor
                cursor.close();
                */
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, studyEndActivity.class);
        Bundle extras = new Bundle();
        extras.putString("FILE_NAME", fileNameST);
        extras.putString("DIR_NAME", directoryNameST);

        intent.putExtras(extras);
        startActivity(intent);
    }

    public void onBackPressed () {

        Intent intent = new Intent(this, stopDowntimeActivity.class);

        if (index == 0) {
            intent = new Intent(this, reasonsActivity.class);
        }

        if (index > 0) {
            index--;
        }

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
