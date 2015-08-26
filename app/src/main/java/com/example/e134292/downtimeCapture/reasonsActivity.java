package com.example.e134292.downtimeCapture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class reasonsActivity extends Activity {

    private String[] arrTemp = new String[6];
    private String[] arrText = new String[6];
    private String studyTitle;
    //private int numofReasons;

    private String[] downtimeStart = new String[500];
    private String[] downtimeEnd = new String[500];
    private String[] downtimeReason = new String[500];
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        arrText[0] = getResources().getString(R.string.downtime_reason_one);
        arrText[1] = getResources().getString(R.string.downtime_reason_two);
        arrText[2] = getResources().getString(R.string.downtime_reason_three);
        arrText[3] = getResources().getString(R.string.downtime_reason_four);
        arrText[4] = getResources().getString(R.string.downtime_reason_five);
        arrText[5] = getResources().getString(R.string.downtime_reason_six);

        setContentView(R.layout.lyt_listview_activity);

        //Bundle extras = getIntent().getExtras();
        //studyTitle = extras.getString("STUDY_TITLE");
        //numofReasons = extras.getInt("NUM_OF_REASONS");

        index = 0;

        //final TextView changeStudyTitle = (TextView) findViewById(R.id.studyTitleTV);
        //changeStudyTitle.setText(studyTitle);

        //if (studyTitle.isEmpty()) {
        //    studyTitle = "New Study";
        //}

        //changeStudyTitle.setText(studyTitle);


        //arrText = new String[numofReasons];
        //arrTemp = new String[numofReasons];

        //for(int i=0; i<numofReasons; i++) {
        //    arrText[i] = "Downtime Reason " + (i+1);
        //}

        MyListAdapter myListAdapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(myListAdapter);
    }

    public void startDowntime (View view) {
        EditText studyTitleET = (EditText) findViewById(R.id.studyTitleET);
        String studyTitleST = studyTitleET.getText().toString();
        studyTitle = studyTitleST;

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

    private class MyListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if(arrText != null && arrText.length != 0){
                return arrText.length;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arrText[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //ViewHolder holder = null;
            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                LayoutInflater inflater = reasonsActivity.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.lyt_listview_list, null);
                holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
                holder.editText1 = (EditText) convertView.findViewById(R.id.editText1);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.ref = position;

            holder.textView1.setText(arrText[position]);
            holder.editText1.setText(arrTemp[position]);
            holder.editText1.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    arrTemp[holder.ref] = arg0.toString();
                }
            });

            return convertView;
        }

        private class ViewHolder {
            TextView textView1;
            EditText editText1;
            int ref;
        }
    }
}